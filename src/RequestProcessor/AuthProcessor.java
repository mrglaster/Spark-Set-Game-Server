package RequestProcessor;

import Database.DatabaseOperations;
import ResponseDto.ExceptionDto;
import ResponseDto.ExtensionResponseDto;
import ResponseDto.RegisterExtensionResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;


import static RequestProcessor.RegisterProcessor.PARAMETERS_ERROR;

public class AuthProcessor {
    public static final String WRONG_AUTHDATA = "Wrong login or password!";
    public static String processAuth(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        if (request.isEmpty() || request.size() != 2){
            return gson.toJson(new ExtensionResponseDto(false, new ExceptionDto(PARAMETERS_ERROR)));
        }
        String userName = request.get("nickname").getAsString();
        String passWord = request.get("password").getAsString();
        if (!DatabaseOperations.isUserRegistered(userName) || userName.length() == 0 || passWord.length() == 0 || !DatabaseOperations.isValidAuthData(userName, passWord)){
            final ExceptionDto exceptionDto = new ExceptionDto(WRONG_AUTHDATA);
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        String userToken = DatabaseOperations.getUserData(userName).getToken();
        final var authDto = new RegisterExtensionResponse(userToken, userName);
        return gson.toJson(authDto);
    }
}
