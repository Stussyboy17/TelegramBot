import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

class AnswerOnMessage {
    private static boolean isPlaying;
    private static Game game = new Game();
    private static GameLogic gameLogic = new GameLogic();
    private static String word;


    static String getAnswer(String msg, SendMessage sendMessage) {
        if (isPlaying){
            if (msg.contains("/stop")) {
                isPlaying = false;
                return "Игра остановлена";
            }
            if (gameLogic.isPlaying) {
                String message = Character.toString(msg.charAt(0)).toUpperCase();
                gameLogic.setNewKeyboard(sendMessage, message);
                return gameLogic.answerMessage(word, message);
            }
            if (msg.contains("Я больше не хочу играть")) {
                Bot.setKeyboard(sendMessage);
                isPlaying = false;
                return "Ну ладно, пиши, если что;)";
            }
            if (msg.contains("Сыграем ещё!")) {
                word = game.getRandomWord();
                gameLogic = new GameLogic();
                isPlaying = true;
                return gameLogic.startGame(sendMessage, word);
            }
            return "";
        }
        else {
            if (msg.toLowerCase().contains("hello") || msg.toLowerCase().contains("hi") || msg.toLowerCase().contains("привет")) {
                Bot.setKeyboard(sendMessage);
                return "Приветствую тебя, друг мой, чего желаешь?";
            }
            if (msg.contains("Каво?")) {
                Bot.setKeyboard(sendMessage);
                return "Это простой бот, который пока что может играть с Вами в виселицу, остальное будет позже";
            }
            if (msg.contains("Сыграем в виселицу") || msg.contains("/game")) {
                word = game.getRandomWord();
                gameLogic = new GameLogic();
                isPlaying = true;
                return gameLogic.startGame(sendMessage, word);
            }
            if (msg.contains("Проверить делишки бота")) {
                return "У меня все хорошо, спасибо, что интересуетесь♥";
            }
            if (msg.contains("/start")) {
                Bot.setKeyboard(sendMessage);
                return "Поздоровался бы хоть...";
            }
            return "Я не понимаю, чего именно ты хочешь...?";
        }
    }
}
