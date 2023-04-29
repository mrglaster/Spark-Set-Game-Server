package Game;

import com.google.gson.JsonArray;

import java.util.Collections;

public class GameOperations {
    public static JsonArray gameField;
    public static JsonArray currentGameField;
    public static JsonArray generateField(){
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
        JsonArray currentField = new JsonArray();
        for (int i = 0; i < 12; i++){
            currentField.add(gameField.get(i));
            gameField.remove(i);
        }
        return currentField;
    }


    /**Checks if current cards set is a set*/
    public static boolean isSet(Card card1, Card card2, Card card3) {
        if (!checkSetProperty(card1.getColor(), card2.getColor(), card3.getColor()) || !checkSetProperty(card1.getShape(), card2.getShape(), card3.getShape()) || !checkSetProperty(card1.getFill(), card2.getFill(), card3.getFill())) {
            return false;
        }
        return checkSetProperty(card1.getCount(), card2.getCount(), card3.getCount());
    }

    /**Method checking properties for cards*/
    private static boolean checkSetProperty(int prop1, int prop2, int prop3) {
        return (prop1 == prop2 && prop2 == prop3) || (prop1 != prop2 && prop1 != prop3 && prop2 != prop3);
    }
}
