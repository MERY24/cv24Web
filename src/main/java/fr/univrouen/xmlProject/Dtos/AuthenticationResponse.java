package fr.univrouen.xmlProject.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.xmlProject.Dtos.Responses.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;

  private UserResponseDto user;
//private User user;


}
