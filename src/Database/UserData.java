package Database;

public class UserData {
    private static String password;
    private static String token;
    public UserData(String password, String token){
    }

    public void setPassword(String passwordNew){
        if (password.length() == 0) try {
            throw new Exception("No password found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.password = passwordNew;
    }

    public void setToken(String token){
        if (token.length() == 0) try {
            throw new Exception("No token found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.token = token;
    }

    public static String getPassword() {
        return password;
    }

    public static String getToken() {
        return token;
    }


}
