package Slongo11.ScreenmatchAlura.model;

import Slongo11.ScreenmatchAlura.service.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "serie")
public class Serie {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(unique = true, nullable = false)
	private String titulo;
	private Integer totalTemporadas;
	private double avaliacao;
	@Enumerated(EnumType.STRING)
	private Categoria genero;
	private String post;
	private String sinopse;
	private String atores;
	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Episodios> episodios;

	public Serie(DadosSerie dadosSerie) {
		episodios = new ArrayList<>();
		this.titulo = dadosSerie.titulo();
		this.totalTemporadas = dadosSerie.totalTemporadas();
		this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0.0);
		try {
			this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
		}catch (IllegalArgumentException e){
			this.genero = null;
		}

		this.post = dadosSerie.post();
		this.sinopse = ConsultaMyMemory.obterTraducao( dadosSerie.sinopse()).trim();
		this.atores = dadosSerie.atores();
	}

	public void setEpisodios(List<Episodios> episodios) {
		episodios.forEach(episodio -> episodio.setSerie(this));
		this.episodios = episodios;
	}

	public Serie() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public List<Episodios> getEpisodios() {
		return episodios;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public void setGenero(Categoria genero) {
		this.genero = genero;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public Categoria getGenero() {
		return genero;
	}

	@Override
	public String toString() {
		return
				"genero=" + genero +
				", titulo='" + titulo + '\'' +
				", totalTemporadas=" + totalTemporadas +
				", avaliacao=" + avaliacao +
				", post='" + post + '\'' +
				", sinopse='" + sinopse + '\'' +
				", atores='" + atores + '\'' +
				"\nEpisodios='" + episodios + '\'';
	}
}
