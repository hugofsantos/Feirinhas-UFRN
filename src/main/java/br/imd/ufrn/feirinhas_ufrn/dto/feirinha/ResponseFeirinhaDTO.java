package br.imd.ufrn.feirinhas_ufrn.dto.feirinha;

import java.time.OffsetDateTime;

public record ResponseFeirinhaDTO (
    String id,
    String name,
    String description,
    String location,
    OffsetDateTime beginTime,
    OffsetDateTime endTime
){}
