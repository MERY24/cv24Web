package fr.univrouen.xmlProject.Dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginDto {

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
}
