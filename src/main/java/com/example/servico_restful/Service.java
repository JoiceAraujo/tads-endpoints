package com.example.servico_restful;

import com.example.servico_restful.PojoModels.RepositoryPojo;
import com.example.servico_restful.PojoModels.RepositoriesPojo;
import com.example.servico_restful.ResponseModels.SimplifiedRepository;
import com.example.servico_restful.RouteParamsModels.GetRepositoriesRequest;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: Fazer uma resposta para rotas não encontradas
// TODO: Tratar quando não passa nenhum parâmetro?
@RestController
@ResponseBody
@RequestMapping("/repos")
public class Service {

    @RequestMapping(method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "/find")
    public @ResponseBody ResponseEntity getRepositories(@RequestBody GetRepositoriesRequest requestParams) throws IOException {
        if(requestParams.getNome().isEmpty() || requestParams.getNome().isBlank()) {
            Map<String, Object> map = Map.ofEntries(Map.entry("mensagem", "Nome é obrigatório"));

            return new ResponseEntity<>(new Gson().toJson(map), HttpStatus.BAD_REQUEST);
        }

        try {
            String stringJson = readFile("repositories_202305081745.json");
            RepositoriesPojo repositoriesPojo = parseStringJsonToRepositoriesPojo(stringJson);
            List<RepositoryPojo> filteredRepositories = filterRepositoryPojoByParam(requestParams.getNome(), repositoriesPojo);

            if(filteredRepositories.isEmpty()) {
                return new ResponseEntity<>(new Gson().toJson(new ArrayList<>()), HttpStatus.NO_CONTENT);
            }

            ArrayList<SimplifiedRepository> simplifiedRepositories = parseRepositoryPojoToSimplifiedRepository(filteredRepositories);
            // TODO: Colocar números mágicos em constantes
            if(requestParams.getPagina() != 0 || requestParams.getPorPagina() != 0) {
                // TODO: implementar a paginação
            }

            return new ResponseEntity<>(new Gson().toJson(simplifiedRepositories), HttpStatus.OK);
        } catch (IOException e) {
            Map<String, Object> map = Map.ofEntries(Map.entry("mensagem", "Ocorreu um erro em nossos servidores. Estamos trabalhando nisso!"));

            return new ResponseEntity<>(new Gson().toJson(map), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String readFile(String fileName) throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());
        String content =  FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        return content;
    }

    private RepositoriesPojo parseStringJsonToRepositoriesPojo(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, RepositoriesPojo.class);
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
}