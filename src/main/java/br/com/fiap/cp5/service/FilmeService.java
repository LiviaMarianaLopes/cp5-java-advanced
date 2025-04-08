package br.com.fiap.cp5.service;

import br.com.fiap.cp5.dto.FilmeRequest;
import br.com.fiap.cp5.model.Filme;
import br.com.fiap.cp5.repository.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {
    @Autowired
    FilmeRepository filmeRepository;

    public Filme createFilme (FilmeRequest filmeRequest){
        Filme filme = requestToFilme(filmeRequest);
        return filmeRepository.save(filme);
    }

    public void updateFilme (Long id, FilmeRequest filmeRequest) {
        Filme filme = searchFilme(id);
        if( filme != null ){
            BeanUtils.copyProperties(filmeRequest, filme);
            filmeRepository.save(filme);
        }
    }

    public Filme requestToFilme(FilmeRequest filmeRequest){
        Filme filme = new Filme();
        filme.setTitulo(filmeRequest.getTitulo());
        filme.setDiretor(filmeRequest.getDiretor());
        filme.setDuracaoMinutos(filmeRequest.getDuracaoMinutos());
        filme.setCategoria(filmeRequest.getCategoria());
        return filme;
    }

    public FilmeRequest filmeToRequest(Filme filme){
        FilmeRequest filmeRequest = new FilmeRequest();
        BeanUtils.copyProperties(filme, filmeRequest);
        return filmeRequest;
    }

    public List<Filme> searchFilme(){
        return filmeRepository.findAll();
    }

    public Filme searchFilme(Long id){
        Optional<Filme> filme = filmeRepository.findById(id);
        return filme.orElse(null);
    }
}
