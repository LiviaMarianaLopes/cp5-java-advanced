package br.com.fiap.cp5.controller;

import br.com.fiap.cp5.dto.SerieRequest;
import br.com.fiap.cp5.model.Serie;
import br.com.fiap.cp5.service.SerieService;
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
@RequestMapping("/serie")
public class SerieController {
    @Autowired
    SerieService serieService;

    @GetMapping("/cadastro")
    public String serieCadastro(Model model) {
        model.addAttribute("serieRequest", new SerieRequest());
        return "serieCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarSerie(@Valid @ModelAttribute SerieRequest serieRequest, BindingResult result) {
        if(result.hasErrors()) {
            return "serieCadastro";
        }
        serieService.salvarSerie(serieRequest);
        return "redirect:/serie/lista";
    }

    @GetMapping("/lista")
    public ModelAndView listarSeries() {
        ModelAndView mv = new ModelAndView("serieLista");
        List<Serie> series = serieService.buscarSeries();
        mv.addObject("listaSeries", series);
        return mv;
    }

    @GetMapping("/edicao/{id}")
    public ModelAndView serieEdicao(@PathVariable Long id) {
        Serie serie = serieService.buscarSerie(id);
        if (serie == null) {
            return listarSeries();
        }
        ModelAndView mv = new ModelAndView("serieEdicao");
        mv.addObject("idSerie", id);
        mv.addObject("serie", serieService.serieToRequest(serie));
        return mv;
    }

    @PostMapping("/editar/{id}")
    public  String  editarSerie(@PathVariable Long id, @Valid @ModelAttribute SerieRequest serieRequest, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/serie/edicao/" + id;
        }
        serieService.atualizarSerie(id, serieRequest);
        return "redirect:/serie/lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletarSerie(@PathVariable Long id){
        serieService.deletarSerie(id);
        return "redirect:/serie/lista";
    }
}

