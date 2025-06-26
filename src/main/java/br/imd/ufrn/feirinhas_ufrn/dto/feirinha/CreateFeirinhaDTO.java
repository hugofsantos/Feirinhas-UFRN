package br.imd.ufrn.feirinhas_ufrn.dto.feirinha;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFeirinhaDTO (
  @NotBlank(message = "O nome da feirinha não pode estar vazia")
  String name,

  String description,

  @NotBlank(message = "A localização da feirinha não pode estar vazia")
  String location,

  @NotNull(message = "A data de início da feirinha não pode estar vazia")
  OffsetDateTime beginTime,

  @NotNull(message = "A data de término da feirinha não pode estar vazia")
  OffsetDateTime endTime
) {}
