import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.print.Book;

public class Bot extends TelegramLongPollingBot {

    Book book = new Book();
    private long chat_id;

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();
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

    public String input(String msg){
        if(msg.contains("Hello") || msg.contains("Hi") || msg.contains("Привет")){
            return "Hello, my friend!";
        }
        return msg;
    }
}
