package fr.univrouen.xmlProject.Dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.univrouen.xmlProject.Entities.Diploma;
import fr.univrouen.xmlProject.Entities.Objectif;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.ToString;

@ToString
@XmlRootElement
@XmlType()
@JacksonXmlRootElement(localName = "cv")
@Data
public class CvDto {

    private String id;
    private String genre;
    private String prenom;
    private String nom;
    private String statut;
    private String value;
    private String intitule;
    private String institut;
    private String date;
    private String Niveau;

}
