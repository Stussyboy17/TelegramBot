import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class GameLogic {

    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private List<String> letters;
    private List<String> deletedLetters;
    private int lives;
    boolean isPlaying;

    GameLogic(){
        letters = getLetters();
        deletedLetters = new ArrayList<>();
        lives = 8;
        isPlaying = true;
    }

    void setNewKeyboard(SendMessage sendMessage, String letter){
        letters.remove(letter);
        deletedLetters.add(letter);
        setReplyKeyboardMarkup(sendMessage);
    }

    String answerMessage(String word, String message){
        System.out.println(isPlaying);
        StringBuilder unknownWord = new StringBuilder();
        unknownWord.append("➖".repeat(word.length()));
        for (int i = 0; i < word.length(); i++){
            for (String deletedLetter : deletedLetters)
                if (word.charAt(i) == deletedLetter.charAt(0)) {
                    unknownWord.setCharAt(i, deletedLetter.charAt(0));
                }
        }
        if (!unknownWord.toString().contains(message)) {
            lives--;
        }
        StringBuilder answer = new StringBuilder();
        States states = new States();
        answer.append(states.states.get(lives));
        answer.append("\n");
        answer.append("Слово: ");
        if (lives == 0){
            answer.append("Загаданное слово - ");
            answer.append(word);
            answer.append("\n");
            answer.append("Ты проиграл!!!");
            setAfterGameKeyboard();
            isPlaying = false;
            return answer.toString();
        }
        if (unknownWord.toString().equals(word)){
            answer.append(word);
            answer.append("\n");
            answer.append("Ты выиграл, поздравляю!!!");
            setAfterGameKeyboard();
            isPlaying = false;
            return answer.toString();
        }
        answer.append(unknownWord);
        return answer.toString();
    }

    private void setAfterGameKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Сыграем ещё!"));
        keyboardFirstRow.add(new KeyboardButton("Я больше не хочу играть"));
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    String startGame(SendMessage sendMessage, String word){
        setReplyKeyboardMarkup(sendMessage);
        States states = new States();
        return states.states.get(8) +
                "\n" +
                "Слово: " +
                "➖".repeat(word.length());
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
        List<KeyboardRow> keyboard = new ArrayList<>();
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


    static void setStandardKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup, SendMessage sendMessage) {
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
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
