package br.imd.ufrn.feirinhas_ufrn.dto.feirinha;

import java.time.OffsetDateTime;
import java.util.List;

import br.imd.ufrn.feirinhas_ufrn.dto.user.UserInfoResponseDTO;

public record FeirinhaInfoResponseDTO (
  String id,
  String name,
  String description,
  String location,
  OffsetDateTime beginTime,
  OffsetDateTime endTime,
  List<UserInfoResponseDTO> sellers // Usuário com os seus produtos  
){}
