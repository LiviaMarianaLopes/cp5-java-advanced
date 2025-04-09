package br.com.fiap.cp5.service;

import br.com.fiap.cp5.dto.SerieRequest;
import br.com.fiap.cp5.model.Serie;
import br.com.fiap.cp5.repository.SerieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    public Serie salvarSerie(SerieRequest serieRequest) {
        Serie serie = requestToSerie(serieRequest);
        return serieRepository.save(serie);
    }

    public void atualizarSerie(Long id, SerieRequest serieRequest) {
        Serie serie = buscarSerie(id);
        if (serie != null) {
            BeanUtils.copyProperties(serieRequest, serie);
            serieRepository.save(serie);
        }
    }

    public void deletarSerie(Long id) {
        serieRepository.deleteById(id);
    }

    public Serie requestToSerie(SerieRequest serieRequest) {
        Serie serie = new Serie();
        serie.setTitulo(serieRequest.getTitulo());
        serie.setCriador(serieRequest.getCriador());
        serie.setCategoria(serieRequest.getCategoria());
        serie.setTemporadas(serieRequest.getTemporadas());
        serie.setEpisodiosPorTemporada(serieRequest.getEpisodiosPorTemporada());
        return serie;
    }

    public SerieRequest serieToRequest(Serie serie) {
        SerieRequest serieRequest = new SerieRequest();
        BeanUtils.copyProperties(serie, serieRequest);
        return serieRequest;
    }

    public List<Serie> buscarSeries() {
        return serieRepository.findAll();
    }

    public Serie buscarSerie(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        return serie.orElse(null);
    }

}
