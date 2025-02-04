package Slongo11.ScreenmatchAlura.controller;

import Slongo11.ScreenmatchAlura.dto.EpisodioDto;
import Slongo11.ScreenmatchAlura.dto.SerieDto;
import Slongo11.ScreenmatchAlura.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/series")
public class SerieController {

	@Autowired
	private SerieService serieService;

	@GetMapping()
	public List<SerieDto> obterSeries() {
		return serieService.obterSeries();
	}
	@GetMapping("/top5")
	public List<SerieDto> obterTop5Series() {
		return serieService.top5Series();
	}

	@GetMapping("/lancamentos")
	public List<SerieDto> obterLancamentos() {
		return serieService.obterLancamentos();
	}
	@GetMapping("/{id}")
	public SerieDto obterSerieId(@PathVariable Long id) {
		return serieService.obterPorId(id);
	}

	@GetMapping("/{id}/temporadas/todas")
	public List<EpisodioDto> obterTemporadas(@PathVariable Long id) {
		return serieService.obeterTodosEpisodioSerie(id);
	}

	@GetMapping("/{id}/temporadas/{idTemporada}")
	public List<EpisodioDto> obterUnicaTemporada(@PathVariable Long id, @PathVariable Long idTemporada) {
		return serieService.obterEpisodioPorTemporada(id,idTemporada);
	}

	@GetMapping("/categoria/{nomeGenero}")
	public List<SerieDto> obterCategoria(@PathVariable String nomeGenero) {
		return  serieService.obterSeriesPorCategoria(nomeGenero);
	}


}
