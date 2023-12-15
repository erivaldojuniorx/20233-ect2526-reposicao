package br.ufrn.ect.rastreador.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.ect.rastreador.app.entities.Usuario;
import br.ufrn.ect.rastreador.app.security.TokenUtil;
import br.ufrn.ect.rastreador.app.services.AuthService;
import br.ufrn.ect.rastreador.app.util.ResponseObject;
import br.ufrn.ect.rastreador.app.util.TokenResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario) {
        ResponseObject response = new ResponseObject();

        if (service.usuarioExiste(usuario.getLogin())) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("o usuário já foi registrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        service.registrarUsuario(usuario.getLogin(), usuario.getSenha(), usuario.getNome());

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Usuário registrado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> fazerLogin(@RequestBody Usuario usuario) {
        Usuario usuarioRec = service.fazerLogin(usuario.getLogin(), usuario.getSenha());
        ResponseObject response = new ResponseObject();

        if (usuarioRec == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Usuário e/ou senha inválidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = TokenUtil.getToken(usuarioRec.getLogin());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return ResponseEntity.ok(tokenResponse);
    }
}
