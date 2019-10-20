package br.udesc.comandaappadm.model;

import java.util.List;

public class Produto {

    private String idProduto;
    private String nomeProduto;
    private String ingredientes;
    private float precoBase;
    private Categoria categoria;
    private List<ConfigProduto> configProduto;

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ConfigProduto> getConfigProduto() {
        return configProduto;
    }

    public void setConfigProduto(List<ConfigProduto> configProduto) {
        this.configProduto = configProduto;
    }
}
