package fr.univrouen.xmlProject.Services;

import fr.univrouen.xmlProject.Dtos.CvDto;
import fr.univrouen.xmlProject.Entities.*;
import fr.univrouen.xmlProject.Exceptions.CvNotFound;
import fr.univrouen.xmlProject.Exceptions.DuplicateCvException;
import fr.univrouen.xmlProject.Repositories.*;
import fr.univrouen.xmlProject.util.CvXsdValidator;
import fr.univrouen.xmlProject.util.HtmlTransformer;
import fr.univrouen.xmlProject.util.MarshallingHelper;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CvServiceImpl implements CvService{


    private final HtmlTransformer htmlTransformer;
    ModelMapper modelMapper ;

    private static final Logger logger = LoggerFactory.getLogger(CvServiceImpl.class);

    private final CvXsdValidator cvXsdValidator;
    private final CvRepository cvRepository ;
    private final IdentityRepository identityRepository;
    private final ObjectifRepository objectifRepository;
    private final ExperienceProRepository experienceProRepository;
    private final CompetenceRepository competenceRepository;
    private final CertifExpDetailsRepository certifExpDetailsRepository;
    private final DiplomaRepository diplomaRepository;
    private final DiverRepository diverRepository;
    private final LangueVivanteRepository langueVivanteRepository;
    private final AutreRepository autreRepository;
    private final MarshallingHelper marshallingHelper;


    public CvServiceImpl(CvRepository cvRepository,
                         IdentityRepository identityRepository,
                         ObjectifRepository objectifRepository,
                         ExperienceProRepository experienceProRepository,
                         CompetenceRepository competenceRepository,
                         CertifExpDetailsRepository certifExpDetailsRepository,
                         DiplomaRepository diplomaRepository,
                         DiverRepository diverRepository,
                         LangueVivanteRepository langueVivanteRepository,
                         AutreRepository autreRepository,
                         CvXsdValidator cvXsdValidator, MarshallingHelper marshallingHelper, HtmlTransformer htmlTransformer) {
        this.cvRepository = cvRepository;
        this.identityRepository = identityRepository;
        this.objectifRepository = objectifRepository;
        this.experienceProRepository = experienceProRepository;
        this.competenceRepository = competenceRepository;
        this.certifExpDetailsRepository = certifExpDetailsRepository;
        this.diplomaRepository = diplomaRepository;
        this.diverRepository = diverRepository;
        this.langueVivanteRepository = langueVivanteRepository;
        this.autreRepository = autreRepository;
        this.cvXsdValidator = cvXsdValidator;
        this.marshallingHelper = marshallingHelper;
        this.htmlTransformer = htmlTransformer;
    }


    @Override
    public Cv saveCv(String xmlCv) throws DuplicateCvException {
        logger.info("Entered saveCv method");

        Cv cv;
        try {
            logger.info("Attempting to unmarshal XML");
            cv = marshallingHelper.fromXml(xmlCv);
            logger.info("Successfully unmarshalled XML");
        } catch (JAXBException e) {
            logger.error("Error unmarshalling XML", e);
            throw new RuntimeException("Error unmarshalling XML", e);
        }

        if (cv == null || cv.getIdentity() == null) {
            logger.error("Unmarshalled CV or CV Identity is null");
            throw new RuntimeException("Unmarshalled CV or CV Identity is null");
        }

        // Duplicate CV check
        boolean exists = cvRepository.existsByIdentity_GenreAndIdentity_NomAndIdentity_PrenomAndIdentity_Tel(
                cv.getIdentity().getGenre(),
                cv.getIdentity().getNom(),
                cv.getIdentity().getPrenom(),
                cv.getIdentity().getTel()
        );

        if (exists) {
            logger.error("Duplicate CV found");
            throw new DuplicateCvException("Duplicate CV found");
        }

        // Save Identity
        Identity identity = cv.getIdentity();
        if (identity != null) {
            logger.info("Saving identity: " + identity);
            if (identity.getGenre() == null) {
                logger.error("Identity genre is null");
                throw new RuntimeException("Identity genre cannot be null");
            }
            Identity savedIdentity = identityRepository.save(identity);
            cv.setIdentity(savedIdentity);
            logger.info("Saved Identity: " + savedIdentity);
        }

        // Save Objectif
        if (cv.getObjectif() != null) {
            Objectif objectif = cv.getObjectif();
            if (objectif.getStatut() == null) {
                logger.error("Objectif statut is null");
                throw new RuntimeException("Objectif statut cannot be null");
            }
            Objectif savedObjectif = objectifRepository.save(objectif);
            cv.setObjectif(savedObjectif);
            logger.info("Saved Objectif: " + savedObjectif);
        }

        // Save ExperiencePro
        ExperiencePro experiencePro = cv.getExperiencePro();
        if (experiencePro != null) {
            logger.info("Saving ExperiencePro: " + experiencePro);
            ExperiencePro savedExperiencePro = experienceProRepository.save(experiencePro);
            cv.setExperiencePro(savedExperiencePro);
            logger.info("Saved ExperiencePro: " + savedExperiencePro);

            List<CertifExpDetails> expDetailsList = experiencePro.getExpDetails();
            if (expDetailsList != null) {
                for (CertifExpDetails expDetails : expDetailsList) {
                    if (expDetails != null) {
                        logger.info("Saving CertifExpDetails: " + expDetails);
                        expDetails.setExperiencePro(savedExperiencePro);
                        certifExpDetailsRepository.save(expDetails);
                    }
                }
            }
        }

        // Save Competence
        Competence competence = cv.getCompetence();
        if (competence != null) {
            logger.info("Saving Competence: " + competence);
            Competence savedCompetence = competenceRepository.save(competence);
            cv.setCompetence(savedCompetence);
            logger.info("Saved Competence: " + savedCompetence);

            List<Diploma> diplomes = competence.getDiplomes();
            if (diplomes != null) {
                for (Diploma diploma : diplomes) {
                    if (diploma != null) {
                        logger.info("Saving Diploma: " + diploma);
                        diploma.setCompetence(savedCompetence);
                        if (diploma.getDate() == null || diploma.getDate().isEmpty()) {
                            throw new IllegalArgumentException("Diploma date cannot be null");
                        }
                        if (diploma.getNiveau() == null) {
                            throw new IllegalArgumentException("Diploma niveau cannot be null");
                        }
                        diplomaRepository.save(diploma);
                    }
                }
            }

            List<CertifExpDetails> certifDetails = competence.getCertifDetails();
            if (certifDetails != null) {
                for (CertifExpDetails certif : certifDetails) {
                    if (certif != null) {
                        logger.info("Saving CertifExpDetails for Competence: " + certif);
                        certif.setCompetence(savedCompetence);
                        if (certif.getDatedeb() == null) {
                            throw new IllegalArgumentException("CertifExpDetails datedeb cannot be null");
                        }
                        if (certif.getTitre() == null) {
                            throw new IllegalArgumentException("CertifExpDetails titre cannot be null");
                        }
                        certifExpDetailsRepository.save(certif);
                    }
                }
            }
        }

        // Save Divers
        Divers divers = cv.getDivers();
        if (divers != null) {
            logger.info("Saving Divers: " + divers);

            Divers savedDivers = diverRepository.save(divers);
            cv.setDivers(savedDivers);
            logger.info("Saved Divers: " + savedDivers);

            // Save LangueVivantes
            for (LangueVivante lv : divers.getLangueVivantes()) {
                lv.setDiver(savedDivers);
                langueVivanteRepository.save(lv);
            }

            // Save Autres with validation
            for (Autre autre : divers.getAutres()) {
                if (autre.getTitre() == null || autre.getTitre().isEmpty()) {
                    logger.error("Autre titre is null or empty: " + autre);
                    throw new RuntimeException("Autre titre cannot be null or empty");
                }
                autre.setDiver(savedDivers);
                autreRepository.save(autre);
            }
        }

        Cv savedCv = cvRepository.save(cv);
        logger.info("Successfully saved CV: " + savedCv);
        return savedCv;
    }




    private Cv fromDtoToEntity(CvDto cvDto) {
        return modelMapper.map(cvDto, Cv.class);
    }

    private CvDto fromEntityToDto(Cv cvEntity) {
        return modelMapper.map(cvEntity, CvDto.class);
    }

    @Override
    public void updateCv(Long id, CvDto cvDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCv'");
    }


    @Override
    public List<Cv> findAllCv() {
        List<Cv> cvList = cvRepository.findAll();
        return  cvList;
    }

    @Override
    public Cv findById(Long id) throws CvNotFound {
        Optional<Cv> cvOptional = cvRepository.findById(id);
        if (cvOptional.isEmpty()) {
            throw new CvNotFound("CV not found for id: " + id);
        }
        return cvOptional.get();
    }

    @Override
    public String cvAsXml(Long id) throws CvNotFound, JAXBException {
        Cv cv = this.findById(id);
        String cvXml = marshallingHelper.toXml(cv);
        return cvXml;
    }

//    @Override
//    public String cvAsHtml(Long id) throws CvNotFound, JAXBException, Exception {
//        Cv cv = this.findById(id);
//        String cvXml;
//        try {
//            cvXml = marshallingHelper.toXml(cv);
//        } catch (JAXBException e) {
//            throw e;
//        }
//
//        String cvHtml;
//        try {
//            cvHtml = htmlTransformer.transformXmlToHtml(cvXml);
//        } catch (Exception e) {
//            throw e;
//        }
//        return cvHtml;
//    }


    public String generateCVListXML(List<Cv> cvList) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<cvList>");
        for (Cv cv : cvList) {
            xmlBuilder.append("<cv>");
            xmlBuilder.append("<Id>").append(cv.getId()).append("</Id>");
            xmlBuilder.append("<Identité>");
            xmlBuilder.append("<Genre>").append(cv.getIdentity().getGenre()).append("</Genre>");
            xmlBuilder.append("<Prénom>").append(cv.getIdentity().getPrenom()).append("</Prénom>");
            xmlBuilder.append("<Nom>").append(cv.getIdentity().getNom()).append("</Nom>");
            xmlBuilder.append("</Identité>");
            xmlBuilder.append("<Objectif>");
            xmlBuilder.append("<Statut>").append(cv.getObjectif().getStatut()).append("</Statut>");
            xmlBuilder.append("<Valeur>").append(cv.getObjectif().getValue()).append("</Valeur>");
            xmlBuilder.append("</Objectif>");

            // most recent diploma
            Optional<Diploma> mostRecentDiploma = cv.getCompetence().getDiplomes().stream()
                    .max(Comparator.comparing(Diploma::getDate));
            if (mostRecentDiploma.isPresent()) {
                Diploma diploma = mostRecentDiploma.get();
                xmlBuilder.append("<DernierDiplome>");
                xmlBuilder.append("<Date>").append(diploma.getDate()).append("</Date>");
                xmlBuilder.append("<Institut>").append(diploma.getInstitut()).append("</Institut>");
                xmlBuilder.append("<Intitule>").append(diploma.getIntitule()).append("</Intitule>");
                xmlBuilder.append("<Niveau>").append(diploma.getNiveau()).append("</Niveau>");
                xmlBuilder.append("</DernierDiplome>");
            }

            xmlBuilder.append("</cv>");
        }
        xmlBuilder.append("</cvList>");

        return xmlBuilder.toString();
    }

    @Override
    public String CvsXml() {
        List<Cv> cvList = findAllCv();
        String cvListXml = generateCVListXML(cvList);
        return cvListXml;
    }

    @Override
    public List<CvDto> CvsHtml() {
        List<Cv> cvList = findAllCv();

        List<CvDto> cvDtoList = new ArrayList<>();

        for (Cv cv : cvList) {
            CvDto cvDto = new CvDto();


            cvDto.setId(cv.getId().toString());

            // Map nested fields
            cvDto.setGenre(cv.getIdentity().getGenre());
            cvDto.setPrenom(cv.getIdentity().getPrenom());
            cvDto.setNom(cv.getIdentity().getNom());
            cvDto.setStatut(cv.getObjectif().getStatut());
            cvDto.setValue(cv.getObjectif().getValue());

            // Map mostRecentDiploma if present
            Optional<Diploma> mostRecentDiploma = cv.getCompetence().getDiplomes().stream()
                    .max(Comparator.comparing(Diploma::getDate));
            if (mostRecentDiploma.isPresent()) {
                cvDto.setInstitut(mostRecentDiploma.get().getInstitut());
                cvDto.setIntitule(mostRecentDiploma.get().getIntitule());
                cvDto.setNiveau(mostRecentDiploma.get().getNiveau());
                cvDto.setDate(mostRecentDiploma.get().getDate());
            }

            cvDtoList.add(cvDto);
        }

        return cvDtoList;
    }

    @Override
    public boolean deleteCv(Long id) {
        Optional<Cv> optionalCv = cvRepository.findById(id);
        if (optionalCv.isPresent()) {
            cvRepository.delete(optionalCv.get());
            return true; // Deletion successful
        } else {
            return false; // CV with given ID not found
        }
    }

}
