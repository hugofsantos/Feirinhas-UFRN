package br.imd.ufrn.feirinhas_ufrn.repository;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
   Optional<User> findByEmail(String Email);
}
