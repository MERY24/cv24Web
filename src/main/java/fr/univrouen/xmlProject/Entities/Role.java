package fr.univrouen.xmlProject.Entities;

import fr.univrouen.xmlProject.Entities.Enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Data
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
