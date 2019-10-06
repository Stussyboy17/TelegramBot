import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private long chat_id;
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        chat_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setText(input(update.getMessage().getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "@coolshortgamebot";
    }
    @Override
    public String getBotToken() {
        return "884804841:AAFL7evBxdUw-RvWM-7G9In6GbB_jp4M7Vk";
    }

    public String input(String msg) {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        if( msg.toLowerCase().contains("hello") || msg.toLowerCase().contains("hi") || msg.toLowerCase().contains("привет")){
            keyboardFirstRow.add(new KeyboardButton("Проверить делишки бота"));
            keyboardFirstRow.add(new KeyboardButton("[В разработке]"));
            keyboardSecondRow.add(new KeyboardButton("Каво?"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Приветствую тебя, друг мой, чего желаешь?";
        }
        if (msg.contains("Каво?")){
            return "Это простой бот, который пока что может дублировать Ваши сообщения, остальное будет позже";
        }
        if (msg.contains("[В разработке]")) {
            return "Этот раздел пока в разработке, попробуйте что-нибудь другое";
        }
        if (msg.contains("Проверить делишки бота")) {
            return "У меня все хорошо, спасибо, что интересуетесь♥";
        }
        return msg;
    }
}
