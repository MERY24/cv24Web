package fr.univrouen.cv24v1.model;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@JacksonXmlRootElement(localName = "TestCV")
@XmlAccessorType(XmlAccessType.NONE)
public class TestCV implements Serializable {
private static final long serialVersionUID = 2024L;
private static int compteur = 1;
@JacksonXmlProperty(isAttribute = true)
private Integer id;
@JacksonXmlProperty
private String nom;
@JacksonXmlProperty
private String prenom;
@XmlElement
private String date;
@JacksonXmlProperty
private String mel;
public TestCV(String nom, String prenom, String date, String mel) {
super();
this.id = compteur++;
this.nom = nom;
this.prenom = prenom;
this.date = date;
this.mel = mel;
}


  public TestCV() {
    }

   @Override
   public String toString() {
    return ("CV (" + id + ") [" + nom + " " + prenom + " ,Date nais=" + date + " ,mel=" + mel);
      }
}
