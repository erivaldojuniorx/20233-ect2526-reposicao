package br.ufrn.ect.rastreador.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.ect.rastreador.app.entities.Veiculo;
import br.ufrn.ect.rastreador.app.services.VeiculoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService service;

    @GetMapping("/{id}")
    public Veiculo findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    public Veiculo save(@RequestBody Veiculo veiculo) {
        return service.save(veiculo);
    }

}
