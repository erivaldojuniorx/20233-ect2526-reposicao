package br.ufrn.ect.rastreador.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.ect.rastreador.app.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.login=:login")
    Usuario findByLogin(String login);

}
