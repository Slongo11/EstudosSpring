package Slongo11.ColocandoEmPratica.service;

import Slongo11.ColocandoEmPratica.dto.FraseDTO;
import Slongo11.ColocandoEmPratica.model.Frase;
import Slongo11.ColocandoEmPratica.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FraseService {

	@Autowired
	private FraseRepository fraseRepository;
	public FraseDTO sorteiaFrase() {
		List<Frase> frases = fraseRepository.findAll();
		Frase escolhida = fraseRepository.buscaFrase(sorteiaFraseDTO(frases));
		return new FraseDTO(escolhida.getTitulo(), escolhida.getFrase(), escolhida.getPersonagem(), escolhida.getPoster());
	}

	private Long sorteiaFraseDTO(List<Frase> frases) {
		if (frases.isEmpty()) {
			return 0L;
		}
		return (long) (Math.random() * frases.size()) + 1;
	}


}
