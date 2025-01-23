package Slongo11.ScreenmatchAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodios {
	private Integer temporada;
	private String titulo;
	private Integer numero;
	private Double avaliacao;
	private LocalDate dataLancamento;

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public Integer getNumero() {
		return numero;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	@Override
	public String toString() {
		return "temporada=" + temporada +
				", titulo='" + titulo + '\'' +
				", numero=" + numero +
				", avaliacao=" + avaliacao +
				", dataLancamento=" + dataLancamento;
	}

	public Episodios(Integer temporada, DadosEpisodio dadosEpisodio) {
		this.temporada = temporada;
		this.titulo = dadosEpisodio.titulo();
		this.numero = dadosEpisodio.numero();
		try {
			this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
		}catch (NumberFormatException e){
			this.avaliacao = 0.0;
		}
		try {
			dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
		}catch (DateTimeParseException e){
			dataLancamento = null;
		}




	}
}
