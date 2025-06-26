package br.imd.ufrn.feirinhas_ufrn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.CreateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.ResponseFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.mappers.FeirinhaMapper;
import br.imd.ufrn.feirinhas_ufrn.services.FeirinhaService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/feirinhas")
@RequiredArgsConstructor
public class FeirinhaController {
  private final FeirinhaService feirinhaService;
  private final FeirinhaMapper feirinhaMapper;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseFeirinhaDTO> createFeirinha(
    @Validated @RequestBody CreateFeirinhaDTO dto
  ) throws BusinessException {
    final Feirinha feirinhaToCreate = feirinhaMapper.feirinhaFromCreateDto(dto);
    final Feirinha createdFeirinha = feirinhaService.create(feirinhaToCreate);
    final ResponseFeirinhaDTO responseDto = feirinhaMapper.responseDtoFromFeirinha(createdFeirinha);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
  
}
