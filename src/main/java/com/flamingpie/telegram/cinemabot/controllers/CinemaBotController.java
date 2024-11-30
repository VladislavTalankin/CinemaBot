package com.flamingpie.telegram.cinemabot.controllers;

import com.flamingpie.telegram.cinemabot.commands.UpdateHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.utility.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaBotController {

    @Autowired
    private UpdateHandler handler;

    @PostMapping(value = "/webhook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int webhook(@RequestBody String updateJson) {
        Update update = BotUtils.parseUpdate(updateJson);
        return handler.process(update);
    }

}
