/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.services;

import com.flamingpie.telegram.cinemabot.controllers.LetterboxdController;
import com.flamingpie.telegram.cinemabot.factories.InlineQueryResultFactory;
import com.flamingpie.telegram.cinemabot.letterboxd.FilmSearchItem;
import com.flamingpie.telegram.cinemabot.letterboxd.SearchResponse;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResult;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.response.BaseResponse;
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
    
    @Autowired
    private InlineQueryResultFactory resultFactory;

    public int process(Update update) {
        processInlineQuery(update.inlineQuery());
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public BaseResponse processInlineQuery(InlineQuery inlineQuery) {
        if (inlineQuery == null) {
            return null;
        }

        SearchResponse<FilmSearchItem> searchResponse = letterBoxdController.searchItem(FilmSearchItem.class, inlineQuery.query());
        List<FilmSearchItem> searchResultList = searchResponse.getItems();
        if (searchResultList.isEmpty()) {
            return bot.execute(new AnswerInlineQuery(inlineQuery.id()));
        }

        int arraySize = searchResultList.size() > 5 ? 5 : searchResultList.size();
        InlineQueryResult[] result = new InlineQueryResult[arraySize];
        for (int i = 0; i < arraySize; i++) {
            result[i] = resultFactory.createInlineQueryResult(searchResultList.get(i));
        }

        return bot.execute(new AnswerInlineQuery(inlineQuery.id(), result));
    }

}
