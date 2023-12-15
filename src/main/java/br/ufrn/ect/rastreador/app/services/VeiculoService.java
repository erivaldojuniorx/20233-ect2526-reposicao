package br.ufrn.ect.rastreador.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ect.rastreador.app.entities.Veiculo;
import br.ufrn.ect.rastreador.app.repositories.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public Veiculo findById(Integer id) {
        Optional<Veiculo> veiculo = repository.findById(id);

        return veiculo.get();
    }

    public Veiculo save(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo update(Veiculo veiculo) {
        Optional<Veiculo> veiculoRecOp = repository.findById(veiculo.getId());

        if (!veiculoRecOp.isPresent()) {
            return null;
        }

        Veiculo veiculoRec = veiculoRecOp.get();

        if (veiculoRec != null) {
            veiculoRec.setAno(veiculo.getAno());
            veiculoRec.setModelo(veiculo.getModelo());
            veiculoRec.setPlaca(veiculo.getPlaca());
            return repository.save(veiculoRec);
        }

        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
