package com.example.servico_restful.ResponseModels;


import com.example.servico_restful.PojoModels.RepositoryPojo;

public class SimplifiedRepository {
    public SimplifiedRepository(RepositoryPojo repositoryPojo) {
        this.id = repositoryPojo.id;
        this.name = repositoryPojo.name;
    }

    private String id;
    private String name;

}



