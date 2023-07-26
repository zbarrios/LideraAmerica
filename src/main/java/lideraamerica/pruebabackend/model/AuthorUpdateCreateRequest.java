package lideraamerica.pruebabackend.model;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateCreateRequest {

  @NotNull
  private String authorName;

  private List<Integer> bookIds = new ArrayList<>();
}
