package fr.univrouen.xmlProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@XmlType(name = "ExpériencePro")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ExperiencePro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiencePro", orphanRemoval = true)
    @XmlElement(name = "detail", required = true, namespace = "http://univ.fr/cv24")
    private List<CertifExpDetails> expDetails;
}
