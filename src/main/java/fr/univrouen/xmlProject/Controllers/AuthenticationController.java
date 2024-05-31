package fr.univrouen.xmlProject.Controllers;

import fr.univrouen.xmlProject.Dtos.AuthenticationResponse;
import fr.univrouen.xmlProject.Dtos.LoginDto;

import fr.univrouen.xmlProject.Services.auth.AuthenticationService;
import fr.univrouen.xmlProject.Services.auth.JWTService;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {


  private final AuthenticationService authenticationService;
  private JWTService jwtService;


  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@Validated @RequestBody LoginDto user){
    try {
      AuthenticationResponse responseLogin = authenticationService.authenticate(user);
      return new ResponseEntity<>(responseLogin, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}
