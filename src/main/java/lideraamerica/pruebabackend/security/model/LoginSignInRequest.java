package lideraamerica.pruebabackend.security.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginSignInRequest {

  private String userName;
  private String password;
  private List<String> roles = new ArrayList<>();

}
