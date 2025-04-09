package br.com.fiap.cp5.dto;

import br.com.fiap.cp5.model.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnimeRequest {
    @NotBlank(message = "{titulo.not.blank}")
    private String titulo;
    @NotBlank(message = "{estudio.not.blank}")
    private String estudio;
    @NotNull(message = "{episodios.not.blank}")
    @Min(value = 1,message = "{episodios.quantity}")
    private int quantEpisodios;
    @NotNull(message = "{categoria.not.null}")
    private Categoria categoria;
}
