package Slongo11.ScreenmatchAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
@Entity
@Table(name = "episodio")
public class Episodios {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer temporada;
	private String titulo;
	private Integer numero;
	private Double avaliacao;
	private LocalDate dataLancamento;
	@ManyToOne
	@JoinColumn(name = "serie_id")
	private Serie serie;
	public Episodios() {}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
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

	public Serie getSerie() {
		return serie;
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
