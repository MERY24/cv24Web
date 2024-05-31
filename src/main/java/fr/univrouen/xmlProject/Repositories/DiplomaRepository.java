package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Integer> {
}
