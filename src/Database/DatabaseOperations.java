package Database;

import java.util.HashMap;
import java.util.Objects;

/**Class providing operations with our a-la database*/
public class DatabaseOperations{
    public static final HashMap<String, UserData> users = new HashMap<String, UserData>();

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
        for (UserData t:users.values()){
            if (Objects.equals(t.getToken(), token)) return true;
        }
        return false;
    }

    /**Checks if user has valid auth data*/
    public static boolean isValidAuthData(String userName, String password){
        return Objects.equals(users.get(userName).getPassword(), password);
    }

    /**Returns data for set user*/
    public static UserData getUserData(String nickname){
        return users.get(nickname);
    }

}
