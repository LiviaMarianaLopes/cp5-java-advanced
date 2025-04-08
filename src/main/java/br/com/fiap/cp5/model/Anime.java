package br.com.fiap.cp5.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_anime")
@Data
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ESTUDIO")
    private String estudio;
    @Column(name = "EPISODIOS")
    private int quantEpisodios;
    @Column(name = "CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
