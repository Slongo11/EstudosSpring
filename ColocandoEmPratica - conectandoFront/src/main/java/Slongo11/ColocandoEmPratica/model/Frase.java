package Slongo11.ColocandoEmPratica.model;

import jakarta.persistence.*;

@Entity
public class Frase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String frase;
	private String personagem;
	private String poster;
	public Frase() {

	}
	public Frase(Dados dados,String frase ,String personagem) {
		this.titulo = dados.titulo();
		this.frase = frase;
		this.personagem = personagem;
		this.poster = dados.poster();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getFrase() {
		return frase;
	}

	public String getPersonagem() {
		return personagem;
	}

	public String getPoster() {
		return poster;
	}
}
