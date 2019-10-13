import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private BotData botData = new BotData();

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        long chat_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        setKeyboard(sendMessage);
        sendMessage.setText(AnswerOnMessage.getAnswer(update.getMessage().getText(), sendMessage));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return botData.botUserName;
    }
    @Override
    public String getBotToken() {
        return botData.botToken;
    }

    private void setKeyboard(SendMessage sendMsg){
        GameLogic.setStandardKeyboard(sendMsg, replyKeyboardMarkup);
    }
}
