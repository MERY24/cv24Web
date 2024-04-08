package fr.univrouen.cv24v1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class Fichier {
	
	private Resource resource;
	
	
	//statically

	    public Fichier() {
	        // Initialise resource avec le fichier smallCV.xml
	        this.resource = new DefaultResourceLoader().getResource("smallCV.xml");
	    }

	    public String loadFileXML() {
	        try {
	            // Créer un gestionnaire de flux d'entrée pour lire le fichier
	            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
	            StringBuilder content = new StringBuilder();
	            String line;
	            // Lire chaque ligne du fichier
	            while ((line = reader.readLine()) != null) {
	                content.append(line).append("\n");
	            }
	            reader.close();
	            // Retourner le contenu du fichier au format String
	            return content.toString();
	        } catch (IOException e) {
	            // En cas d'erreur, retourner un message d'erreur au format String
	            return "Erreur lors de la lecture du fichier : " + e.getMessage();
	        }
	    }
	    
	    
	    
	    
	    //dynamically 
	    public void loadFileXML(Resource resource) {
	        this.resource = resource;
	        try {
	            // Créer un gestionnaire de flux d'entrée pour lire le fichier
	            BufferedReader reader = new BufferedReader(new InputStreamReader(this.resource.getInputStream()));
	            StringBuilder content = new StringBuilder();
	            String line;
	            // Lire chaque ligne du fichier
	            while ((line = reader.readLine()) != null) {
	                content.append(line).append("\n");
	            }
	            reader.close();
	            // Retourner le contenu du fichier au format String
	            System.out.println("Contenu du fichier : " + content.toString());
	        } catch (IOException e) {
	            // En cas d'erreur, afficher un message d'erreur
	            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
	        }
	    }
}
