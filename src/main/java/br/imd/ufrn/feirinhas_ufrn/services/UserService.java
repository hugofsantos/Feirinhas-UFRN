package br.imd.ufrn.feirinhas_ufrn.services;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UpdateUserDTO;
import br.imd.ufrn.feirinhas_ufrn.repository.UserRepository;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) throws BusinessException{
        repository.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        repository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(String id, UpdateUserDTO userUpdate) throws BusinessException {
        User user = repository.findById(id)
            .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if(userUpdate.fullname() != null && !userUpdate.fullname().isBlank()) 
            user.setFullname(userUpdate.fullname());
        if(userUpdate.whatsapp() != null)
            user.setWhatsapp(userUpdate.whatsapp());
        
        return repository.save(user);
    }

    public List<User> findAllSellers() {
        return repository.findByRole(UserRole.USER); // BUSCA TODOS OS USUÁRIOS COMUNS
    }

    public Optional<User> findSellerById(String id) {
        return repository.findByIdAndRole(id, UserRole.USER);
    }
}
