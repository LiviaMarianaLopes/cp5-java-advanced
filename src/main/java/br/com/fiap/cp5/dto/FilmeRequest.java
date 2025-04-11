package br.com.fiap.cp5.dto;

import br.com.fiap.cp5.model.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class FilmeRequest {
    @NotBlank(message = "O titulo nao pode ser vazio")
    private String titulo;
    @NotBlank(message = "O diretor nao pode ser vazio")
    private String diretor;
    @NotNull(message = "A duracao nao pode ser vazia")
    @Min(value = 40, message = "O filme deve ter no minimo 40 minutos ")
    private int duracaoMinutos;
    @NotNull(message = "A categoria nao pode ser vazia")
    private Categoria categoria;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
