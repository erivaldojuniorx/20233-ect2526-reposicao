package br.ufrn.ect.rastreador.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.ect.rastreador.app.entities.Localizacao;
import br.ufrn.ect.rastreador.app.services.LocalizacaoService;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

    @Autowired
    LocalizacaoService service;

    @GetMapping("/{veiculo}/{inicio}/{fim}")
    public List<Localizacao> findByRange(@PathVariable Integer veiculo, @PathVariable String inicio,
            @PathVariable String fim) {
        return service.localizacaoPorPeriodo(veiculo, inicio, fim);
    }

    @GetMapping("/{veiculo}/{data}")
    public List<Localizacao> findByDate(@PathVariable Integer veiculo, @PathVariable String data) {
        return service.localizacaoPorData(veiculo, data);
    }

    @GetMapping("/{veiculo}")
    public List<Localizacao> historicoLocalizacao(@PathVariable Integer veiculo) {
        return service.historicoLocalizacao(veiculo);

    }

    @GetMapping("/ultima/{veiculo}")
    public Localizacao ultimaLocalizacao(@PathVariable Integer veiculo) {
        return service.ultimaLocalizacao(veiculo);
    }

    @PostMapping()
    public Localizacao save(@RequestBody Localizacao localizacao) {
        return service.save(localizacao);
    }

}
