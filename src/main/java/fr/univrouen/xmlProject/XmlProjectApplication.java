package fr.univrouen.xmlProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univrouen.xmlProject.Entities.*;
import fr.univrouen.xmlProject.Entities.Enums.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class XmlProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlProjectApplication.class, args);
//		XmlMapper xmlMapper = new XmlMapper();
//
//		// Create the CvEntity instance and set all necessary fields
//		Cv cvEntity = new Cv();
//		// ... set fields ...
//
//		try {
//			// Serialize to XML
//			String xml = xmlMapper.writeValueAsString(cvEntity);
//
//			// Write XML to file
//			File file = new File("cv_entity.xml");
//			xmlMapper.writeValue(file, cvEntity);
//
//			System.out.println("XML written to file: " + file.getAbsolutePath());
//		} catch (JsonProcessingException e) {
//			e.printStackTrace(); // or handle the exception as needed
//		} catch (IOException e) {
//			e.printStackTrace(); // or handle the exception as needed
//		}


//		try {
//			Identity identity = new Identity();
//			identity.setId(1L);
//			identity.setGenre("M");
//			identity.setNom("Doe");
//			identity.setPrenom("John");
//			identity.setTel("1234567890");
//			identity.setMel("john.doe@example.com");
//
//			// Création d'un objet Objectif
//			Objectif objectif = new Objectif();
//			objectif.setId(1L);
//			objectif.setValue("Développeur Java expérimenté");
//			objectif.setStatut("STAGE");
//
//			// Création d'un objet ExperiencePro
//			CertifExpDetails expDetails = new CertifExpDetails();
//			expDetails.setId(1L);
//			expDetails.setDatedeb(new Date().toString());
//			expDetails.setTitre("Développeur Java");
//			List<CertifExpDetails> expDetailsList = new ArrayList<>();
//			expDetailsList.add(expDetails);
//			ExperiencePro experiencePro = new ExperiencePro();
//			experiencePro.setId(1L);
//			experiencePro.setExpDetails(expDetailsList);
//
//			// Création d'un objet Competence
//			List<Diploma> diplomes = new ArrayList<>();
//			Diploma diploma = new Diploma();
//			diploma.setId(1L);
//			diploma.setDate(new Date().toString());
//			diploma.setInstitut("Université XYZ");
//			diploma.setIntitule("Licence Informatique");
//			diploma.setNiveau("BAC_3");
//			diplomes.add(diploma);
//			Competence competence = new Competence();
//			competence.setId(1L);
//			competence.setDiplomes(diplomes);
//
//			// Création d'un objet Diver
//			LangueVivante langueVivante = new LangueVivante();
//			langueVivante.setId(1L);
//			langueVivante.setLang("EN");
//			langueVivante.setCert("TOEIC");
//			langueVivante.setNivs("C1");
//			langueVivante.setNivi(850);
//			List<LangueVivante> langueVivantes = new ArrayList<>();
//			langueVivantes.add(langueVivante);
//			Autre autre = new Autre();
//			autre.setId(1L);
//			autre.setTitre("Autre compétence");
//			autre.setComment("Commentaire sur autre compétence");
//			List<Autre> autres = new ArrayList<>();
//			autres.add(autre);
//			Divers divers = new Divers();
//			divers.setId(1L);
//			divers.setLangueVivantes(langueVivantes);
//			divers.setAutres(autres);
//
//			// Création d'un objet Cv
//			Cv cv = new Cv();
//			cv.setId(1L);
//			cv.setIdentity(identity);
//			cv.setObjectif(objectif);
//			cv.setExperiencePro(experiencePro);
//			cv.setCompetence(competence);
//			cv.setDivers(divers);
//
//			// Créer le contexte JAXB
//			JAXBContext context = JAXBContext.newInstance(Cv.class);
//
//			// Créer le marshaller
//			Marshaller marshaller = context.createMarshaller();
//
//			// Définir les options de formatage (optionnel)
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//			// Marshalling de l'entité vers le fichier XML
//			marshaller.marshal(cv, new File("cv.xml"));
//
//			System.out.println("Fichier XML généré avec succès.");
//			System.out.println("Chemin du fichier XML : " + new File("cv.xml").getAbsolutePath());
//
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
	}
}


