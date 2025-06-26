package br.imd.ufrn.feirinhas_ufrn.mappers;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.CreateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.ResponseFeirinhaDTO;

@Component
public class FeirinhaMapper {
  public Feirinha feirinhaFromCreateDto(CreateFeirinhaDTO dto) {
    if(dto == null) return null;

    final Feirinha feirinha = new Feirinha();
    feirinha.setName(dto.name());
    feirinha.setLocation(dto.location());
    feirinha.setDescription(dto.description());
    feirinha.setBeginTime(dto.beginTime());
    feirinha.setEndTime(dto.endTime());

    return feirinha;
  }

  public ResponseFeirinhaDTO responseDtoFromFeirinha(Feirinha feirinha) {
    if(feirinha == null) return null;

    final ResponseFeirinhaDTO dto = new ResponseFeirinhaDTO(
      feirinha.getId(),
      feirinha.getName(),
      feirinha.getDescription(),
      feirinha.getLocation(),
      feirinha.getBeginTime(),
      feirinha.getEndTime()
    );

    return dto;
  }
}
