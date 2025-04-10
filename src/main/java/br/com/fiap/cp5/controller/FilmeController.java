package br.com.fiap.cp5.controller;

import br.com.fiap.cp5.dto.FilmeRequest;
import br.com.fiap.cp5.model.Filme;
import br.com.fiap.cp5.service.FilmeService;
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
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    FilmeService filmeService;

    @GetMapping("/cadastro")
    public String filmeRegister(Model model){
        model.addAttribute("filmeRequest", new FilmeRequest());
        return "filmeCadastro";
    }

    @PostMapping("/cadastrar")
    public ModelAndView registerFilme(@Valid FilmeRequest filmeRequest, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("filmeCadastro");
            modelAndView.addObject("filme", new Filme());
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        filmeService.createFilme(filmeRequest);
        modelAndView.setViewName("redirect:/filme/lista");

        return modelAndView;
    }

    @GetMapping("/lista")
    public ModelAndView listFilme(){
        ModelAndView mv = new ModelAndView("filmeLista");
        List<Filme> filmes = filmeService.searchFilme();
        mv.addObject("listaFilme", filmes);
        return mv;
    }

    @GetMapping("/edicao/{id}")
    public ModelAndView filmeUpdate(@PathVariable Long id) {
        Filme filme = filmeService.searchFilme(id);
        if(filme == null) {
            return listFilme();
        }
        ModelAndView mv = new ModelAndView("filmeEdicao");
        mv.addObject("idFilme", id);
        mv.addObject("filme", filmeService.filmeToRequest(filme));
        return mv;
    }

    @PostMapping("/editar/{id}")
    public String updateFilme(@PathVariable Long id, @Valid @ModelAttribute FilmeRequest filmeRequest, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/filme/edicao/" + id;
        }
        filmeService.updateFilme(id, filmeRequest);
        return "redirect:/filme/lista";
    }

    @GetMapping("/deletar/{id}")
    public String deleteFilme(@PathVariable Long id){
        filmeService.deleteFilme(id);
        return "redirect:/filme/lista";
    }
}
