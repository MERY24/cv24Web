package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.univrouen.xmlProject.Entities.Enums.QualifLvlType;
import fr.univrouen.xmlProject.util.EnumAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Diplome", propOrder = {"intitule","date","institut"},namespace = "http://univ.fr/cv24")
@ToString(exclude = {"competence"})
@Data
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @XmlElement(name = "date", required = true, namespace = "http://univ.fr/cv24")
    private String date;

    @Column(length = 32)
    @XmlElement(name = "institut", namespace = "http://univ.fr/cv24")
    private String institut;

    @Column(nullable = false, length = 32)
    @XmlElement(name = "intitule", required = true, namespace = "http://univ.fr/cv24")
    private String intitule;

    @XmlAttribute(name = "niveau", required = true)
    @Column(nullable = false)
    private String niveau;

    @ManyToOne
    @JoinColumn(name = "diplome")
    @XmlTransient
    private Competence competence;

}


