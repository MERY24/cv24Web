package fr.univrouen.xmlProject.Services;

import fr.univrouen.xmlProject.Dtos.UserDto;
import fr.univrouen.xmlProject.Dtos.Responses.UserResponseDto;
import fr.univrouen.xmlProject.Entities.Role;
import fr.univrouen.xmlProject.Entities.User;
import fr.univrouen.xmlProject.Exceptions.UserAlreadyExistsException;
import fr.univrouen.xmlProject.Repositories.RoleRepository;
import fr.univrouen.xmlProject.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService
//        , UserDetailsService
{

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto addUser(UserDto user) {
        Optional<User> userExists = userRepo.findByMail(user.getMail());
        System.out.println("got in userserv");

        if (userExists.isPresent()) {
            try {

                throw new UserAlreadyExistsException("Un utilisateur avec cet e-mail: " + user.getMail() + " existe déjà.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        Optional<Role> userRole =  roleRepo.findById(user.getRoleId());

        User newUser = new User();
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setMail(user.getMail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(userRole.orElseThrow(() -> new RuntimeException("Role not found with ID: " + user.getRoleId()))); //that's impossible

        User savedUser = userRepo.save(newUser);
        System.out.println("shit im dying");

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setFirstname(savedUser.getFirstname());
        responseDto.setLastname(savedUser.getLastname());
        responseDto.setMail(savedUser.getMail());
        responseDto.setRoleId(savedUser.getRole().getId());

        return responseDto;
    }

    public User findUser(Long id){
        return null;
    }
    public User updateUser(){
        return null;
    }
    public User deleteUser(){
        return null;
    }


    public List<User> findAllUser() {
        return null;
    }


}
