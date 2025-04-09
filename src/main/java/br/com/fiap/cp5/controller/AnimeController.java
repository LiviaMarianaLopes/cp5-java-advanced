package br.com.fiap.cp5.controller;

import br.com.fiap.cp5.dto.AnimeRequest;
import br.com.fiap.cp5.model.Anime;
import br.com.fiap.cp5.model.Serie;
import br.com.fiap.cp5.service.AnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registerAnime(@Valid AnimeRequest animeRequest, BindingResult result){
        if (result.hasErrors()){
            return "animeRegistry";
        }
        animeService.createAnime(animeRequest);
        return "redirect:/anime/list";
    }

    @GetMapping("/list")
    public ModelAndView listAnimes() {
        ModelAndView mv = new ModelAndView("listAnimes");
        List<Anime> animes = animeService.searchAnimes();
        mv.addObject("animesList", animes);
        return mv;
    }

    @GetMapping("/details/{id}")
    public ModelAndView animeDetails(@PathVariable Long id){
        Anime anime = animeService.searchAnime(id);
        if (anime == null){
            return listAnimes();
        }
        ModelAndView mv = new ModelAndView("animeEdit");
        mv.addObject("idAnime", id);
        mv.addObject("anime", animeService.animeToRequest(anime));
        return mv;
    }

    @PostMapping("/edit/{id}")
    public String editAnime(@PathVariable Long id, @Valid @ModelAttribute AnimeRequest animeRequest, BindingResult result, RedirectAttributes redirectAttributes){
       if (result.hasErrors()) {
           redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
           return "redirect:/anime/details" + id;
       }
       animeService.updateAnime(id, animeRequest);
       return "redirect:/anime/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id){
        animeService.deleteAnime(id);
        return "redirect:/anime/list";
    }

}
