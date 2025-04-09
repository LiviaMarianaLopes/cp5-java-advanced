package br.com.fiap.cp5.dto;

import br.com.fiap.cp5.model.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SerieRequest {
    @NotBlank(message = "{titulo.not.blank}")
    private String titulo;
    @NotBlank(message = "{criador.not.blank}")
    @Size(min = 3, max = 150, message = "{criador.size}")
    private String criador;
    @NotNull(message = "temporadas.not.null")
    @Min(value = 1, message = "{temporadas.min}")
    private int temporadas;
    @NotNull(message = "episodiosPorTemporada.not.null")
    @Min(value = 1, message = "{episodiosPorTemporada.min}")
    private int episodiosPorTemporada;
    @NotNull(message = "{categoria.not.null}")
    private Categoria categoria;

}
