package br.ufrn.ect.rastreador.app.security;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.ect.rastreador.app.entities.Usuario;
import br.ufrn.ect.rastreador.app.services.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(usuario.getLogin(), usuario.getSenha(), emptyList());
    }

}
