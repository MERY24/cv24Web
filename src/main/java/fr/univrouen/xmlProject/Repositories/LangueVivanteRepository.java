package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.LangueVivante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangueVivanteRepository extends JpaRepository<LangueVivante, Integer> {
}
