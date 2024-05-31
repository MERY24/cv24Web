package fr.univrouen.xmlProject.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://univ.fr/cv24")
@ToString(exclude = {"experiencePro","competence"})
@Data
public class CertifExpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @XmlElement(name = "datedeb", required = true, namespace = "http://univ.fr/cv24")
    private String datedeb;

    @Column
    @XmlElement(name = "datefin", namespace = "http://univ.fr/cv24")
    private String datefin;

    @Column(nullable = false, length = 32)
    @XmlElement(name = "titre", required = true, namespace = "http://univ.fr/cv24")
    private String titre;

    @ManyToOne
    @JoinColumn(name = "exp_pro_id")
    @XmlTransient
    private ExperiencePro experiencePro;

    @ManyToOne
    @JoinColumn(name = "certifDetails")
    @XmlTransient
    private Competence competence;
}
