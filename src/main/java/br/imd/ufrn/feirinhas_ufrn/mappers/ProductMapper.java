package br.imd.ufrn.feirinhas_ufrn.mappers;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.product.CreateProductDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductResponseDTO;

@Component
public class ProductMapper {
  public Product productByCreateProductDto(CreateProductDTO dto) {
    if(dto == null) return null;
    
    final Product product = new Product();
    
    // Atribui o vendedor
    final User seller = new User();
    seller.setId(dto.sellerId());
    product.setSeller(seller);

    // Atribui os outros atributos
    product.setName(dto.name());
    product.setDescription(dto.description());
    product.setPriceInCents(dto.priceInCents());

    return product;
  }

  public ProductResponseDTO responseDtoFromProduct(Product product) {
    if (product == null) return null;

    final ProductResponseDTO dto = new ProductResponseDTO(
      product.getSeller().getId(),
      product.getName(),
      product.getDescription(),
      product.getPriceInCents(),
      product.getPhotoPath()
    );

    return dto;
  }
}
