package br.udesc.comandaappadm.model;

class ConfigProduto {

    private String idConfigProduto;
    private String nomeConfigProduto;
    private float acrescimoPreco;

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

    public float getAcrescimoPreco() {
        return acrescimoPreco;
    }

    public void setAcrescimoPreco(float acrescimoPreco) {
        this.acrescimoPreco = acrescimoPreco;
    }

}
