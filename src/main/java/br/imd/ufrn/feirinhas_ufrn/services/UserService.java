package br.imd.ufrn.feirinhas_ufrn.services;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.repository.UserRepository;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<User> findAllVendedores() {
        return repository.findAll().stream()
            .filter(user -> user.getRole().equals("VENDEDOR") || user.getRole().equals("USER")) // ajuste conforme enum/role
            .collect(Collectors.toList());
    }

    public User findVendedorByIdWithProdutosAndFeirinhas(String id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
        // Supondo que User já tenha os relacionamentos para produtos e feirinhas
        user.getProdutos().size(); // força carregamento se for LAZY
        user.getFeirinhas().size();
        return user;
    }
}
