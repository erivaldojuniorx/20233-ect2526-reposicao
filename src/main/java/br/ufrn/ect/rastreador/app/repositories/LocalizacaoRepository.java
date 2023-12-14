package br.ufrn.ect.rastreador.app.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.ect.rastreador.app.entities.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {

    @Query("SELECT l FROM Localizacao l WHERE l.veiculo.id = :veiculoId")
    List<Localizacao> historicoLocalizacao(Integer veiculoId);

    /*
     * @Query("SELECT l FROM Localizacao l WHERE l.veiculo.id = :veiculoId AND l.data = :data"
     * )
     * List<Localizacao> localizacaoPorData(Integer veiculoId, Date data);
     */

    @Query("SELECT l FROM Localizacao l WHERE l.veiculo.id = :veiculoId AND l.data >= :dataInicial AND l.data <= :dataFinal")
    List<Localizacao> localizacaoPorPeriodo(Integer veiculoId, Date dataInicial, Date dataFinal);

    @Query("SELECT l FROM Localizacao l WHERE l.veiculo.id = :veiculoId ORDER BY l.data DESC, l.hora DESC LIMIT 1")
    Localizacao ultimaLocalizacao(Integer veiculoId);
}
