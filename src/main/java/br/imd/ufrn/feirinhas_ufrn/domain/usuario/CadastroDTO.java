package br.imd.ufrn.feirinhas_ufrn.domain.usuario;

public record CadastroDTO(String nome, String email, String senha, String whatsapp, UserRole role) {
}
