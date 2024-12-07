package com.flamingpie.telegram.cinemabot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flamingpie.telegram.cinemabot.letterboxd.AbstractSearchItem;
import com.flamingpie.telegram.cinemabot.letterboxd.SearchResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LetterboxdController {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private ObjectMapper mapper;

    public <T extends AbstractSearchItem> SearchResponse<T> searchItem(Class<T> searchItem, String searchString) {
        if (searchString == null || searchString.isBlank()) {
            return new SearchResponse();
        }
        
        StringBuilder requestString = new StringBuilder("https://api.letterboxd.com/api/v0/search?input=")
                .append(searchString.trim())
                .append("&searchMethod=")
                .append("Autocomplete")
                .append("&include=")
                .append(searchItem.getSimpleName());
        
        Request request = new Request.Builder()
                .url(requestString.toString())
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.body() == null) {
                return new SearchResponse();
            }
            String content = response.body().string();
            return mapper.readValue(content, SearchResponse.class);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(LetterboxdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LetterboxdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new SearchResponse();
    }
}
