package br.imd.ufrn.feirinhas_ufrn.repository;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepo extends JpaRepository<Usuario, String> {
   UserDetails findByEmail(String Email);
}
