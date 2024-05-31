package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.ExperiencePro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceProRepository extends JpaRepository<ExperiencePro, Integer> {
}
