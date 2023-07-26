package lideraamerica.pruebabackend.controller;

import lideraamerica.pruebabackend.controller.responses.RestControllerErrorTemplate;
import lideraamerica.pruebabackend.security.model.LoginSignInRequest;
import lideraamerica.pruebabackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController {

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<Object> newUser(@RequestBody LoginSignInRequest loginOrNewUserRequest) {
    userService.newUser(loginOrNewUserRequest);
    return new ResponseEntity<>(
        new RestControllerErrorTemplate("Successfully Sign In",HttpStatus.OK.value()), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody LoginSignInRequest loginUser) {
    return new ResponseEntity<>(userService.login(loginUser), HttpStatus.OK);
  }
}
