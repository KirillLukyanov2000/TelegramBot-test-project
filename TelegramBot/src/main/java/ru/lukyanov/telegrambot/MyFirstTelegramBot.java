package ru.lukyanov.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "kirs2000_bot"; // bot name
    public static final String TOKEN = ""; //bot token

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {

        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Hello from IDEA! " + "Some *bold text* added and " + "Some _cursive text_ added");
            sendTextMessageAsync("Your fav pet?",
                    Map.of("Cat", "cmnd1_cat", "Dog", "cmnd2_dog"));
        }
        if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("*Bye-Bye*, _Asta la vista, baby!_");
        }
        if (getMessageText().equals("How are you?")) {
            sendTextMessageAsync("Super");
        }
        if (getMessageText().contains("weather") || getMessageText().contains("Weather")) {
            sendTextMessageAsync("I'm not a weather forecast chat-bot");
        }
        if (getMessageText().contains("picture") || getMessageText().contains("Picture")) {
            sendPhotoMessageAsync("final_pic");
        }
        if (getMessageText().contains("cat")
                || getMessageText().contains("Cat")
                || getMessageText().contains("Dog")
                || getMessageText().contains("dog")) {
            sendTextMessageAsync("Choose Cat or Dog picture: ",
                    Map.of("Cat", "cmnd1_cat", "Dog", "cmnd2_dog"));
        }
        if (getCallbackQueryButtonKey().equals("cmnd1_cat")) {
            sendPhotoMessageAsync("step_4_pic");
        }
        if (getCallbackQueryButtonKey().equals("cmnd2_dog")) {
            sendPhotoMessageAsync("step_6_pic");
        }
        if (getMessageText().equals("smile")) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText() + " :))");
        }

    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
