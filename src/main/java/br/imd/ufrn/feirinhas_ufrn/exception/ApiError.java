package br.imd.ufrn.feirinhas_ufrn.exception;

import java.time.OffsetDateTime;

public record ApiError (
  String requestPath,
  int statusCode,
  String errorMessage,
  OffsetDateTime timestamp
){}
