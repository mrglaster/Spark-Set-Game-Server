package Database;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**Class providing operations with our a-la database*/
public class DatabaseOperations{
    public static final HashMap<String, UserData> users = new HashMap<String, UserData>();
    public static final HashMap<String, Integer> scores = new HashMap<String, Integer>();


    /**Checks if a user is registered*/
    public static boolean isUserRegistered(String userName){
        return users.containsKey(userName);
    }


    /**Registers user*/
    public static void registerUser(String userName, String password, String token){
        users.put(userName, new UserData(password, token));
    }

    /**Checks if token is valid*/
    public static boolean isValidToken(String token){
        for (UserData t: users.values()){
            if (Objects.equals(t.getToken(), token)) return true;
        }
        return false;
    }


    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getNameByToken(String token){
        for (UserData i: users.values()) {
            if (Objects.equals(i.getToken(), token)) {
                return getKeyByValue(users, i);
            }
        }
        return null;
    }


    /**Checks if user has valid auth data*/
    public static boolean isValidAuthData(String userName, String password){
        return Objects.equals(users.get(userName).getPassword(), password);
    }

    /**Returns data for set user*/
    public static UserData getUserData(String nickname){
        return users.get(nickname);
    }

    /**Chekcs if the user is in game*/
    public static boolean isInGame(String token){
        return scores.get(token) != null;
    }

    /**Returns scores by token*/
    public static int getScores(String token){
        if (isInGame(token)){
            return scores.get(token);
        }
        return 0;
    }

    /**Returns current game's scores as array of json*/
    public static JsonArray getServerScores(){
        JsonArray result = new JsonArray();
        for (var currentToken : scores.keySet()){
            String name = getNameByToken(currentToken);
            int scores = getScores(currentToken);
            JsonObject currentScores = new JsonObject();
            currentScores.addProperty("name", name);
            currentScores.addProperty("score", scores);
            result.add(currentScores);
        }
        return result;
    }

}
