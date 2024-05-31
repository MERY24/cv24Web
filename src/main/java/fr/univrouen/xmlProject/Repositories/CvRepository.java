package fr.univrouen.xmlProject.Repositories;

import fr.univrouen.xmlProject.Entities.Cv;
import fr.univrouen.xmlProject.Entities.Enums.Gender;
import jakarta.persistence.GenerationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv,Long> {
    boolean existsByIdentity_GenreAndIdentity_NomAndIdentity_PrenomAndIdentity_Tel(
            String genre, String nom, String prenom, String tel);
}
