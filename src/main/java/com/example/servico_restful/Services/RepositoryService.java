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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@ResponseBody()
@RequestMapping("/repos")
public class RepositoryService {
    @RequestMapping(method = RequestMethod.GET, value = "/find", produces="application/json")
    public @ResponseBody ResponseEntity<String> getRepositories(
            @RequestParam(name = "nome") String repositoryName,
            @RequestParam(name = "pagina", required = false, defaultValue = Constants.PAGE_DEFAULT_VALUE) int page,
            @RequestParam(name = "por_pagina", required = false, defaultValue = Constants.PER_PAGE_DEFAULT_VALUE) int perPage
            ) {
        if(repositoryName != null && (repositoryName.isEmpty() || repositoryName.isBlank())) {

            return badRequest(Constants.REQUIRED_NAME_PARAM_ERROR);
        }

        if(repositoryName.length() < Constants.MIN_SEARCH_TERM_LENGTH) {
            return badRequest(Constants.MIN_LENGTH_NAME_PARAM_ERROR);
        }

        if(perPage > Constants.MAX_PER_PAGE_LENGTH) {
            return badRequest(Constants.MAX_LENGTH_PER_PAGE_PARAM_ERROR);
        }

        try {
            String stringJson = readFile(Constants.JSON_REPOSITORIES_FILE_NAME);
            RepositoriesPojo repositoriesPojo = parseStringJsonToRepositoriesPojo(stringJson);
            List<RepositoryPojo> filteredRepositories = filterRepositoryPojoByParam(repositoryName, repositoriesPojo);

            if(filteredRepositories.isEmpty()) {
                return noContent();
            }

            ArrayList<SimplifiedRepository> simplifiedRepositories = parseRepositoryPojoToSimplifiedRepository(filteredRepositories);

            Pagination<SimplifiedRepository> pagination = new Pagination<SimplifiedRepository>(simplifiedRepositories);

            ArrayList<SimplifiedRepository> paginatedList = new ArrayList<SimplifiedRepository>(pagination.getItemsByPage(page, perPage));

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

            Optional<RepositoryPojo> repositoryPojo = filterRepositoriesPojoById(repositoriesPojo, repoId);
            if(repositoryPojo.isEmpty()) return noContent();

            Repository repository = new Repository(repositoryPojo.get());
            Optional<ActorPojo> actorPojo = findActorPojoByRepositoryOwnerId(actorsPojo, repository.getOwner().getId());

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

    private Optional<ActorPojo> findActorPojoByRepositoryOwnerId(ActorsPojo actorsPojo, String repositoryOwnerId) {
        return actorsPojo.actors.stream()
                .filter(actor -> actor.id.equals(repositoryOwnerId))
                .findFirst();
    }

    private Optional<RepositoryPojo> filterRepositoriesPojoById(RepositoriesPojo repositoriesPojo, String repoId) {
        return repositoriesPojo.repositories.stream()
                .filter(repository ->  repository.id.equals(repoId))
                .findFirst();
    }

    private @ResponseBody ResponseEntity<String> badRequest(String error) {
        Map<String, Object> map = Map.ofEntries(Map.entry(Constants.KEY_MESSAGE, error));

        return new ResponseEntity<>(new Gson().toJson(map), HttpStatus.BAD_REQUEST);
    }

    private @ResponseBody ResponseEntity<String> noContent() {
        return new ResponseEntity<>(new Gson().toJson(new ArrayList<>()), HttpStatus.NO_CONTENT);
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