package fr.univrouen.xmlProject.Repositories;


import fr.univrouen.xmlProject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {
    Optional<User> findById (long id);
    Optional<User> findByMail (String mail);




}
