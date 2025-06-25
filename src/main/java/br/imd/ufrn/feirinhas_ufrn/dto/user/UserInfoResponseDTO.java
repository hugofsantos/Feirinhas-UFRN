package br.imd.ufrn.feirinhas_ufrn.dto.user;

import java.util.List;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;

public record UserInfoResponseDTO (
  String id,
  String fullname,
  String email,
  String whatsapp,
  String role,
  List<Product> products
){}
