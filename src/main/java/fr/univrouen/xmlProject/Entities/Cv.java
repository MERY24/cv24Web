package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "cv24")
@XmlRootElement(name = "cv24"
        , namespace = "http://univ.fr/cv24"
)
@XmlType(name = "cv24",namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id"})
public class Cv {

    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identity_id",referencedColumnName = "id")
    @XmlElement(name = "Identité", namespace = "http://univ.fr/cv24")
    private Identity identity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objectif_id",referencedColumnName = "id")
    @XmlElement(name = "Objectif", namespace = "http://univ.fr/cv24")
    private Objectif objectif;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_pro_id",referencedColumnName = "id")
    @XmlElement(name = "ExpériencePro", namespace = "http://univ.fr/cv24")
    private ExperiencePro experiencePro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competence_id",referencedColumnName = "id")
    @XmlElement(name = "Compétences", namespace = "http://univ.fr/cv24")
    private Competence competence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "divers_id",referencedColumnName = "id")
    @XmlElement(name = "Divers", namespace = "http://univ.fr/cv24")
    private Divers divers;

}