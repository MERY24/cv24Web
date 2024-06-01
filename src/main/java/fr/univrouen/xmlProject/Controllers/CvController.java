package fr.univrouen.xmlProject.Controllers;

import fr.univrouen.xmlProject.Dtos.CvDto;
import fr.univrouen.xmlProject.Dtos.Responses.ResponseDto;
import fr.univrouen.xmlProject.Entities.Cv;
import fr.univrouen.xmlProject.Exceptions.CvNotFound;
import fr.univrouen.xmlProject.Exceptions.DuplicateCvException;
import fr.univrouen.xmlProject.Exceptions.InvalidCvException;
import fr.univrouen.xmlProject.Services.CvService;
import fr.univrouen.xmlProject.util.CvXsdValidator;
import fr.univrouen.xmlProject.util.HtmlTransformer;
import fr.univrouen.xmlProject.util.MarshallingHelper;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cv24")
public class CvController {

    private static final Logger logger = LoggerFactory.getLogger(CvController.class);
    private final CvXsdValidator cvXsdValidator;
    private final CvService cvService;

    public CvController(CvXsdValidator cvXsdValidator, CvService cvService) {
        this.cvXsdValidator = cvXsdValidator;
        this.cvService = cvService;

    }

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseDto> insertCv(@RequestBody(required = false) String cvXml) {
        if (cvXml == null || cvXml.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseDto(null, "Empty or null XML received"));
        }

        try {
            System.out.println("Received CV XML: " + cvXml);
            InputStream inputStream = new ByteArrayInputStream(cvXml.getBytes(StandardCharsets.UTF_8));
            boolean isValid = cvXsdValidator.isValid(inputStream);
            if (!isValid) {
                throw new InvalidCvException("Invalid CV XML");
            }

            Cv savedCv = cvService.saveCv(cvXml);
            ResponseDto responseDto = new ResponseDto(savedCv.getId().toString(), "INSERTED");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (InvalidCvException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(null, "INVALID"));
        } catch (DuplicateCvException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto(null, "DUPLICATED"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(null, e.getMessage()));
        }
    }


        @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
        public ResponseEntity<ResponseDto> getCvAsXml(@RequestParam Long id) {
            try {
                String cvXml = cvService.cvAsXml(id);
                return ResponseEntity.ok(new ResponseDto(cvXml, "SUCCESS"));
            } catch (CvNotFound e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(null, "CV NOT FOUND"));
            } catch (JAXBException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(null, e.getMessage()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(null, e.getMessage()));
            }
        }


    @GetMapping(value = "/html")
    public String getCvInHTMLFormat(@RequestParam Long id, Model model) throws CvNotFound {


        if (id == null) {
            logger.error("ID is null or empty");
            throw new IllegalArgumentException("ID cannot be empty or blank");
        }

        Cv cv;
        try {
            logger.debug("Fetching CV with ID: {}", id);
            cv = cvService.findById(id);
            logger.debug("Fetched CV: {}", cv);
        } catch (CvNotFound e) {
            logger.error("CV not found with ID: {}", id);
            throw new CvNotFound("CV not found with ID: " + id);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching CV with ID: {}", id, e);
            throw new RuntimeException(e);
        }
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String date = format.format(new Date());
        logger.debug("Formatted date: {}", date);
        model.addAttribute("date", date);
        model.addAttribute("cv", cv);
        logger.debug("Model attributes set with CV: {}", cv);

        return "cv";
    }


    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllXmlCvSummed() throws CvNotFound {
        String cvs = cvService.CvsXml();
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }


    @GetMapping("/resume")
    public String getCvsHtml(Model model) {

        List<CvDto> cvList = cvService.CvsHtml();
        logger.info("CV List HTML: {}", cvList);
        model.addAttribute("cvList", cvList);
        return "cvList";
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseDto> deleteCv(@PathVariable Long id) {
        try {
            boolean deleted = cvService.deleteCv(id);
            if (deleted) {
                return ResponseEntity.ok(new ResponseDto(String.valueOf(id), "DELETED"));
            } else {
                return ResponseEntity.badRequest().body(new ResponseDto(null, "ERROR"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(null, "ERROR"));
        }
    }



    @PostMapping("/validate-xml")
    public ResponseEntity<String> validateXml(String xmlData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());

            boolean isValid = cvXsdValidator.isValid(inputStream);

            if (isValid) {
                return ResponseEntity.ok("Le XML est valide.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le XML est invalide.");
            }
        } catch (IOException | SAXException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la validation XML.");
        }
    }

}
