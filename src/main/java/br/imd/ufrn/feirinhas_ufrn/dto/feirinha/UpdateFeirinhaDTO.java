package br.imd.ufrn.feirinhas_ufrn.dto.feirinha;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.Size;

public record UpdateFeirinhaDTO (
  @Size(min = 1, message = "O nome não pode estar vazio")
  String name,

  String description,

  @Size(min = 1, message = "A localização não pode estar vazia")
  String location,

  OffsetDateTime beginTime,

  OffsetDateTime endTime
) {}
