package br.imd.ufrn.feirinhas_ufrn.infra.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthenticated implements UserDetails{
  private final User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      if(this.user.getRole() == UserRole.ADMIN){
          return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
      }else {
          return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
  }

  public String getId() {
    return this.user.getId();
  }

  @Override
  public String getPassword() {
      return this.user.getPassword();
  }

  @Override
  public String getUsername() {
      return this.user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }  
}
