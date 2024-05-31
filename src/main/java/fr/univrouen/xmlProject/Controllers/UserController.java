package fr.univrouen.xmlProject.Controllers;


import fr.univrouen.xmlProject.Dtos.UserDto;
import fr.univrouen.xmlProject.Dtos.Responses.UserResponseDto;
import fr.univrouen.xmlProject.Entities.User;
import fr.univrouen.xmlProject.Exceptions.Errors.ValidationError;
import java.util.stream.*;
import fr.univrouen.xmlProject.Services.UserService;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService ;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok().body(userService.findAllUser());
    }

    private Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser (@Valid @RequestBody UserDto user, BindingResult bindingResult) {
        Set<ConstraintViolation<UserDto>> violations = getValidator().validate(user);
        if(!violations.isEmpty()){
            for (ConstraintViolation<UserDto> violation : violations) {
                log.error(violation.getMessage());
            }
        }

        UserResponseDto addedUser = userService.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedUser.getId())
                .toUri();

//        return ResponseEntity.created(location).body(addedUser);
        return ResponseEntity.ok().body(addedUser);

    }
}
