package br.imd.ufrn.feirinhas_ufrn.controller;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> listarUsuarios(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable String id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable String id, @RequestBody User userUpdate) {
        try {
            User updated = userService.updateUser(id, userUpdate);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
