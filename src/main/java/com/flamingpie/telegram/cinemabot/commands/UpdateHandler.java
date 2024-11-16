/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.commands;

import com.flamingpie.telegram.cinemabot.controllers.LetterboxdController;
import com.flamingpie.telegram.cinemabot.letterboxd.FilmSearchItem;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResult;
import com.pengrad.telegrambot.model.request.InlineQueryResultPhoto;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class UpdateHandler {

    @Autowired
    private TelegramBot bot;

    @Autowired
    private LetterboxdController letterBoxdController;

    public int process(Update update) {
        if (update.inlineQuery() != null) {
            processInlineQuery(update);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public int processInlineQuery(Update update) {
        InlineQuery inlineQuery = update.inlineQuery();

        if (inlineQuery.query() != null && !inlineQuery.query().isBlank()) {
            try {
                List<FilmSearchItem> searchResult = letterBoxdController.searchFilm(inlineQuery.query().trim());
                if (searchResult.isEmpty()) {
                    bot.execute(new AnswerInlineQuery(inlineQuery.id()));
                    return update.updateId();
                }

                int arraySize = searchResult.size() > 5 ? 5 : searchResult.size();
                InlineQueryResult[] result = new InlineQueryResult[arraySize];
                for (int i = 0; i < arraySize; i++) {
                    FilmSearchItem searchItem = searchResult.get(i);
                    String posterUrl;
                    String thumbPosterUrl;
                    if (searchItem.getFilm().getPoster() == null) {
                        posterUrl = null;
                        thumbPosterUrl = null;
                    } else {
                        posterUrl = searchItem.getFilm().getPoster().getSizes().getLast().getUrl();
                        thumbPosterUrl = searchItem.getFilm().getPoster().getSizes().getFirst().getUrl();
                    }
                    String title = "%s (%s) Rating: %s".formatted(searchItem.getFilm().getName(), 
                            searchItem.getFilm().getReleaseYear(), 
                            searchItem.getFilm().getRatingStars());
                    
                    String caption = title + "\nGenres: " + searchItem.getFilm().getGenresString();

                    InlineQueryResult queryResult = new InlineQueryResultPhoto(String.valueOf(i),
                            posterUrl,
                            thumbPosterUrl)
                            .photoWidth(600)
                            .photoHeight(900)
                            .description(title)
                            .title(title)
                            .caption(caption);

                    result[i] = queryResult;
                }

                bot.execute(new AnswerInlineQuery(inlineQuery.id(), result));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return update.updateId();
    }

}
