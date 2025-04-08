package br.com.fiap.cp5.dto;

import br.com.fiap.cp5.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimeRequest {
    @NotBlank(message = "O título não pode estar vazio.")
    private String titulo;
    @NotBlank(message = "O estudio não pode estar vazio")
    private String estudio;
    @NotBlank(message = "Quantidade de episódio não pode estar vazio.")
    private int quantEpisodios;
    @NotNull(message = "O título deve ter uma categoria")
    private Categoria categoria;
}
