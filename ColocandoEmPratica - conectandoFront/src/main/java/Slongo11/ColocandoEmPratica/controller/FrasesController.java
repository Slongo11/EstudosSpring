package Slongo11.ColocandoEmPratica.controller;

import Slongo11.ColocandoEmPratica.dto.FraseDTO;
import Slongo11.ColocandoEmPratica.service.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class FrasesController {
	@Autowired
	private FraseService fraseService;

	@GetMapping("/frases")
	public FraseDTO frases(){
		return fraseService.sorteiaFrase();
	}
}
