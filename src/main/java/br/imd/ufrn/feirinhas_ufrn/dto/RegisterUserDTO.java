package br.imd.ufrn.feirinhas_ufrn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDTO(
  @NotBlank(message = "O nome do usuário não pode estar vazio")
  String fullname,
  
  @NotBlank(message = "O email não pode estar vazio")
  @Email(message = "Email em formato inválido")
  String email,

  @NotBlank(message = "A senha não pode estar vazia")
  String password, 
  
  @NotBlank(message = "O campo whatsapp não pode ser vazio.") 
  @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "O campo telefone deve estar no formato (00) 00000-0000.")
  String whatsapp
  ) {}
