package Slongo11.ScreenmatchAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTraducao(@JsonAlias ("responseData") DadosResposta dadosResposta) {
}
