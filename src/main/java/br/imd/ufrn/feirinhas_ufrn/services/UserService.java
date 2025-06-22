package br.imd.ufrn.feirinhas_ufrn.services;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.repository.UserRepository;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public User updateUser(String id, User userUpdate) throws BusinessException {
        User user = repository.findById(id)
            .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        user.setFullname(userUpdate.getFullname());
        user.setWhatsapp(userUpdate.getWhatsapp());
        // Não altere email/senha/role aqui por padrão
        return repository.save(user);
    }
}
