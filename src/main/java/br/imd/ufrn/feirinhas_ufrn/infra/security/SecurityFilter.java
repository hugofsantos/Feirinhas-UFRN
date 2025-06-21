package br.imd.ufrn.feirinhas_ufrn.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.imd.ufrn.feirinhas_ufrn.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
  private final TokenService tokenService;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        final String token = this.recoverToken(request);
        
        if(token != null) {
          final String subject = tokenService.validateToken(token);

          final UserDetails user = userDetailsService.loadUserByUsername(subject);

          final var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    final var authHeader = request.getHeader("Authorization");

    if(authHeader == null) return null;

    return authHeader.replace("Bearer ", "");
  }
  
}
