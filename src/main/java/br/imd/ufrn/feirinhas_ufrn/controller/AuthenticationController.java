package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.AuthDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.RegisterUserDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.UserResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.mappers.UserMapper;
import br.imd.ufrn.feirinhas_ufrn.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthorizationService authService;
    private final UserMapper userMapper;

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody AuthDTO dto){
        final String token = authService.authenticate(dto);

        return ResponseEntity.ok(token);
    }  


    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Validated @RequestBody RegisterUserDTO data){
        final User createdUser = this.authService.registerUser(data);
        final UserResponseDTO dto = userMapper.userResponseDtoFromUser(createdUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(dto); 
    }

    @SneakyThrows
    @PostMapping("/registerAdmin")
    public ResponseEntity<UserResponseDTO> registerAdmin(@Validated @RequestBody RegisterUserDTO data) {
        final User createdUser = this.authService.registerUser(data);
        final UserResponseDTO dto = userMapper.userResponseDtoFromUser(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }    
}
