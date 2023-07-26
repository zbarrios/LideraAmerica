package lideraamerica.pruebabackend.service;

import lideraamerica.pruebabackend.security.model.MainUser;
import lideraamerica.pruebabackend.security.model.User;
import lideraamerica.pruebabackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(userName).orElseThrow();
    return MainUser.build(user);
  }
}
