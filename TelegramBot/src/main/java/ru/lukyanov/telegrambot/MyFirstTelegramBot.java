package ru.lukyanov.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "kirs2000_bot"; // bot name
    public static final String TOKEN = "xxx"; //bot token

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {

        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Hello from IDEA! " + "Some *bold text* added and " + "Some _cursive text_ added");
        }
        if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("*Bye-Bye*");
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
        if (getMessageText().contains("cat") || getMessageText().contains("Cat")) {
            sendTextMessageAsync("Choose number of the Cat picture: ",
                    Map.of("Cat 1", "command1", "Cat 2", "command2"));
        }
        if (getCallbackQueryButtonKey().equals("command1")) {
            sendPhotoMessageAsync("step_1_pic");
        }
        if (getCallbackQueryButtonKey().equals("command2")) {
            sendPhotoMessageAsync("step_2_pic");
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
