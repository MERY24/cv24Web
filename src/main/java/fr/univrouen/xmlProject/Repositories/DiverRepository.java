package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.Divers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiverRepository extends JpaRepository<Divers, Integer> {
}
