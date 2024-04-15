package ru.lukyanov.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "kirs2000_bot"; // bot name
    public static final String TOKEN = "xxx"; //bot token

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        sendTextMessageAsync("Hello from IDEA!");
        sendTextMessageAsync("Some *bold text* added");
        sendTextMessageAsync("Some _cursive text_ added");

    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
