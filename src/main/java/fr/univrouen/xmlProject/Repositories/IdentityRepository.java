package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, Integer> {
}
