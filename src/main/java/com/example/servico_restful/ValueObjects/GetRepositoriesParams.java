package com.example.servico_restful.ValueObjects;

public class GetRepositoriesParams {
    private String nome;
    private int pagina;
    private int por_pagina;

    public GetRepositoriesParams(String nome, int pagina, int por_pagina) {
        this.nome = nome;
        this.pagina = pagina;
        this.por_pagina = por_pagina;
    }

    public String getNome() {
        return nome;
    }

    public int getPagina() {
        return pagina;
    }
    public int getPorPagina() {
        return por_pagina;
    }
}
