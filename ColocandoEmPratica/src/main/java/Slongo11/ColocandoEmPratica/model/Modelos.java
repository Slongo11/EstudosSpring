package Slongo11.ColocandoEmPratica.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties (ignoreUnknown = true)
public record Modelos(@JsonAlias("modelos") List<DadosModelo> dadosMarcas){
}
