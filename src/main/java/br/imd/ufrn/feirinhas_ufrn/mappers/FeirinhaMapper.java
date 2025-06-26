package br.imd.ufrn.feirinhas_ufrn.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.CreateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.FeirinhaInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.ResponseFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeirinhaMapper {
  private final UserMapper userMapper;

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

  public FeirinhaInfoResponseDTO infoResponseFromFeirinha(Feirinha feirinha) {
    if(feirinha == null) return null;

    final List<UserInfoResponseDTO> sellers = feirinha
      .getSellers()
      .stream()
      .map(userMapper::userInfoResponseDtoFromUser)
      .collect(Collectors.toList());

    final FeirinhaInfoResponseDTO dto = new FeirinhaInfoResponseDTO(
      feirinha.getId(),
      feirinha.getName(),
      feirinha.getDescription(),
      feirinha.getLocation(),
      feirinha.getBeginTime(),
      feirinha.getEndTime(),
      sellers
    );

    return dto;
  }
}
