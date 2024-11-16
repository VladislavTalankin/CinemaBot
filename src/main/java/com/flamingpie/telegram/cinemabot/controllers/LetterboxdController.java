package com.flamingpie.telegram.cinemabot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.flamingpie.telegram.cinemabot.letterboxd.FilmSearchItem;
import com.flamingpie.telegram.cinemabot.letterboxd.SearchResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LetterboxdController {

    private final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public List<FilmSearchItem> searchFilm(String name) throws IOException {
        if (name == null || name.isBlank() || name.length() < 2) {
            return new ArrayList();
        }

        Request request = new Request.Builder()
                .url("https://api.letterboxd.com/api/v0/search?input=" + name + "&searchMethod=Autocomplete&include=FilmSearchItem")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                return new ArrayList();
            }

            String body = response.body().string();

            if (body == null) {
                return new ArrayList();
            }

            SearchResponse searchResponse = MAPPER.readValue(body, SearchResponse.class);

            return (List<FilmSearchItem>) searchResponse.getItems();
        }
    }
}
