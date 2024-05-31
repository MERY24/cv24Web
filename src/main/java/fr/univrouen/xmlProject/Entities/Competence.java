package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@XmlType(name = "Competence", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence", orphanRemoval = true)
    @XmlElement(name = "diplome", required = true, namespace = "http://univ.fr/cv24")
    private List<Diploma> diplomes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence", orphanRemoval = true)
    @XmlElement(name = "certif", required = true, namespace = "http://univ.fr/cv24")
    private List<CertifExpDetails> certifDetails;
}