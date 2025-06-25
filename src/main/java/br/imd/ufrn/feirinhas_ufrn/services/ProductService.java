package br.imd.ufrn.feirinhas_ufrn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.product.UpdateProductDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final UserService userService;

  @Transactional(rollbackFor = Exception.class)
  public Product create(Product product) throws BusinessException{
    final User findedSeller = userService
      .findSellerById(product.getSeller().getId())
      .orElseThrow(() -> new BusinessException("Não existe nenhum vendedor com esse ID"));

    product.setSeller(findedSeller);
    return this.productRepository.save(product);
  } 

  public Optional<Product> findById(String id) {
    // TODO
    return Optional.empty();
  }

  public List<Product> findAllProducts() {
    // TODO
    return List.of();
  }

  @Transactional(rollbackFor = Exception.class)
  public Product updateProduct(String productId, UpdateProductDTO dto) {
    // TODO
    return null;
  }

  public void deleteById(String productId) {
    // TODO
    
  }
}
