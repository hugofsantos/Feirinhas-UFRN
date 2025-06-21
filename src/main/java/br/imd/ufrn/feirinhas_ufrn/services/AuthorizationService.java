package br.imd.ufrn.feirinhas_ufrn.services;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;
import br.imd.ufrn.feirinhas_ufrn.dto.AuthDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.RegisterUserDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.AuthFailException;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    
    public String authenticate(AuthDTO dto) throws AuthFailException{
        final User findedUser = this.repository
            .findByEmail(dto.email())
            .orElseThrow(() -> new AuthFailException("Email ou senha inválidos"));
        
        if(!passwordEncoder.matches(dto.password(), findedUser.getPassword())) // Se não der match
            throw new AuthFailException("Email ou senha inválidos");

        return tokenService.generateToken(findedUser);
    }

    public User registerUser(RegisterUserDTO dto) throws BusinessException{
        final Optional<User> findedUser = this.repository.findByEmail(dto.email());

        if(findedUser.isPresent())
            throw new BusinessException("Já existe um usuário com esse email");

        final String encryptePassword = new BCryptPasswordEncoder().encode(dto.password());
        final User userToCreate = new User(dto.fullname(), dto.email(), encryptePassword, dto.whatsapp(), UserRole.USER);

        return this.repository.save(userToCreate);
    }

    public User registerAdminUser(RegisterUserDTO dto) throws BusinessException {
        final Optional<User> findedUser = this.repository.findByEmail(dto.email());

        if (findedUser.isPresent())
            throw new BusinessException("Já existe um usuário com esse email");

        final String encryptePassword = new BCryptPasswordEncoder().encode(dto.password());
        final User userToCreate = new User(dto.fullname(), dto.email(), encryptePassword, dto.whatsapp(), UserRole.ADMIN);

        return this.repository.save(userToCreate);        
    }
}
