package Slongo11.ColocandoEmPratica.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelo(@JsonAlias("codigo") Integer codigo,
						  @JsonAlias("nome") String modeloNome) {
}
