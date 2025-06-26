package br.imd.ufrn.feirinhas_ufrn.dto.product;

public record ProductResponseDTO (
  String sellerId,

  String name,

  String description,

  Long priceInCents,
  
  String photoPath
){}
