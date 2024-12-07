/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.factories;

import com.flamingpie.telegram.cinemabot.letterboxd.AbstractSearchItem;
import com.flamingpie.telegram.cinemabot.letterboxd.FilmSearchItem;
import com.pengrad.telegrambot.model.request.InlineQueryResult;
import com.pengrad.telegrambot.model.request.InlineQueryResultPhoto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class InlineQueryResultFactory {

    public <T extends AbstractSearchItem> InlineQueryResult createInlineQueryResult(T searchItem) {
        switch (searchItem) {
            case FilmSearchItem filmSearchItem -> {
                return buildFilmSearchResult(filmSearchItem);
            }
            default ->
                throw new IllegalArgumentException("Unknown type of SearchItem");
        }
    }

    private InlineQueryResult buildFilmSearchResult(FilmSearchItem filmSearchItem) {
        String posterUrl;
        String thumbPosterUrl;
        if (filmSearchItem.getFilm().getPoster() == null) {
            posterUrl = null;
            thumbPosterUrl = null;
        } else {
            posterUrl = filmSearchItem.getFilm().getPoster().getSizes().getLast().getUrl();
            thumbPosterUrl = filmSearchItem.getFilm().getPoster().getSizes().getFirst().getUrl();
        }
        String title = "%s (%s) Rating: %s".formatted(filmSearchItem.getFilm().getName(),
                filmSearchItem.getFilm().getReleaseYear(),
                filmSearchItem.getFilm().getRatingStars());

        String caption = title + "\nGenres: " + filmSearchItem.getFilm().getGenresString();

        InlineQueryResult queryResult = new InlineQueryResultPhoto(
                filmSearchItem.getFilm().getId(),
                posterUrl,
                thumbPosterUrl)
                .photoWidth(600)
                .photoHeight(900)
                .description(title)
                .title(title)
                .caption(caption);

        return queryResult;
    }
}
