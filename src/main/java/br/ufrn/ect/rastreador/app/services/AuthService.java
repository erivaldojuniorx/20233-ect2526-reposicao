package br.ufrn.ect.rastreador.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ect.rastreador.app.entities.Usuario;
import br.ufrn.ect.rastreador.app.enums.Papel;
import br.ufrn.ect.rastreador.app.repositories.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public boolean usuarioExiste(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        return usuario != null;
    }

    public Usuario registrarUsuario(String login, String senha, String nome) {

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senha);
        novoUsuario.setNome(nome);
        novoUsuario.setPapel(Papel.USUARIO);

        return usuarioService.save(novoUsuario);
    }

    public Usuario fazerLogin(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario == null) {
            return null;
        }

        boolean matches = usuarioService.verifyPassword(usuario, senha);

        if (!matches) {
            return null;
        }

        return usuario;
    }

}
