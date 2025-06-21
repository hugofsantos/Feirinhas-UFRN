package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.AuthenticationDTO;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.CadastroDTO;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.Usuario;
import br.imd.ufrn.feirinhas_ufrn.repository.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastroUsuario(@RequestBody @Valid CadastroDTO data){
        if(this.usuarioRepo.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }else {
            String encryptePassword = new BCryptPasswordEncoder().encode(data.senha());
            Usuario user = new Usuario(data.nome(), data.email(), encryptePassword, data.whatsapp(), data.role());

            this.usuarioRepo.save(user);
            return ResponseEntity.ok().build();
        }
    }
}
