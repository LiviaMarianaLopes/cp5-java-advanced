package br.com.fiap.cp5.controller;

import br.com.fiap.cp5.dto.AnimeRequest;
import br.com.fiap.cp5.model.Anime;
import br.com.fiap.cp5.service.AnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    AnimeService animeService;

    @GetMapping("/registry")
    public String animeRegistry(Model model){
        model.addAttribute("animeRequest", new AnimeRequest());
        return "animeRegistry";
    }

    @PostMapping("/register")
    public String registerAnime(@Valid AnimeRequest animeRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            return "animeRegistry";
        }
        animeService.createAnime(animeRequest);
        return listAnimes(model);
    }

    @GetMapping("/list")
    public String listAnimes(Model model) {
        List<Anime> animes = animeService.searchAnimes();
        model.addAttribute("animesList", animes);
        return "listAnimes";
    }

    @GetMapping("/details/{id}")
    public String animeDetails(@PathVariable Long id, Model model){
        Anime anime = animeService.searchAnime(id);
        if (anime == null){
            return listAnimes(model);
        }
        model.addAttribute("idAnime", id);
        model.addAttribute("anime", animeService.animeToRequest(anime));
        return "animeEdit";
    }

    @PostMapping("/edit/{id}")
    public String editAnime(@PathVariable Long id, @Valid @ModelAttribute AnimeRequest animeRequest, Model model){
        animeService.updateAnime(id, animeRequest);
        return listAnimes(model);
    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id, Model model){
        animeService.deleteAnime(id);
        return listAnimes(model);
    }

}
