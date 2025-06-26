package br.imd.ufrn.feirinhas_ufrn.mappers;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.product.CreateProductDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductSellerInfoResponseDTO;

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
      product.getId(),      
      product.getName(),
      product.getDescription(),
      product.getPriceInCents(),
      product.getPhotoPath()
    );

    return dto;
  }

  public ProductInfoResponseDTO productInfoByProduct(Product product) {
    if (product == null) return null;

    // Cria o ProductSellerInfoResponseDTO 
    final ProductSellerInfoResponseDTO productSellerDto = new ProductSellerInfoResponseDTO(
      product.getSeller().getId(),
      product.getSeller().getFullname(),
      product.getSeller().getEmail(),
      product.getSeller().getWhatsapp()
    );

    final ProductInfoResponseDTO dto = new ProductInfoResponseDTO(
      productSellerDto, 
      product.getId(),
      product.getName(),
      product.getDescription(),
      product.getPriceInCents(),
      product.getPhotoPath()
    );

    return dto;
  }
}
