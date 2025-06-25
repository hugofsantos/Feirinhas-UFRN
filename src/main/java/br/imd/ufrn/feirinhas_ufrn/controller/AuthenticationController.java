package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.auth.AuthDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.auth.RegisterUserDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.exception.AuthFailException;
import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;
import br.imd.ufrn.feirinhas_ufrn.mappers.UserMapper;
import br.imd.ufrn.feirinhas_ufrn.services.AuthorizationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthorizationService authService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody AuthDTO dto) throws AuthFailException{
        final String token = authService.authenticate(dto);

        return ResponseEntity.ok(token);
    }  


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Validated @RequestBody RegisterUserDTO data) throws BusinessException{
        final User createdUser = this.authService.registerUser(data);
        final UserResponseDTO dto = userMapper.userResponseDtoFromUser(createdUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(dto); 
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<UserResponseDTO> registerAdmin(@Validated @RequestBody RegisterUserDTO data) throws BusinessException{
        final User createdUser = this.authService.registerAdminUser(data);
        final UserResponseDTO dto = userMapper.userResponseDtoFromUser(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }    
}
