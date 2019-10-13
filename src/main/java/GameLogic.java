import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class GameLogic {

    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private List<String> letters;
    private List<String> rightLetters;

    GameLogic(){
        letters = getLetters();
        rightLetters = new ArrayList<>();
    }

    void setNewKeyboard(SendMessage sendMessage, String letter){
        letters.remove(letter);
        rightLetters.add(letter);
        setReplyKeyboardMarkup(sendMessage);
    }

    String answerMessage(String word){
        StringBuilder answer = new StringBuilder();
        answer.append("➖".repeat(word.length()));
        for (int i = 0; i < word.length(); i++){
            for (String rightLetter : rightLetters)
                if (word.charAt(i) == rightLetter.charAt(0))
                    answer.setCharAt(i, rightLetter.charAt(0));
        }
        if (answer.toString().equals(word)){
            answer.append("\n");
            answer.insert(0, "Загаданное слово - ");
            answer.append("Ты выиграл, поздравляю!!!");
            return answer.toString();
        }
        answer.insert(0,"Слово: ");
        return answer.toString();
    }

    String startGame(SendMessage sendMessage, String word){
        setReplyKeyboardMarkup(sendMessage);

        StringBuilder answer = new StringBuilder();
        answer.append("Слово: ");
        for (int i = 0; i < word.length(); i++){
            answer.append("➖");
        }
        return answer.toString();
    }

    private List<String> getLetters(){
        List<String> answer = new ArrayList<>();
        for(int i = 0; i < 32; i++) {
            answer.add(Character.toString('А' + i));
        }
        return answer;
    }

    private void setReplyKeyboardMarkup(SendMessage sendMessage){
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for(int i = 0; i < letters.size(); i++){
            keyboardRow.add(letters.get(i));
            if ((i + 1) % (letters.size()/ 3 + 1) == 0){
                keyboard.add(keyboardRow);
                keyboardRow = new KeyboardRow();
            }
        }
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    void setStandardKeyboard(SendMessage sendMessage){
        setStandardKeyboard(sendMessage, replyKeyboardMarkup);
    }

    static void setStandardKeyboard(SendMessage sendMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Проверить делишки бота"));
        keyboardFirstRow.add(new KeyboardButton("Сыграем в виселицу"));
        keyboardSecondRow.add(new KeyboardButton("Каво?"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
