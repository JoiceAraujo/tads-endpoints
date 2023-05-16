package com.example.servico_restful.ValueObjects;

public class GetRepositoriesParams {
    private final String nome;
    private final int pagina;
    private final int por_pagina;

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
