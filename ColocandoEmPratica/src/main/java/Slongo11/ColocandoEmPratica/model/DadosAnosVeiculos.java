package Slongo11.ColocandoEmPratica.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAnosVeiculos(@JsonAlias("codigo") String ano,
								@JsonAlias("nome") String descricao) {
}
