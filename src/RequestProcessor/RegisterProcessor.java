package RequestProcessor;

import Database.DatabaseOperations;
import ResponseDto.ExceptionDto;
import ResponseDto.ExtensionResponseDto;
import ResponseDto.RegisterExtensionResponse;
import StringOperations.StringOperator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

public class RegisterProcessor {

    public static final String PARAMETERS_ERROR = "Wrong amount of parameters";
    private static final String WRONG_LOGIN_PASSWORD ="Nickname or password is incorrect";

    /**Provides register processing for the app*/
    public static String processRegisterRequest(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");

        if (request.isEmpty() || request.size() != 2){
            return gson.toJson(new ExtensionResponseDto(false, new ExceptionDto(PARAMETERS_ERROR)));
        }
        String userName = request.get("nickname").getAsString();
        String passWord = request.get("password").getAsString();

        if (DatabaseOperations.isUserRegistered(userName) || userName.length() == 0 || passWord.length() == 0){
            final ExceptionDto exceptionDto = new ExceptionDto(WRONG_LOGIN_PASSWORD);
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }

        String accessToken = StringOperator.generateRandomLine(15);
        final var responseDto = new RegisterExtensionResponse(userName, accessToken);
        DatabaseOperations.registerUser(userName, passWord, accessToken);
        return gson.toJson(responseDto);
    }
}
