package Slongo11.ColocandoEmPratica.repository;

import Slongo11.ColocandoEmPratica.model.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FraseRepository extends JpaRepository<Frase, Long> {
	@Query("SELECT f " +
			"FROM Frase f " +
			"WHERE f.id = :idFrase ")
	public Frase buscaFrase(Long idFrase);
}
