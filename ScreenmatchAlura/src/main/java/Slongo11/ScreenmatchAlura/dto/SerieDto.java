package Slongo11.ScreenmatchAlura.dto;

import Slongo11.ScreenmatchAlura.model.Categoria;

public record SerieDto (Long id,
						String titulo,
						Integer totalTemporadas,
						Double avaliacao,
						Categoria genero,
						String poster,
						String sinopse,
						String atores){
}
