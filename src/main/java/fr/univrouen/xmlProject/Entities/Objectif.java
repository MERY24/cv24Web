package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import fr.univrouen.xmlProject.Entities.Enums.ContratType;
import fr.univrouen.xmlProject.util.EnumAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@XmlType(name = "Objectif", propOrder = {"value"},namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Objectif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @Column( nullable = false, length = 128)
    @XmlValue
    private String value;


    @XmlAttribute(name = "statut", required = true)
//    @Enumerated(EnumType.STRING)
//    @XmlJavaTypeAdapter(EnumAdapter.class)
    @Column(nullable = false)
    protected String statut;
}