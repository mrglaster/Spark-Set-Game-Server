package Game;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.Random;

public class GameOperations {
    public static JsonArray gameField;
    public static JsonArray currentGameField = null;

    /**Generates field*/
    public static void generateField(){
        int currentId = 0;
        gameField = new JsonArray();
        for (int count = 1; count < 4; count++ ){
            for (int fill = 1; fill < 4; fill++ ){
                for (int shape = 1; shape < 4; shape++){
                    for (int color = 1; color < 4; color++){
                        Card newCard = new Card(currentId, color, shape, fill, count);
                        gameField.add(newCard.toJson());
                        currentId++;
                    }
                }
            }
        }
        Collections.shuffle(gameField.asList());
        currentGameField = new JsonArray();
        for (int i = 0; i < 12; i++){
            currentGameField.add(gameField.get(i));
            gameField.remove(i);
        }
    }

    /**Returns current game field*/
    public static JsonArray getCurrentGameField(){
        if (currentGameField == null) generateField();
        return currentGameField;
    }


    /**Checks if current cards set is a set*/
    public static boolean isSet(Card card1, Card card2, Card card3) {
        if (card1.getId() == card2.getId() || card1.getId() == card3.getId() || card2.getId() == card3.getId()) return false;
        if (!checkSetProperty(card1.getColor(), card2.getColor(), card3.getColor()) || !checkSetProperty(card1.getShape(), card2.getShape(), card3.getShape()) || !checkSetProperty(card1.getFill(), card2.getFill(), card3.getFill())) {
            return false;
        }
        return checkSetProperty(card1.getCount(), card2.getCount(), card3.getCount());
    }

    /**Checks if 3 input cards are sets by their ids*/
    public static boolean isSetById(int idOne, int idTwo, int idThree){
        if (idOne < 0 || idTwo < 0 || idThree < 0) return false;
        if (idOne == idTwo || idOne == idThree || idTwo == idThree) return false;
        Card one = null;
        Card two = null;
        Card three = null;
        for (var i : currentGameField){
            int currentId = i.getAsJsonObject().get("id").getAsInt();
            if (currentId == idOne) one = Card.fromJsonObject(i.getAsJsonObject());
            else if (currentId == idTwo) two = Card.fromJsonObject(i.getAsJsonObject());
            else if (currentId == idThree) three =  Card.fromJsonObject(i.getAsJsonObject());
            else if (one != null && two != null & three != null) break;
        }
        if (one == null || two == null || three == null) return false;
        return  isSet(one, two, three);
    }


    /**Method checking properties for cards*/
    private static boolean checkSetProperty(int prop1, int prop2, int prop3) {
        return (prop1 == prop2 && prop2 == prop3) || (prop1 != prop2 && prop1 != prop3 && prop2 != prop3);
    }

    /**Checks if there is the set in the current game field*/
    public  static boolean isSetInCurrent(){
        for (int i = 0; i < currentGameField.size() - 2; i++){
            for (int j = 1; j < currentGameField.size() - 1; j++){
                for (int k = 2; k < currentGameField.size(); k++){
                    if (isSet(Card.fromJsonObject((JsonObject) currentGameField.get(i)), Card.fromJsonObject((JsonObject) currentGameField.get(j)), Card.fromJsonObject((JsonObject) currentGameField.get(k)))){
                        System.out.println("HINT: ");
                        System.out.println("ID " + Card.fromJsonObject((JsonObject) currentGameField.get(i)).getId());
                        System.out.println("ID " + Card.fromJsonObject((JsonObject) currentGameField.get(j)).getId());
                        System.out.println("ID " + Card.fromJsonObject((JsonObject) currentGameField.get(k)).getId());
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**Returns status of the game*/
    public static String getGameStatus(){
        if (gameField.size() == 0 && !isSetInCurrent()) return "ended";
        return "ongoing";
    }

    /**Checks if the user can add cards to the current deck*/
    public static boolean canAdd(){
        return !(gameField.size() == 0 || currentGameField.size() >= 21);
    }

    /**Adds cards to the game field if it's possible*/
    public static void addCards(){
        if (canAdd()){
            Gson gson = new Gson();
            int[] randomIndexes = new Random().ints(0, gameField.size()).distinct().limit(3).toArray();
            for (int i = 0; i < randomIndexes.length; i++) {
                JsonElement element = gameField.get(randomIndexes[i]);
                currentGameField.add(element);
                gameField.remove(element);
            }
        }
    }
}
