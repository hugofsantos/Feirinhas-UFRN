package br.imd.ufrn.feirinhas_ufrn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.UpdateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.repository.FeirinhaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeirinhaService {
  private final FeirinhaRepository repository;

  @Transactional(rollbackFor = Exception.class)
  public Feirinha create(Feirinha feirinha) throws BusinessException {
    this.verifyDatetime(feirinha);

    return this.repository.save(feirinha);
  }

  public Optional<Feirinha> findById(String feirinhaId) {
    return this.repository.findById(feirinhaId);
  }

  public List<Feirinha> findAllFeirinhas() {
    return this.repository.findAll();
  }

  public Feirinha updateFeirinha(String feirinhaId, UpdateFeirinhaDTO dto) throws BusinessException {
    // TODO

    this.verifyDatetime(null);
    return null;
  }

  public void verifyDatetime(Feirinha feirinha) throws BusinessException{
    if(feirinha.getBeginTime().isAfter(feirinha.getEndTime()))
      throw new BusinessException("A data de início do evento não pode ser depois da data de término");
  }


}
