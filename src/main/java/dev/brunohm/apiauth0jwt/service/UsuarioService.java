package dev.brunohm.apiauth0jwt.service;

import dev.brunohm.apiauth0jwt.entity.Usuario;
import dev.brunohm.apiauth0jwt.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, String nome, String email, String senha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuarioRepository.save(usuario);
    }

    public void desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public UserDetails encontrarPorLogin(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
