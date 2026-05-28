package dev.brunohm.apiauth0jwt.controller;

import dev.brunohm.apiauth0jwt.dto.request.RequestAtualizarUsuario;
import dev.brunohm.apiauth0jwt.dto.request.RequestRegistroUsuario;
import dev.brunohm.apiauth0jwt.dto.response.ResponseAtualizarUsuario;
import dev.brunohm.apiauth0jwt.dto.response.ResponseListarUsuarios;
import dev.brunohm.apiauth0jwt.dto.response.ResponseRegistroUsuario;
import dev.brunohm.apiauth0jwt.entity.Usuario;
import dev.brunohm.apiauth0jwt.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseRegistroUsuario> criar(@RequestBody @Valid RequestRegistroUsuario dtoRequest) {
        Usuario novoUsuario = new Usuario(dtoRequest.nome(), dtoRequest.email(), dtoRequest.senha());
        usuarioService.salvar(novoUsuario);

        ResponseRegistroUsuario dtoResponse = new ResponseRegistroUsuario(novoUsuario.getNome(), novoUsuario.getEmail(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<ResponseListarUsuarios> listar() {
        List<Usuario> usuarios = usuarioService.listar();

        usuarios.forEach(u -> new ResponseListarUsuarios(u.getNome(), u.getEmail()));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAtualizarUsuario> editar(@PathVariable Long id, @RequestBody @Valid RequestAtualizarUsuario dtoRequest) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, dtoRequest.nome(), dtoRequest.email(), dtoRequest.senha());
        return ResponseEntity.ok(new ResponseAtualizarUsuario(usuarioAtualizado.getNome(), usuarioAtualizado.getEmail()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
