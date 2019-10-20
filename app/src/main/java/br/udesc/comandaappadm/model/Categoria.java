package br.udesc.comandaappadm.model;


import androidx.annotation.NonNull;

public class Categoria {

    private String idCategoria;
    private String nomeCategoria;

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeCategoria;
    }
}
