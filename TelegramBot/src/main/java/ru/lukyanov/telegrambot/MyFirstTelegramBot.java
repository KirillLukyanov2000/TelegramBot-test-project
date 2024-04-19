package ru.lukyanov.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ru.lukyanov.telegrambot.Buttons.*;
import static ru.lukyanov.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "kirs2000_bot"; // bot name
    public static final String TOKEN = "7080101107:AAFfLQe4DkFiZUAzSp0SZ2ejNkj2w20bKtk"; //bot token

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        //test version:
        //super.onUpdateEventReceived(updateEvent);

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Количество очков славы: " + getUserGlory());
            if (getUserGlory() < 230) {
                sendTextMessageAsync("Игра ещё не закончена, продолжай дальше!");
                sendTextMessageAsync("Пройди игру до конца, снова запроси очки славы и тебя будет ждать сюрприз от меня. Мяу!");
            } else {
                sendTextMessageAsync("Ты заработал максимальное количество очков славы! Победа!");
                sendTextMessageAsync("Порадуй кота, отправь сообщение, содержащее *мяу*, и я тебе добавлю сердечек в моем последнем сообщении!\n");
            }
        }
        if (getMessageText().equals("/start") || getMessageText().contains("start") || getMessageText().contains("Start") || getMessageText().equals("Начать") || getMessageText().contains("начать")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", STEP_1_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_1_BUTTON)) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! +20 славы", STEP_2_BUTTON, "Взять рыбку! +20 славы", STEP_2_BUTTON, "Сбросить банку с огурцами! +20 славы", STEP_2_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_2_BUTTON)) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", STEP_3_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_3_BUTTON)) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить робопылесос за едой! +30 славы", STEP_4_BUTTON, "Проехаться на робопылесосе! +30 славы", STEP_4_BUTTON, "Убегать от робопылесоса! +30 славы", STEP_4_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_4_BUTTON)) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", STEP_5_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_5_BUTTON)) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Бегать по крышам! +40 славы", STEP_6_BUTTON, "Нападать на других котов! +40 славы", STEP_6_BUTTON, "Нападать на собак из засады! +40 славы", STEP_6_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_6_BUTTON)) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", STEP_7_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_7_BUTTON)) {
            sendPhotoMessageAsync("step_8_pic");
            addUserGlory(50);
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор", STEP_8_BUTTON));
        }
        if (getCallbackQueryButtonKey().equals(STEP_8_BUTTON)) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }
        if ((getMessageText().contains("мяу") || getMessageText().contains("Мяу")) && getUserGlory() == 230) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText() +
                    HEARTS_TEXT);
            sendImageMessageAsync("C:\\Users\\tayga\\Downloads\\Template TelegramBot Marathon 1.1\\TelegramBot\\src\\main\\resources\\images\\lovely_cat.jpg");
        }
        if ((getMessageText().contains("мяу") || getMessageText().contains("Мяу")) && getUserGlory() < 230) {
            sendTextMessageAsync("Очков славы недостаточно, чтобы получить подарок от кота");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
