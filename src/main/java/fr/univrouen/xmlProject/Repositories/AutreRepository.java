package fr.univrouen.xmlProject.Repositories;

import fr.univrouen.xmlProject.Entities.Autre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutreRepository extends JpaRepository<Autre, Long> {
}
