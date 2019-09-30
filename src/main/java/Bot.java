import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        if (update.getMessage().getText().equals("Привет")) {
            sendMessage.setText("Hello, my friend!");
            try {
                execute(sendMessage);
            }catch (TelegramApiException e) {
                e.printStackTrace();
            }
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
}
