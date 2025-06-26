package br.imd.ufrn.feirinhas_ufrn.dto.product;

public record ProductInfoResponseDTO (
  ProductSellerInfoResponseDTO seller,
  String id,
  String name,
  String description,
  Long priceInCents,
  String photoPath  
){}
