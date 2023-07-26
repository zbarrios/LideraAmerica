package lideraamerica.pruebabackend.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestControllerErrorTemplate {

  private String issue;

  private Integer code;
}
