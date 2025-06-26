package br.imd.ufrn.feirinhas_ufrn.infra.security.product;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component("productSecurity")
@RequiredArgsConstructor
public class ProductSecurity {
  private final ProductRepository productRepository;

  public Boolean isOwner(String productId, String userId) {
    return productRepository.existsByIdAndSellerId(productId, userId);
  }
}
