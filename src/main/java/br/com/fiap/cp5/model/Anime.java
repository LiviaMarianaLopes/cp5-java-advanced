package br.com.fiap.cp5.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_anime")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public int getQuantEpisodios() {
        return quantEpisodios;
    }

    public void setQuantEpisodios(int quantEpisodios) {
        this.quantEpisodios = quantEpisodios;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
