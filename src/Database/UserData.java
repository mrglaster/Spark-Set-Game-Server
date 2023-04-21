package Database;

public class UserData {
    private static String password;
    private static String token;
    public UserData(String password, String token){
        setPassword(password);
        setToken(token);
    }

    public void setPassword(String passwordNew){
        if (passwordNew.length() == 0) try {
            throw new Exception("No password found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        password = passwordNew;
    }

    public void setToken(String tokenNew){
        if (tokenNew.length() == 0) try {
            throw new Exception("No token found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        token = tokenNew;
    }

    public static String getPassword() {
        return password;
    }

    public static String getToken() {
        return token;
    }

    @Override
    public String toString(){
        return "{" + password + " ; " + token + " }";
    }
}
