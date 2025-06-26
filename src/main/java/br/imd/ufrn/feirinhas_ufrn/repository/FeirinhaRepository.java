package br.imd.ufrn.feirinhas_ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;

@Repository
public interface FeirinhaRepository extends JpaRepository<Feirinha, String> {
  
}
