import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

class AnswerOnMessage {
    private static boolean isPlaying;
    private static Game game = new Game();
    private static GameLogic gameLogic = new GameLogic();
    private static String word;


    static String getAnswer(String msg, SendMessage sendMessage, Update update) {
        if (isPlaying){
            if (msg.contains("/stop")) {
                isPlaying = false;
                return "Игра остановлена";
            }
            gameLogic.setNewKeyboard(sendMessage, Character.toString(msg.charAt(0)));
            if (word.contains(msg)) {
                if (gameLogic.answerMessage(word).contains(word)) {
                    isPlaying = false;
                    gameLogic.setStandardKeyboard(sendMessage);
                }
                return gameLogic.answerMessage(word);
            }
            else{
                return "Неверная буква!";
            }
        }
        else {
            if (msg.toLowerCase().contains("hello") || msg.toLowerCase().contains("hi") || msg.toLowerCase().contains("привет")) {
                return "Приветствую тебя, друг мой, чего желаешь?";
            }
            if (msg.contains("Каво?")) {
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
                return "Поздоровался бы хоть...";
            }
            return "Game";
        }
    }
}
