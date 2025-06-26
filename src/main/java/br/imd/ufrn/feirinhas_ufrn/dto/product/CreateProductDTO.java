package br.imd.ufrn.feirinhas_ufrn.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateProductDTO (
  @NotBlank(message = "O ID do vendedor não pode estar vazio")
  String sellerId,

  @NotBlank(message = "O nome do produto não pode estar vazio")
  String name,

  String description,

  @Positive(message = "O preço do produto não pode ser negativo!")
  Long priceInCents 
) {}
