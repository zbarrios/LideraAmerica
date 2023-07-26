package lideraamerica.pruebabackend.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lideraamerica.pruebabackend.security.enums.RoleName;
import lideraamerica.pruebabackend.security.jwt.JwtProvider;
import lideraamerica.pruebabackend.security.model.JwtDto;
import lideraamerica.pruebabackend.security.model.LoginSignInRequest;
import lideraamerica.pruebabackend.security.model.Role;
import lideraamerica.pruebabackend.security.model.User;
import lideraamerica.pruebabackend.repo.RoleRepository;
import lideraamerica.pruebabackend.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      RoleRepository roleRepository, AuthenticationManager authenticationManager,
      JwtProvider jwtProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
  }

  public Optional<User> getUserByUserName(String userName){
    return userRepository.findByUserName(userName);
  }

  public void newUser(LoginSignInRequest loginOrNewUserRequest) {
    Set<Role> roleModels = new HashSet<>();
    User user = User.builder().userName(loginOrNewUserRequest.getUserName())
        .password(passwordEncoder.encode(loginOrNewUserRequest.getPassword()))
        .roles(roleModels).build();
    roleModels.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow());
    if (loginOrNewUserRequest.getRoles().contains("admin"))
      roleModels.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow());
    user.setRoles(roleModels);
    userRepository.save(user);
  }

  public JwtDto login(LoginSignInRequest loginUser) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateToken(authentication);
    return new JwtDto(jwt);
  }

}
