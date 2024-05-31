package fr.univrouen.xmlProject.Dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@AllArgsConstructor
public class UserDto {


    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @Size( min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Le prénom doit contenir uniquement des lettres")
    private String firstname;

    @NotEmpty(message = "Le nom de famille ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom de famille doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Le nom de famille doit contenir uniquement des lettres")
    private String lastname;

    @NotEmpty(message = "L'adresse e-mail ne peut pas être vide")
    @Email(message = "Veuillez entrer une adresse e-mail valide")
    private String mail;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "Le mot de passe doit contenir au moins une lettre majuscule"),
            @Pattern(regexp = ".*[a-z].*", message = "Le mot de passe doit contenir au moins une lettre minuscule"),
            @Pattern(regexp = ".*\\d.*", message = "Le mot de passe doit contenir au moins un chiffre"),
            @Pattern(regexp = ".*[!@#$%^&*()-_=+\\[\\]{}|;:',.<>?/].*", message = "Le mot de passe doit contenir au moins un caractère spécial parmi !@#$%^&*()-_=+[]{}|;:',.<>?/")
    })
    private String password;

    @NotNull(message = "Un rôle doit être assigné à chaque utilisateur")
    @Positive(message = "L'ID du rôle doit être un entier positif")
    @Max(value = 6, message = "L'ID du rôle ne doit pas dépasser 2") //2 roles defined at the moment
    private int roleId;

}
