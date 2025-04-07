package br.com.fiap.cp5.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_serie")
@Data
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "criador")
    private String criador;
    @Column(name = "temporadas")
    private int temporadas;
    @Column(name = "epsodios")
    private int episodiosPorTemporada;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
