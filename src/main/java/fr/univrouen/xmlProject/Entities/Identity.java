package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.univrouen.xmlProject.Entities.Enums.Gender;
import fr.univrouen.xmlProject.util.EnumAdapter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@XmlType(name = "Identité")
@Table(name = "identity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({ "genre", "nom", "prenom", "tel", "mel" })
@Data
public class Identity {
    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "genre", required = true, namespace = "http://univ.fr/cv24")
//    @Enumerated(EnumType.STRING)
//    @XmlJavaTypeAdapter(EnumAdapter.class)
    @Column(nullable = false)
    private String genre;

    @XmlElement(name = "nom", required = true, namespace = "http://univ.fr/cv24")
    @Pattern(regexp = "^[A-Z]+$")
    @Column(nullable = false, length = 32)
    private String nom;

    @XmlElement(name = "prenom", required = true, namespace = "http://univ.fr/cv24")
    @Column(nullable = false, length = 32)
    private String prenom;

    @XmlElement(name = "tel", namespace = "http://univ.fr/cv24")
    @Column
    private String tel;

    @XmlElement(name = "mel", namespace = "http://univ.fr/cv24")
    @Column
    private String mel;
}