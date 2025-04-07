package br.com.fiap.cp5.model;

public enum Categoria {
    COMEDIA("Comédia"),
    ROMANCE("Romance"),
    TERROR("Terror"),
    ACAO("Ação"),
    FANTASIA("Fantasia");

    private String descricao;
    Categoria(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
