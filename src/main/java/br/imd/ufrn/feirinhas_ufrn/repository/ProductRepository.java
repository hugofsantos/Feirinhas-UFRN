package br.imd.ufrn.feirinhas_ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
  /**
   * Verifica de forma eficiente se um produto existe com um determinado ID E se
   * ele pertence
   * a um vendedor específico, diretamente no banco de dados.
   * Spring Data JPA irá gerar a query: "SELECT count(*) FROM products WHERE id =
   * ? AND seller_id = ?"
   * 
   * @param productId O ID do produto a ser verificado.
   * @param sellerId  O ID do vendedor (dono) a ser verificado.
   * @return true se o produto existir e pertencer ao vendedor, false caso
   *         contrário.
   */
  boolean existsByIdAndSellerId(String productId, String sellerId);

}
