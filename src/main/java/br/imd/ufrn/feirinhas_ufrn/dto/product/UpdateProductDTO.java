package br.imd.ufrn.feirinhas_ufrn.dto.product;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductDTO (
  @Size(min = 1, message = "O nome do produto não pode estar vazio")
  String name,

  String description,

  @Positive(message = "O preço do produto não pode ser negativo!")
  Long priceInCents 
){}
