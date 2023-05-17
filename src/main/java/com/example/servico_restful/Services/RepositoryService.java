package com.example.servico_restful.Services;

import com.example.servico_restful.Entities.Actor;
import com.example.servico_restful.Entities.PojoEntities.ActorPojo;
import com.example.servico_restful.Entities.PojoEntities.ActorsPojo;
import com.example.servico_restful.Entities.Repository;
import com.example.servico_restful.Utils.Constants;
import com.example.servico_restful.Entities.PojoEntities.RepositoryPojo;
import com.example.servico_restful.Entities.PojoEntities.RepositoriesPojo;
import com.example.servico_restful.ValueObjects.SimplifiedRepository;
import com.example.servico_restful.Utils.Pagination;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Fazer uma resposta para rotas não encontradas
// TODO: Tratar quando não passa nenhum parâmetro?
@RestController
@ResponseBody()
@RequestMapping("/repos")
public class RepositoryService {
    @RequestMapping(method = RequestMethod.GET, value = "/find", produces="application/json")
    // TODO: passar os parâmetros na rota
    public @ResponseBody ResponseEntity<String> getRepositories(
            @RequestParam(name = "nome") String repositoryName,
            @RequestParam(name = "pagina", required = false, defaultValue = "1") String page,
            @RequestParam(name = "por_pagina", required = false, defaultValue = "10") String perPage
            ) {
        if(repositoryName != null && (repositoryName.isEmpty() || repositoryName.isBlank())) {
            Map<String, Object> map = Map.ofEntries(Map.entry(Constants.KEY_MESSAGE, Constants.REQUIRED_NAME_PARAM_ERROR));

            return new ResponseEntity<>(new Gson().toJson(map), HttpStatus.BAD_REQUEST);
        }

        try {
            String stringJson = readFile(Constants.JSON_REPOSITORIES_FILE_NAME);
            RepositoriesPojo repositoriesPojo = parseStringJsonToRepositoriesPojo(stringJson);
            List<RepositoryPojo> filteredRepositories = filterRepositoryPojoByParam(repositoryName, repositoriesPojo);

            if(filteredRepositories.isEmpty()) {
                return new ResponseEntity<>(new Gson().toJson(new ArrayList<>()), HttpStatus.NO_CONTENT);
            }

            ArrayList<SimplifiedRepository> simplifiedRepositories = parseRepositoryPojoToSimplifiedRepository(filteredRepositories);

            Pagination pagination = new Pagination(simplifiedRepositories);
            ArrayList paginatedList = new ArrayList();

            paginatedList.addAll(pagination.getItemsByPage(Integer.valueOf(page), Integer.valueOf(perPage)));

            return new ResponseEntity<>(new Gson().toJson(paginatedList), HttpStatus.OK);
        } catch (IOException e) {
            return serverErrorResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "{repoId}", produces="application/json")
    public @ResponseBody ResponseEntity<String> getRepositoriesById(@PathVariable String repoId) {
        try {
            String repositoriesJson = readFile(Constants.JSON_REPOSITORIES_FILE_NAME);
            String actorsJson = readFile(Constants.JSON_ACTORS_FILE_NAME);

            RepositoriesPojo repositoriesPojo = parseStringJsonToRepositoriesPojo(repositoriesJson);
            ActorsPojo actorsPojo = parseStringJsonToActor(actorsJson);

            Optional<RepositoryPojo> repositoryPojo = repositoriesPojo.repositories.stream()
                    .filter(repository ->  repository.id.equals(repoId))
                    .findFirst();
            if(repositoryPojo.isEmpty()) {
                return new ResponseEntity<>(new Gson().toJson(new ArrayList<>()), HttpStatus.NO_CONTENT);
            }

            Repository repository = new Repository(repositoryPojo.get());
            Optional<ActorPojo> actorPojo = actorsPojo.actors.stream()
                    .filter(actor -> actor.id.equals(repository.owner.getId()))
                    .findFirst();

            if (actorPojo.isPresent()) {
                ActorPojo actor = actorPojo.get();
                repository.setOwner(new Actor(actor.id, actor.type, actor.login, actor.avatar_url));

                return new ResponseEntity<>(new Gson().toJson(repository), HttpStatus.OK);
            } else {
                return serverErrorResponse();
            }
        } catch (IOException e) {
            return serverErrorResponse();
        }
    }

    private String readFile(String fileName) throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());

        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    private RepositoriesPojo parseStringJsonToRepositoriesPojo(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, RepositoriesPojo.class);
    }

    private ActorsPojo parseStringJsonToActor(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ActorsPojo.class);
    }

    private List<RepositoryPojo> filterRepositoryPojoByParam(String param, RepositoriesPojo repositoriesPojo) {
        return repositoriesPojo.repositories.stream()
                .filter(repositoryPojo ->  repositoryPojo.isNameRepositoryEqualsParam(param))
                .collect(Collectors.toList());
    }

    private ArrayList<SimplifiedRepository> parseRepositoryPojoToSimplifiedRepository(List<RepositoryPojo> pojoRepositories) {
        ArrayList<SimplifiedRepository> repositories = new ArrayList<>();

        pojoRepositories.forEach(repositoryPojo -> repositories.add(new SimplifiedRepository(repositoryPojo)));

        return repositories;
    }

    private ResponseEntity<String> serverErrorResponse() {
        Map<String, Object> map = Map.ofEntries(Map.entry(Constants.KEY_MESSAGE, Constants.SERVER_ERROR_MESSAGE));

        return new ResponseEntity<>(new Gson().toJson(map), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}