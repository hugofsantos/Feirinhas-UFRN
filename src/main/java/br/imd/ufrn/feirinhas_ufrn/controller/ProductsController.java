package br.imd.ufrn.feirinhas_ufrn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import br.imd.ufrn.feirinhas_ufrn.dto.product.CreateProductDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.mappers.ProductMapper;
import br.imd.ufrn.feirinhas_ufrn.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
  private final ProductService productService;
  private final ProductMapper productMapper;

  @PreAuthorize("hasRole('ADMIN') or #dto.sellerId == authentication.principal.id")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductResponseDTO> createProduct(
    @Validated @RequestPart(name = "product") CreateProductDTO dto,
    @RequestPart(required = false) MultipartFile productImg
  ) throws BusinessException {
    final Product productToCreate = productMapper.productByCreateProductDto(dto);
    final Product createdProduct = this.productService.create(productToCreate, productImg);

    final ProductResponseDTO responseDto = productMapper.responseDtoFromProduct(createdProduct);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductInfoResponseDTO> findProductById(@PathVariable(required = true) String productId) {
    return productService
      .findById(productId)
      .map(product -> {
        final ProductInfoResponseDTO dto = productMapper.productInfoByProduct(product);

        return ResponseEntity.ok(dto);
      })
      .orElse(ResponseEntity.notFound().build());
  }
  
  
}
