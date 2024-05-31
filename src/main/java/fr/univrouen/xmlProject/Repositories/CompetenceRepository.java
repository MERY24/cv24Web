package fr.univrouen.xmlProject.Repositories;

import fr.univrouen.xmlProject.Entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}
