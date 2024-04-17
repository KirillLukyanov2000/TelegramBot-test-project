package ru.lukyanov.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ru.lukyanov.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "kirs2000_bot"; // bot name
    public static final String TOKEN = ""; //bot token

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        //test version:
        //super.onUpdateEventReceived(updateEvent);

        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! +20 славы", "step_2_btn", "Взять рыбку! +20 славы", "step_2_btn", "Сбросить банку с огурцами! +20 славы", "step_2_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить робопылесос за едой! +30 славы", "step_4_btn", "Проехаться на робопылесосе! +30 славы", "step_4_btn", "Убегать от робопылесоса! +30 славы", "step_4_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Бегать по крышам! +40 славы", "step_6_btn", "Нападать на других котов! +40 славы", "step_6_btn", "Нападать на собак из засады! +40 славы", "step_6_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор", "step_8_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendTextMessageAsync(FINAL_TEXT);
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
