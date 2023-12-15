package br.ufrn.ect.rastreador.app.services;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufrn.ect.rastreador.app.entities.Usuario;
import br.ufrn.ect.rastreador.app.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z]).{6,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);

        return usuario.get();
    }

    public Usuario findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        return usuario;
    }

    public Usuario save(Usuario usuario) throws RuntimeException {
        String login = usuario.getLogin();
        String senha = usuario.getSenha();

        Usuario usuarioRec = repository.findByLogin(login);

        if (usuarioRec != null) {
            throw new RuntimeException("Login já existe");
        }

        Matcher matcher = pattern.matcher(senha);
        if (!matcher.matches()) {
            throw new RuntimeException("Senha não atende os requisitos");
        }

        String senhaCriptografada = bCryptPasswordEncoder.encode(senha);
        usuario.setSenha(senhaCriptografada);

        return repository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public boolean verifyPassword(Usuario usuario, String senha) {
        return bCryptPasswordEncoder.matches(senha, usuario.getSenha());
    }
}
