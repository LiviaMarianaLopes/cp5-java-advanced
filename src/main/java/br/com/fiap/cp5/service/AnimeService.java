package br.com.fiap.cp5.service;

import br.com.fiap.cp5.dto.AnimeRequest;
import br.com.fiap.cp5.model.Anime;
import br.com.fiap.cp5.repository.AnimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    @Autowired
    AnimeRepository animeRepository;

    public Anime createAnime(AnimeRequest animeRequest)  {
        Anime anime = requestToAnime(animeRequest);
        return animeRepository.save(anime);
    }

    public void updateAnime(Long id, AnimeRequest animeRequest){
        Anime anime = searchAnime(id);
        if (anime != null){
           BeanUtils.copyProperties(animeRequest, anime);
           animeRepository.save(anime);
        }
    }

    public void deleteAnime(Long id){
        animeRepository.deleteById(id);
    }

    public Anime requestToAnime(AnimeRequest animeRequest){
        Anime anime = new Anime();
        anime.setTitulo(animeRequest.getTitulo());
        anime.setEstudio(animeRequest.getEstudio());
        anime.setQuantEpisodios(animeRequest.getQuantEpisodios());
        anime.setCategoria(animeRequest.getCategoria());
        return anime;
    }

    public AnimeRequest animeToRequest(Anime anime){
        AnimeRequest animeRequest = new AnimeRequest();
        BeanUtils.copyProperties(anime, animeRequest);
        return animeRequest;
    }

    public List<Anime> searchAnimes() {
        return animeRepository.findAll();
    }

    public Anime searchAnime(Long id){
        Optional<Anime> anime = animeRepository.findById(id);
        return anime.orElse(null);
    }

}


