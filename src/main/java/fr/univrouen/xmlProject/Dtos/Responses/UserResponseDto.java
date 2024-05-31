package fr.univrouen.xmlProject.Dtos.Responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String mail;
    private int roleId;
}
