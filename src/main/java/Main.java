import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main (String[] args) throws TelegramApiRequestException {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", "9150");

        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();

        Bot bot = new Bot();
        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
