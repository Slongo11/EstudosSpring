package Slongo11.ScreenmatchAlura.service;

import Slongo11.ScreenmatchAlura.dto.EpisodioDto;
import Slongo11.ScreenmatchAlura.dto.SerieDto;
import Slongo11.ScreenmatchAlura.model.Categoria;
import Slongo11.ScreenmatchAlura.model.Episodios;
import Slongo11.ScreenmatchAlura.model.Serie;
import Slongo11.ScreenmatchAlura.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class SerieService {
	@Autowired
	private SerieRepository serieRepository;

	public List<SerieDto> obterSeries(){
		return converteDados( serieRepository.findAll());
	}

	public List<SerieDto> top5Series(){
		return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
	}

	private List<SerieDto> converteDados(List<Serie> series){
		return  series.stream()
				.map(s -> new SerieDto(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getGenero(),s.getPost(),s.getSinopse(),s.getAtores()))
				.collect(Collectors.toList());
	}

	public List<SerieDto> obterLancamentos(){
		return converteDados(serieRepository.top5Lancamentos());
	}

	public SerieDto obterPorId(long id){
		Optional<Serie> serie = serieRepository.findById(id);
		if(serie.isEmpty()){
			return null;
		}
		Serie s = serie.get();
		return new SerieDto(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getGenero(),s.getPost(),s.getSinopse(),s.getAtores());
	}

	public List<EpisodioDto> obeterTodosEpisodioSerie(long id){
		Optional<Serie> serie = serieRepository.findById(id);
		if(serie.isEmpty()){
			return null;
		}
		Serie s = serie.get();
		return converterEpisodios(s.getEpisodios());
	}
	private List<EpisodioDto> converterEpisodios(List<Episodios> ep){
		return ep.stream()
				.map(e -> new EpisodioDto(e.getTemporada(),e.getNumero(),e.getTitulo()))
				.collect(Collectors.toList());
	}


	public List<EpisodioDto> obterEpisodioPorTemporada(Long id, Long idTemporada) {
		return converterEpisodios(serieRepository.encontraEpisodioPorSerieTemporada(id,idTemporada));
	}

	public List<SerieDto> obterSeriesPorCategoria(String nomeGenero) {
		Categoria categoria = Categoria.fromPortugues(nomeGenero);
		return converteDados(serieRepository.findByGenero(categoria));
	}
}
