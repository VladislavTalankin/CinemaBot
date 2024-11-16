/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import java.io.File;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class CinemaBot extends TelegramBot {

    public CinemaBot() {
        super("7942406818:AAH1yz1wF_ixvb4VEpzOHaRJ85GpgCz1FME");
        try {
            File certificate = new File(getClass().getClassLoader().getResource("public.pem").getFile());
            SetWebhook request = new SetWebhook()
                    .url("https://5.43.153.114:443/webhook")
                    .certificate(certificate)
                    .dropPendingUpdates(true);
            execute(request).toString();
        } catch (NullPointerException ex) {
            System.err.println("Certificate isn't found!");
        }
    }

}
