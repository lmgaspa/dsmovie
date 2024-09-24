package com.devsuperior.dsmovie.clients;

import com.devsuperior.dsmovie.responses.MovieResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TMDBClient {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/movie/popular?language=pt-BR&page=1"))
            .header("accept", "application/json")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZDUxM2IzMTJjYjM4ZGQzNTYwMzhjZjRkNzcwMjQ3OSIsIm5iZiI6MTcyNjk2MDM3MC44MDg1ODYsInN1YiI6IjY2ZWY0NTgxNzMwMGE1YmEyMTNiMmRhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Epk2KZmg67pM-H0ldUyD_ZofnzjUV8HddGaImnDQw0Y")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

    public MovieResponse getMovies() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), MovieResponse.class);
    }
}

@Component
class Apiremota {
    @PostConstruct
    public void getApi() throws IOException, InterruptedException {
        var response = new TMDBClient().getMovies();
        System.out.println(response);
    }
}