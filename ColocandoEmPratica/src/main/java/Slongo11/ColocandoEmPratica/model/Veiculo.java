package Slongo11.ColocandoEmPratica.model;

import java.time.LocalDate;

public class Veiculo {
	private Double valor;
	private String marca;
	private String modelo;
	private LocalDate ano;
	private String combustivel;
	private String codigoFipe;

	public Veiculo(DadosVeiculo dadosVeiculo) {
		try {
			this.valor = Double.parseDouble(dadosVeiculo.valor()
					.replace("R$","")
					.replace(",00","")
					.replace(".",""));
		}catch (Exception e){
			this.valor = 0.0;
		}
		this.marca = dadosVeiculo.marca();
		this.modelo = dadosVeiculo.modelo();
		this.ano = LocalDate.of(Integer.parseInt(dadosVeiculo.ano().substring(0,4)),1,1);
		this.combustivel = dadosVeiculo.combustivel();
		this.codigoFipe = dadosVeiculo.codigoFipe();
	}

	@Override
	public String toString() {
		return "valor=" + valor +
				", marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", ano=" + ano +
				", combustivel='" + combustivel + '\'' +
				", codigoFipe='" + codigoFipe + '\'';
	}
}
