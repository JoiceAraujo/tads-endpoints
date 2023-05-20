package com.example.servico_restful.ValueObjects;


import com.example.servico_restful.Entities.PojoEntities.RepositoryPojo;
public class SimplifiedRepository {
    private final String id;
    private final String name;

    public SimplifiedRepository(RepositoryPojo repositoryPojo) {
        this.id = repositoryPojo.id;
        this.name = repositoryPojo.name;
    }
}



