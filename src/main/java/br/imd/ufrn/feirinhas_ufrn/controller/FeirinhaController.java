package br.imd.ufrn.feirinhas_ufrn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.CreateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.FeirinhaInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.ResponseFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.UpdateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.mappers.FeirinhaMapper;
import br.imd.ufrn.feirinhas_ufrn.services.FeirinhaService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

  @GetMapping()
  public ResponseEntity<List<ResponseFeirinhaDTO>> findAllFeirinhas() {
      final List<ResponseFeirinhaDTO> responseFeirinhas = this.feirinhaService
        .findAllFeirinhas()
        .stream()
        .map(feirinhaMapper::responseDtoFromFeirinha)
        .collect(Collectors.toList());

      return ResponseEntity.ok(responseFeirinhas);
  }

  @GetMapping("/{feirinhaId}")
  public ResponseEntity<FeirinhaInfoResponseDTO> getFeirinhaById(
    @PathVariable(required = true) String feirinhaId
  ) {
    return this.feirinhaService
      .findById(feirinhaId)
      .map(feirinha -> {
        final FeirinhaInfoResponseDTO responseDto = feirinhaMapper.infoResponseFromFeirinha(feirinha);

        return ResponseEntity.ok(responseDto);
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("/{feirinhaId}")
  public ResponseEntity<ResponseFeirinhaDTO> updateFeirinha(
    @PathVariable(required = true) String feirinhaId,
    @Validated @RequestBody UpdateFeirinhaDTO updateDTO
  ) throws BusinessException {
    final Feirinha updatedFeirinha = this.feirinhaService.updateFeirinha(feirinhaId, updateDTO);
    final ResponseFeirinhaDTO responseDTO = this.feirinhaMapper.responseDtoFromFeirinha(updatedFeirinha);

    return ResponseEntity.ok(responseDTO);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{feirinhaId}")
  public ResponseEntity<Void> deleteFeirinha(
    @PathVariable(required = true) String feirinhaId
  ) throws BusinessException {
    this.feirinhaService.deleteById(feirinhaId);

    return ResponseEntity.noContent().build();
  }
  
}
