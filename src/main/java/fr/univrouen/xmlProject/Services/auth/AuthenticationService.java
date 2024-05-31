package fr.univrouen.xmlProject.Services.auth;



import fr.univrouen.xmlProject.Dtos.AuthenticationResponse;
import fr.univrouen.xmlProject.Dtos.LoginDto;
import fr.univrouen.xmlProject.Dtos.Responses.UserResponseDto;
import fr.univrouen.xmlProject.Entities.User;
import fr.univrouen.xmlProject.Repositories.RoleRepository;
import fr.univrouen.xmlProject.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository ;
    private final JWTService jwtService;




//    public UserResponseDto signup(UserDto userDto) {
//        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
//        if (optionalRole.isEmpty()) {
//            return null;
//        }
//        User user = new User() ;
//        user.setMail(userDto.getMail());
//        user.setLastname(userDto.getLastname());
//        user.setFirstname(userDto.getFirstname());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setRole(optionalRole.get());
//
//        User registeredUser = userRepository.save(user);
//        UserResponseDto responseUser = new UserResponseDto(
//                registeredUser.getId(),
//                user.getFirstname(),
//                user.getLastname(),
//                user.getMail(),
//                user.getRole().getId()
//        );
//        return responseUser;
//    }
    public AuthenticationResponse authenticate(LoginDto loginInfo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginInfo.getMail(),
                            loginInfo.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User authUser = userRepository.findByMail(userDetails.getUsername()).orElseThrow();

            UserResponseDto responseUser = new UserResponseDto(
                    authUser.getId(),
                    authUser.getFirstname(),
                    authUser.getLastname(),
                    authUser.getMail(),
                    authUser.getRole().getId()
            );
            String jwtToken = jwtService.generateToken(authUser);
            System.out.println("khello from auth serv");
            return new AuthenticationResponse(jwtToken, responseUser);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }
}
