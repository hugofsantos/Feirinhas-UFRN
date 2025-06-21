package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.Usuario;
import br.imd.ufrn.feirinhas_ufrn.repository.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsuarioController {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioRepo.findAll();
    }

}
