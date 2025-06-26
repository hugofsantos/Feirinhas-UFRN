package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UpdateUserDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.mappers.UserMapper;
import br.imd.ufrn.feirinhas_ufrn.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/sellers")
    public List<UserResponseDTO> listarVendedores() {
        return userService
            .findAllSellers()
            .stream()
            .map(userMapper::userResponseDtoFromUser)
            .collect(Collectors.toList());
    }

    @GetMapping("/sellers/{id}")
    public ResponseEntity<UserInfoResponseDTO> buscarVendedorPorId(@PathVariable String id) {
        return userService
            .findSellerById(id)
            .map(user -> {
                final UserInfoResponseDTO dto = userMapper.userInfoResponseDtoFromUser(user);

                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponseDTO> listarUsuarios(){
        return userService
            .findAll()
            .stream()
            .map(userMapper::userResponseDtoFromUser)
            .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDTO> buscarPorId(@PathVariable String id) {
        return userService.findById(id)
            .map(user -> {
                final UserInfoResponseDTO dto = userMapper.userInfoResponseDtoFromUser(user);

                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) throws BusinessException{
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id") // Só deixa atualizar se você for admin ou se o usuário for você mesmo
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable String id, @Validated @RequestBody UpdateUserDTO userUpdate) throws BusinessException{
        final User updated = userService.updateUser(id, userUpdate);
        final UserResponseDTO responseDto = userMapper.userResponseDtoFromUser(updated);
        return ResponseEntity.ok(responseDto);
    }
}
