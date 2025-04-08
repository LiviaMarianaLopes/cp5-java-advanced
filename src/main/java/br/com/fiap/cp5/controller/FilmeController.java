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
    public String registerFilme(@Valid FilmeRequest filmeRequest, BindingResult result, Model model){
        if(result.hasErrors()){
            return "filmeCadastro";
        }
        filmeService.createFilme(filmeRequest);
        return listFilme(model);
    }

    @GetMapping("/lista")
    public String listFilme(Model model){
        List<Filme> filmes = filmeService.searchFilme();
        model.addAttribute("listaFilmes", filmes);
        return "filmeLista";
    }

    @GetMapping("/edicao/{id}")
    public String filmeUpdate(@PathVariable Long id, Model model) {
        Filme filme = filmeService.searchFilme(id);
        if(filme == null) {
            return listFilme(model);
        }
        model.addAttribute("idFilme", id);
        model.addAttribute("livro", filmeService.filmeToRequest(filme));
        return "filmeEdicao";
    }

    @PostMapping("/editar/{id}")
    public String updateFilme(@PathVariable Long id, @Valid @ModelAttribute FilmeRequest filmeRequest, Model model){
        filmeService.updateFilme(id, filmeRequest);
        return listFilme(model);
    }

    @GetMapping("/deletar/{id}")
    public String deleteFilme(@PathVariable Long id, Model model){
        filmeService.deleteFilme(id);
        return listFilme(model);
    }
}
