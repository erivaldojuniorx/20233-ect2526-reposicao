package br.ufrn.ect.rastreador.app.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ect.rastreador.app.entities.Localizacao;
import br.ufrn.ect.rastreador.app.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    public Localizacao findById(Integer id) {
        Optional<Localizacao> localizacao = repository.findById(id);

        return localizacao.get();
    }

    public Localizacao save(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public Localizacao update(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Localizacao> historicoLocalizacao(Integer veiculoId) {
        return repository.historicoLocalizacao(veiculoId);
    }

    public Localizacao ultimaLocalizacao(Integer veiculoId) {
        return repository.ultimaLocalizacao(veiculoId);
    }

    public List<Localizacao> localizacaoPorData(Integer veiculoId, String data) {
        return repository.localizacaoPorPeriodo(veiculoId, Date.valueOf(data), Date.valueOf(data));
    }

    public List<Localizacao> localizacaoPorPeriodo(Integer veiculoId, String dataInicial, String dataFinal) {
        return repository.localizacaoPorPeriodo(veiculoId, Date.valueOf(dataInicial), Date.valueOf(dataFinal));
    }

}
