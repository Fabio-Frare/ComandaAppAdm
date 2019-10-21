package br.udesc.comandaappadm.model;

import androidx.annotation.NonNull;

public class ConfigValues {

    private String idConfigValue;
    private String nomeConfigValue;
    private String acrescimoPreco;

    public String getIdConfigValue() {
        return idConfigValue;
    }

    public void setIdConfigValue(String idConfigValue) {
        this.idConfigValue = idConfigValue;
    }

    public String getNomeConfigValue() {
        return nomeConfigValue;
    }

    public void setNomeConfigValue(String nomeConfigValue) {
        this.nomeConfigValue = nomeConfigValue;
    }

    public String getAcrescimoPreco() {
        return acrescimoPreco;
    }

    public void setAcrescimoPreco(String acrescimoPreco) {
        this.acrescimoPreco = acrescimoPreco;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return nomeConfigValue + " : R$ " + acrescimoPreco + ",00";
//    }

}
