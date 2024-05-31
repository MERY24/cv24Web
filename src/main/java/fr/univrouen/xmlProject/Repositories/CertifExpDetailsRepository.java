package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.CertifExpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertifExpDetailsRepository extends JpaRepository<CertifExpDetails, Integer> {
}
