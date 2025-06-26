package br.imd.ufrn.feirinhas_ufrn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.imd.ufrn.feirinhas_ufrn.domain.feirinha.Feirinha;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.feirinha.UpdateFeirinhaDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.repository.FeirinhaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeirinhaService {
  private final FeirinhaRepository repository;
  private final UserService userService;

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

  @Transactional(rollbackFor = Exception.class)
  public Feirinha updateFeirinha(String feirinhaId, UpdateFeirinhaDTO dto) throws BusinessException {
    final Feirinha feirinhaToUpdate = this.repository
      .findById(feirinhaId)
      .orElseThrow(() -> new BusinessException("Não existe nenhuma feirinha com esse ID"));
    
    if(dto.name() != null && !dto.name().isBlank())
      feirinhaToUpdate.setName(dto.name());
    if(dto.description() != null)
      feirinhaToUpdate.setDescription(dto.description());
    if(dto.location() != null && !dto.location().isBlank())
      feirinhaToUpdate.setLocation(dto.location());
    if(dto.beginTime() != null)
      feirinhaToUpdate.setBeginTime(dto.beginTime());
    if(dto.endTime() != null)
      feirinhaToUpdate.setEndTime(dto.endTime());
    
    this.verifyDatetime(feirinhaToUpdate);
    return this.repository.save(feirinhaToUpdate);
  }

  public void deleteById(String feirinhaId) throws BusinessException{
    if(!this.repository.existsById(feirinhaId))
      throw new BusinessException("Não existe nenhuma feirinha com esse ID");
    
    this.repository.deleteById(feirinhaId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void bindSellerWithFeirinha(String feirinhaId, String sellerId) throws BusinessException{
    final User sellerToVinculate = userService
      .findSellerById(sellerId)
      .orElseThrow(() -> new BusinessException("Não existe nenhum usuário com esse ID"));

    final Feirinha feirinha = this.repository
      .findById(feirinhaId)
      .orElseThrow(() -> new BusinessException("Não existe nenhuma feirinha com esse ID"));
    
    feirinha.getSellers().add(sellerToVinculate);
    this.repository.save(feirinha);
  }

  @Transactional(rollbackFor = Exception.class)
  public void unlinkSellerFromFeirinha(String feirinhaId, String sellerId) throws BusinessException{
    final Feirinha feirinha = this.repository
      .findById(feirinhaId)
      .orElseThrow(() -> new BusinessException("Feirinha não encontrada!"));

    boolean removed = feirinha.getSellers().removeIf(vendedor -> vendedor.getId().equals(sellerId));

    if (!removed) 
      throw new BusinessException("O usuário não pertence a essa feirinha");

    this.repository.save(feirinha);
  }

  public void verifyDatetime(Feirinha feirinha) throws BusinessException{
    if(feirinha.getBeginTime().isAfter(feirinha.getEndTime()))
      throw new BusinessException("A data de início do evento não pode ser depois da data de término");
  }
}
