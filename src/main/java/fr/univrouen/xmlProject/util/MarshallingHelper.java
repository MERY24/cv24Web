package fr.univrouen.xmlProject.util;

import fr.univrouen.xmlProject.Entities.*;
import fr.univrouen.xmlProject.Entities.Enums.*;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MarshallingHelper {

    private static final Logger logger = LoggerFactory.getLogger(MarshallingHelper.class);

    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public MarshallingHelper() throws JAXBException {
        logger.info("Initializing MarshallingHelper");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cv.class, Identity.class, Objectif.class, Competence.class, CertifExpDetails.class, ExperiencePro.class, Diploma.class, LangueVivante.class, Autre.class, Divers.class);
            this.unmarshaller = jaxbContext.createUnmarshaller();
            this.marshaller = jaxbContext.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            this.marshaller.setProperty("jaxb.schemaLocation", "http://univ.fr/cv24 cv24.tp1.xsd");
            logger.info("MarshallingHelper initialized successfully");
        } catch (JAXBException e) {
            logger.error("Error initializing MarshallingHelper", e);
            throw e;
        }
    }

    public Cv fromXml(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Cv.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        unmarshaller.setAdapter(new EnumAdapter<>(Gender.class));
//        unmarshaller.setAdapter(new EnumAdapter<>(ContratType.class));
//        unmarshaller.setAdapter(new EnumAdapter<>(LangueCert.class));
//        unmarshaller.setAdapter(new EnumAdapter<>(LangueLvl.class));
//        unmarshaller.setAdapter(new EnumAdapter<>(QualifLvlType.class));

        StringReader reader = new StringReader(xml);
        Cv unmarshalled = (Cv) unmarshaller.unmarshal(reader);
        return unmarshalled;
    }



    public String toXml(Cv cv) throws JAXBException {
        StringWriter writer = new StringWriter();
        marshaller.marshal(cv, writer);
        return writer.toString();
    }

    public String getXmlEnumValue(Class<? extends Enum<?>> enumClass, String fieldName) {
        try {
            Field  field = enumClass.getField(fieldName);

            XmlEnumValue annotation = field.getAnnotation(XmlEnumValue.class);

            return annotation.value();
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }


//    public Map<String, String> fromXml(String xml) throws JAXBException {
//        Map<String, String> values = new HashMap<>();
//        JAXBElement<Cv> jaxbElement = (JAXBElement<Cv>) unmarshaller.unmarshal(new StringReader(xml));
//        Cv cv = jaxbElement.getValue();
//
//        // Extract values from Cv object and add them to the map
//        if (cv.getIdentity() != null) {
//            Identity identity = cv.getIdentity();
//            values.put("genre", identity.getGenre().toString());
//            values.put("nom", identity.getNom());
//            values.put("prenom", identity.getPrenom());
//            values.put("tel", identity.getTel());
//            values.put("mel", identity.getMel());
//        }
//        if (cv.getObjectif() != null) {
//            Objectif objectif = cv.getObjectif();
//            values.put("objectif", objectif.getValue());
//            values.put("statut", objectif.getStatut().toString());
//        }
//        if (cv.getExperiencePro() != null && !cv.getExperiencePro().getExpDetails().isEmpty()) {
//            CertifExpDetails expDetails = cv.getExperiencePro().getExpDetails().get(0); // Assuming only one detail for simplicity
//            values.put("datedeb_exp", expDetails.getDatedeb().toString());
//            values.put("datefin_exp", expDetails.getDatefin() != null ? expDetails.getDatefin().toString() : "");
//            values.put("titre_exp", expDetails.getTitre());
//        }
//        if (cv.getCompetence() != null && !cv.getCompetence().getDiplomes().isEmpty()) {
//            Diploma diploma = cv.getCompetence().getDiplomes().get(0); // Assuming only one diploma for simplicity
//            values.put("date_diploma", diploma.getDate().toString());
//            values.put("institut_diploma", diploma.getInstitut());
//            values.put("intitule_diploma", diploma.getIntitule());
//            values.put("niveau_diploma", diploma.getNiveau().toString());
//        }
//        if (cv.getCompetence() != null && !cv.getCompetence().getCertifDetails().isEmpty()) {
//            CertifExpDetails certif = cv.getCompetence().getCertifDetails().get(0); // Assuming only one certification for simplicity
//            values.put("datedeb_certif", certif.getDatedeb().toString());
//            values.put("datefin_certif", certif.getDatefin() != null ? certif.getDatefin().toString() : "");
//            values.put("titre_certif", certif.getTitre());
//        }
//        if (cv.getDivers() != null) {
//            Diver divers = cv.getDivers();
//            if (divers.getLangueVivantes() != null && !divers.getLangueVivantes().isEmpty()) {
//                LangueVivante langueVivante = divers.getLangueVivantes().get(0); // Assuming only one language for simplicity
//                values.put("lang_langue", langueVivante.getLang());
//                values.put("cert_langue", langueVivante.getCert().toString());
//                values.put("nivs_langue", langueVivante.getNivs() != null ? langueVivante.getNivs().toString() : "");
//                values.put("nivi_langue", langueVivante.getNivi() != null ? langueVivante.getNivi().toString() : "");
//            }
//            if (divers.getAutres() != null && !divers.getAutres().isEmpty()) {
//                Autre autre = divers.getAutres().get(0); // Assuming only one other item for simplicity
//                values.put("titre_autre", autre.getTitre());
//                values.put("comment_autre", autre.getComment());
//            }
//        }
//
//        return values;
//    }



//    public Cv fromXml(String xml) throws JAXBException {
//        // Create a HashMap to store the values extracted from the XML
//        Map<String, Object> valuesMap = new HashMap<>();
//
//        // Use XPath to extract values from XML and store them in the HashMap
//        try {
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            InputSource is = new InputSource(new StringReader(xml));
//            Document document = builder.parse(is);
//
//            XPathFactory xPathfactory = XPathFactory.newInstance();
//            XPath xpath = xPathfactory.newXPath();
//
//            // Extract values for Identity
//            String genre = xpath.evaluate("/cv24:cv24/cv24:Identité/cv24:genre", document);
//            String nom = xpath.evaluate("/cv24:cv24/cv24:Identité/cv24:nom", document);
//            String prenom = xpath.evaluate("/cv24:cv24/cv24:Identité/cv24:prenom", document);
//            String tel = xpath.evaluate("/cv24:cv24/cv24:Identité/cv24:tel", document);
//            String mel = xpath.evaluate("/cv24:cv24/cv24:Identité/cv24:mel", document);
//
//            // Extract values for Objectif
//            String objectifStatut = xpath.evaluate("/cv24:cv24/cv24:Objectif/@statut", document);
//            String objectifValue = xpath.evaluate("/cv24:cv24/cv24:Objectif", document);
//
//
//            // Extract values for ExperiencePro
//            XPathExpression expProExpr = xpath.compile("/cv24:cv24/cv24:ExpériencePro/cv24:detail");
//            NodeList expProNodes = (NodeList) expProExpr.evaluate(document, XPathConstants.NODESET);
//
//            List<Map<String, String>> expProDetailsList = new ArrayList<>();
//
//            for (int i = 0; i < expProNodes.getLength(); i++) {
//                Node expProNode = expProNodes.item(i);
//                Map<String, String> expProDetailsMap = new HashMap<>();
//
//                // Extract details for each ExpériencePro element
//                String datedebExp = xpath.evaluate("cv24:datedeb", expProNode);
//                String datefinExp = xpath.evaluate("cv24:datefin", expProNode);
//                String titreExp = xpath.evaluate("cv24:titre", expProNode);
//
//                // Add extracted values to the map
//                expProDetailsMap.put("datedeb_exp", datedebExp);
//                expProDetailsMap.put("datefin_exp", datefinExp);
//                expProDetailsMap.put("titre_exp", titreExp);
//
//                // Add the map to the list of ExpériencePro details
//                expProDetailsList.add(expProDetailsMap);
//            }
//
//
//            // Extract values for Divers
//            XPathExpression diversExpr = xpath.compile("/cv24:cv24/cv24:Divers/*");
//            NodeList diversNodes = (NodeList) diversExpr.evaluate(document, XPathConstants.NODESET);
//
//            List<Map<String, String>> diversDetailsList = new ArrayList<>();
//
//            for (int i = 0; i < diversNodes.getLength(); i++) {
//                Node diversNode = diversNodes.item(i);
//                Map<String, String> diversDetailsMap = new HashMap<>();
//
//                // Extract details for each Divers element
//                String langLangue = xpath.evaluate("cv24:lv/@lang", diversNode);
//                String certLangue = xpath.evaluate("cv24:lv/@cert", diversNode);
//                String nivsLangue = xpath.evaluate("cv24:lv/@nivs", diversNode);
//                String niviLangue = xpath.evaluate("cv24:lv/@nivi", diversNode);
//                String titreAutre = xpath.evaluate("cv24:autre/@titre", diversNode);
//                String commentAutre = xpath.evaluate("cv24:autre/@comment", diversNode);
//
//                // Add extracted values to the map
//                diversDetailsMap.put("lang_langue", langLangue);
//                diversDetailsMap.put("cert_langue", certLangue);
//                diversDetailsMap.put("nivs_langue", nivsLangue);
//                diversDetailsMap.put("nivi_langue", niviLangue);
//                diversDetailsMap.put("titre_autre", titreAutre);
//                diversDetailsMap.put("comment_autre", commentAutre);
//
//                // Add the map to the list of Divers details
//                diversDetailsList.add(diversDetailsMap);
//            }
//
////            // Extract values for Competence
////            XPathExpression competenceExpr = xpath.compile("/cv24:cv24/cv24:Compétences/cv24:diplome | /cv24:cv24/cv24:Compétences/cv24:certif");
////            NodeList competenceNodes = (NodeList) competenceExpr.evaluate(document, XPathConstants.NODESET);
////
////            List<Competence> competenceList = new ArrayList<>();
////
////            for (int i = 0; i < competenceNodes.getLength(); i++) {
////                Node competenceNode = competenceNodes.item(i);
////
////                // Create a new Competence object for each iteration
////                Competence competence = new Competence();
////
////                // Create lists to hold diplomas and certifDetails
////                List<Diploma> diplomes = new ArrayList<>();
////                List<CertifExpDetails> certifDetails = new ArrayList<>();
////
////                // Extract details for each Competence element
////                String dateDiploma = xpath.evaluate("cv24:date", competenceNode);
////                String institutDiploma = xpath.evaluate("cv24:institut", competenceNode);
////                String intituleDiploma = xpath.evaluate("cv24:intitule", competenceNode);
////                String niveauDiploma = xpath.evaluate("cv24:niveau | cv24:niv", competenceNode);
////                String dateDebCertif = xpath.evaluate("cv24:datedeb", competenceNode);
////                String dateFinCertif = xpath.evaluate("cv24:datefin", competenceNode);
////                String titreCertif = xpath.evaluate("cv24:titre", competenceNode);
////
////                // Create a new Diploma object and set its values
////                Diploma diploma = new Diploma();
////                diploma.setDate(dateDiploma);
////                diploma.setInstitut(institutDiploma);
////                diploma.setIntitule(intituleDiploma);
////                diploma.setNiveau(niveauDiploma);
////
////                // Create a new CertifExpDetails object and set its values
////                CertifExpDetails certif = new CertifExpDetails();
////                certif.setDatedeb(dateDebCertif);
////                certif.setDatefin(dateFinCertif);
////                certif.setTitre(titreCertif);
////
////                // Add the Diploma and CertifExpDetails to their respective lists
////                diplomes.add(diploma);
////                certifDetails.add(certif);
////
////                // Set the lists in the Competence object
////                competence.setDiplomes(diplomes);
////                competence.setCertifDetails(certifDetails);
////
////                // Add the Competence object to the list
////                competenceList.add(competence);
////            }
//
//
//
//
//
//            // Add extracted values to the HashMap
//            valuesMap.put("identity_genre", genre);
//            valuesMap.put("identity_nom", nom);
//            valuesMap.put("identity_prenom", prenom);
//            valuesMap.put("identity_tel", tel);
//            valuesMap.put("identity_mel", mel);
//
//            valuesMap.put("objectif_statut", objectifStatut);
//            valuesMap.put("objectif_value", objectifValue);
//
//            // Add the list of ExpériencePro details to the main valuesMap
//            valuesMap.put("experience_pro_details", expProDetailsList);
//
////            valuesMap.put("competence_list", competenceList);
//
//            valuesMap.put("divers_details", diversDetailsList);
//
//
//        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
//            throw new JAXBException("Error parsing XML", e);
//        }
//
//        // Now you can construct your Cv object using the valuesMap
//        Cv cv = new Cv();
//        Identity identity = new Identity();
//        String genreString = (String) valuesMap.get("identity_genre");
//        Gender genre = Gender.valueOf(genreString);
//        identity.setGenre(genre);
//        identity.setNom((String) valuesMap.get("identity_nom"));
//        identity.setPrenom((String) valuesMap.get("identity_prenom"));
//        identity.setTel((String) valuesMap.get("identity_tel"));
//        identity.setMel((String) valuesMap.get("identity_mel"));
//        cv.setIdentity(identity);
//
//        Objectif objectif = new Objectif();
//        String statutString = (String) valuesMap.get("objectif_statut");
//        ContratType statut = ContratType.valueOf(statutString);
//        objectif.setStatut(statut);
//        objectif.setValue((String) valuesMap.get("objectif_value"));
//        cv.setObjectif(objectif);
//
//
//        System.out.println("khelllllllllllllo"+cv);
//
//        return cv;
//    }


}
