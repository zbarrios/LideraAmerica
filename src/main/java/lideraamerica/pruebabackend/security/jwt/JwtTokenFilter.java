package lideraamerica.pruebabackend.security.jwt;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lideraamerica.pruebabackend.service.UsersDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final Logger loggerJwtTok = LoggerFactory.getLogger(JwtTokenFilter.class);

  @Autowired
  JwtProvider jwtProvider;
  @Autowired
  UsersDetailServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = getToken(request);
      if (token != null && jwtProvider.validateToken(token)) {
        String email = jwtProvider.getUserEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      loggerJwtTok.error("Fail method doFilterInternal " + e.getMessage());
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request){
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer")) {
      return header.replace("Bearer ", "");
    }
    return null;
  }

}
