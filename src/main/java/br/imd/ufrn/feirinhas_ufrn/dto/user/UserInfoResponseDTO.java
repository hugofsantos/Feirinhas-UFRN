package br.imd.ufrn.feirinhas_ufrn.dto.user;

import java.util.List;

import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductResponseDTO;

public record UserInfoResponseDTO (
  String id,
  String fullname,
  String email,
  String whatsapp,
  String role,
  List<ProductResponseDTO> products
){}
