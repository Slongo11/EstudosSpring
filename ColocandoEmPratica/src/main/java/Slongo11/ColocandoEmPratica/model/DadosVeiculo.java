package Slongo11.ColocandoEmPratica.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Valor") String valor,
		@JsonAlias("Marca") String marca,
		@JsonAlias("Modelo") String modelo,
		@JsonAlias("AnoModelo") String ano,
		@JsonAlias("Combustivel") String combustivel,
		@JsonAlias("CodigoFipe") String codigoFipe) {
}
