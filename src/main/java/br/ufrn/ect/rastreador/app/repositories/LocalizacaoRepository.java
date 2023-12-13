package br.ufrn.ect.rastreador.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.ect.rastreador.app.entities.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {

}
