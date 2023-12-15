package br.ufrn.ect.rastreador.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.ect.rastreador.app.entities.Veiculo;
import br.ufrn.ect.rastreador.app.services.VeiculoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService service;

    @GetMapping("/{id}")
    @Secured(value = { "ADMIN", "GESTOR", "USUARIO" })
    public Veiculo findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    @Secured(value = { "GESTOR" })
    public Veiculo save(@RequestBody Veiculo veiculo) {
        return service.save(veiculo);
    }

    @PutMapping()
    @Secured(value = { "GESTOR" })
    public Veiculo update(@RequestBody Veiculo veiculo) {
        return service.update(veiculo);
    }

    @DeleteMapping("/{id}")
    @Secured(value = { "GESTOR" })
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
