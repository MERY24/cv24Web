package fr.univrouen.xmlProject.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@XmlType(name = "Divers", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Divers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @OneToMany(mappedBy = "diver", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, max = 5)
    @XmlElement(name = "lv", required = true, namespace = "http://univ.fr/cv24")
    private List<LangueVivante> langueVivantes;

    @OneToMany(mappedBy = "diver", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(max = 3)
    @XmlElement(name = "autre", namespace = "http://univ.fr/cv24")
    private List<Autre> autres;
}
