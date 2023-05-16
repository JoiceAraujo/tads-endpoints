package com.example.servico_restful.Entities;


import com.example.servico_restful.Entities.PojoEntities.RepositoryPojo;

// Objeto de valor
public class SimplifiedRepository {
    public SimplifiedRepository(RepositoryPojo repositoryPojo) {
        this.id = repositoryPojo.id;
        this.name = repositoryPojo.name;
    }
    private String id;
    private String name;
}



