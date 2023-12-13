package br.ufrn.ect.rastreador.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.ect.rastreador.app.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

}
