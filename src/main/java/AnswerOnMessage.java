public class AnswerOnMessage {
    public static String getAnswer(String msg) {
        if( msg.toLowerCase().contains("hello") || msg.toLowerCase().contains("hi") || msg.toLowerCase().contains("привет")){
            return "Приветствую тебя, друг мой, чего желаешь?";
        }
        if (msg.contains("Каво?")) {
            return "Это простой бот, который пока что может дублировать Ваши сообщения, остальное будет позже";
        }
        if (msg.contains("[В разработке]")) {
            return "Этот раздел пока в разработке, попробуйте что-нибудь другое";
        }
        if (msg.contains("Проверить делишки бота")) {
            return "У меня все хорошо, спасибо, что интересуетесь♥";
        }
        if (msg.contains("/start")){
            return "Поздоровался бы хоть...";
        }
        return msg;
    }
}
