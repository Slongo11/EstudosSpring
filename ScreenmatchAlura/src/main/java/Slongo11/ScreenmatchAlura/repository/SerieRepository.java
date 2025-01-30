package Slongo11.ScreenmatchAlura.repository;

import Slongo11.ScreenmatchAlura.model.Categoria;
import Slongo11.ScreenmatchAlura.model.Episodios;
import Slongo11.ScreenmatchAlura.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
	Optional<Serie> findByTituloContainingIgnoreCase(String nome);

	List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nome, double minAvaliacao);
	List<Serie> findTop5ByOrderByAvaliacaoDesc();
	List<Serie> findByGenero(Categoria genero);

	List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer numeroDeTemporadas, Double minAvaliacao);

	//JPQL
	@Query("SELECT s " +
			"FROM Serie s " +
			"WHERE s.totalTemporadas <= :numeroDeTemporadas " +
			"AND s.avaliacao >= :minAvaliacao")
	List<Serie> tempoaradasEAvaliacao(Integer numeroDeTemporadas, Double minAvaliacao);

	@Query("SELECT e " +
			"FROM Serie s JOIN Episodios e ON s.id = e.serie.id " +
			"WHERE e.titulo ILIKE %:trecho%")
	List<Episodios> buscarEpisodioPorTrecho(String trecho);
	@Query("SELECT e " +
			"FROM Serie s JOIN Episodios e ON s.id = e.serie.id " +
			"WHERE s = :serie " +
			"ORDER BY e.avaliacao " +
			"DESC " +
			"LIMIT 5")
	List<Episodios> topEpisodioPorSerie(Serie serie);

	@Query("SELECT e " +
			"FROM Serie s JOIN Episodios e ON s.id = e.serie.id " +
			"WHERE s = :serie " +
			"AND YEAR(e.dataLancamento) >= :anoLimite")
	List<Episodios> anoEpisodioPorSerie(Serie serie, Integer anoLimite);
}
