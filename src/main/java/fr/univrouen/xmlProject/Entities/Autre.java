package fr.univrouen.xmlProject.Entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@XmlType(name = "Autre", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString(exclude = "diver")
@Data
public class Autre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id", nullable = false)
    private Long id;

    @XmlAttribute(name = "titre", required = true)
    @Column(nullable = false, length = 32)
    private String titre;

    @Column(length = 128)
    @XmlAttribute(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "diver_id")
    @XmlTransient
    private Divers diver;
}
