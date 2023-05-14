package com.example.servico_restful.RouteParamsModels;

public class GetRepositoriesRequest {
    private String nome;
    private int pagina;
    private int porPagina;

    public GetRepositoriesRequest(String nome, int pagina, int porPagina) {
        this.nome = nome;
        this.pagina = pagina;
        this.porPagina = porPagina;
    }

    public String getNome() {
        return nome;
    }

    public int getPagina() {
        return pagina;
    }
    public int getPorPagina() {
        return porPagina;
    }
}
