package br.imd.ufrn.feirinhas_ufrn.dto.user;

public record UserResponseDTO (
  String id,
  String fullname,
  String email,
  String whatsapp,
  String role
) { }
