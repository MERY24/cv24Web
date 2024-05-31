package fr.univrouen.xmlProject.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@XmlType(name = "LangueVivante", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString(exclude = "diver")
public class LangueVivante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id", nullable = false)
    private Long id;

    @XmlAttribute(name = "lang", required = true)
    @Pattern(regexp = "[a-zA-Z]{2}", message = "lang must be exactly two letters")
    @Column(name = "lang", nullable = false, length = 2)
    private String lang;

    @XmlAttribute(name = "cert", required = true)
    @Column(nullable = false)
    private String cert;

    @XmlAttribute(name = "nivs")
    @Column
    private String nivs;

    @XmlAttribute(name = "nivi")
    @Column
    @Min(10)
    @Max(990)
    private Integer nivi;

    @ManyToOne
    @JoinColumn(name = "diver_id")
    @XmlTransient
    private Divers diver;
}
