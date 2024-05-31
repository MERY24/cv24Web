package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.Role;
import fr.univrouen.xmlProject.Entities.Enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findById (int id);
    Optional<Role> findByName (RoleEnum name);
}

