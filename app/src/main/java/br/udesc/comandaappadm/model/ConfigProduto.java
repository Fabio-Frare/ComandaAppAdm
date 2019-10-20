package br.udesc.comandaappadm.model;

import androidx.annotation.NonNull;

import java.util.List;

public class ConfigProduto {

    private String idConfigProduto;
    private String nomeConfigProduto;
    private List<ConfigValues> values;


    public String getIdConfigProduto() {
        return idConfigProduto;
    }

    public void setIdConfigProduto(String idConfigProduto) {
        this.idConfigProduto = idConfigProduto;
    }

    public String getNomeConfigProduto() {
        return nomeConfigProduto;
    }

    public void setNomeConfigProduto(String nomeConfigProduto) {
        this.nomeConfigProduto = nomeConfigProduto;
    }

    public List<ConfigValues> getValues() {
        return values;
    }

    public void setValues(List<ConfigValues> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeConfigProduto;
    }

}
