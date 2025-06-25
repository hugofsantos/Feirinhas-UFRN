package br.imd.ufrn.feirinhas_ufrn.repository;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String Email);

    /**
     * 1. Busca todos os usuários que possuem uma role específica.
     * O Spring Data JPA cria a consulta automaticamente a partir do nome do método.
     * JPQL: "SELECT u FROM User u WHERE u.role = ?1"
     */
    List<User> findByRole(UserRole role);

    /**
     * 2. Busca um usuário pelo seu ID, mas apenas se ele tiver uma role específica.
     * Retorna um Optional, que é uma boa prática para lidar com resultados que podem não existir.
     * JPQL: "SELECT u FROM User u WHERE u.id = ?1 AND u.role = ?2"
     */
    Optional<User> findByIdAndRole(String id, UserRole role);

    /**
     * Verifica de forma otimizada se já existe um usuário com o e-mail fornecido.
     * Retorna 'true' se o e-mail existir, 'false' caso contrário.
     * * JPQL gerado (ou SQL equivalente): "SELECT count(u) FROM User u WHERE
     * u.email = ?1"
     * Esta consulta é muito mais rápida do que buscar a entidade inteira.
     *
     * @param email O e-mail a ser verificado.
     * @return boolean
     */
    boolean existsByEmail(String email);    

}
