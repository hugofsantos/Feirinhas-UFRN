package br.imd.ufrn.feirinhas_ufrn.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
    @Size(min = 1, message = "O nome não pode estar vazio")
    String fullname,

    @Pattern(
        regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$",
        message = "O campo telefone deve estar no formato (00) 00000-0000."
    ) 
    String whatsapp
) {}
