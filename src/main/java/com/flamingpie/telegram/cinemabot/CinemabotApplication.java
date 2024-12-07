package com.flamingpie.telegram.cinemabot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemabotApplication extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        SpringApplication.run(CinemabotApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CinemabotApplication.class);
    }

    @Bean
    public TelegramBot getTelegramBot() throws IOException {
        TelegramBot telegramBot = new TelegramBot("7942406818:AAH1yz1wF_ixvb4VEpzOHaRJ85GpgCz1FME");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("public.pem");
        SetWebhook request = new SetWebhook()
                .url("https://195.133.196.198:443/cinemabot/webhook")
                .certificate(inputStream.readAllBytes())
                .dropPendingUpdates(true);
        telegramBot.execute(request);
        return telegramBot;
    }
    
    @Bean
    public OkHttpClient getHttpClient()
    {
        return new OkHttpClient();
    }
}
