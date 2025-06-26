package br.imd.ufrn.feirinhas_ufrn.dto.feirinha;

import jakarta.validation.constraints.NotNull;

public record BindDTO (
  @NotNull(message = "O ID do vendedor não pode estar vazio")
  String sellerId,

  @NotNull(message = "O ID da feirinha não pode estar vazio")
  String feirinhaId
) {}
