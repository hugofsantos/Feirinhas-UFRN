package br.imd.ufrn.feirinhas_ufrn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.imd.ufrn.feirinhas_ufrn.components.PhotoStorageComponent;
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
  private final PhotoStorageComponent photoStorageComponent;

  @Transactional(rollbackFor = Exception.class)
  public Product create(Product product, MultipartFile productImg) throws BusinessException{
    final User findedSeller = userService
      .findSellerById(product.getSeller().getId())
      .orElseThrow(() -> new BusinessException("Não existe nenhum vendedor com esse ID"));

    if(productImg != null && !productImg.isEmpty()) {
      final String imgUrl = photoStorageComponent.storePhoto(productImg);
      product.setPhotoPath(imgUrl);
    }

    product.setSeller(findedSeller);
    return this.productRepository.save(product);
  } 

  public Optional<Product> findById(String id) {
    return this.productRepository.findById(id);
  }

  public List<Product> findAllProducts() {
    return this.productRepository.findAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public Product updateProduct(String productId, UpdateProductDTO dto) throws BusinessException{
    final Product productToUpdate = this.productRepository
      .findById(productId)
      .orElseThrow(() -> new BusinessException("Não existe nenhum produto com esse ID"));
    
    if(dto.name() != null && !dto.name().isBlank())
      productToUpdate.setName(dto.name());
    if(dto.description() != null)
      productToUpdate.setDescription(dto.description());
    if(dto.priceInCents() != null)
      productToUpdate.setPriceInCents(dto.priceInCents());
    
    return this.productRepository.save(productToUpdate);
  }

  public void deleteById(String productId) {
    // TODO
    
  }
}
