package RequestProcessor;

import Database.DatabaseOperations;
import Game.GameOperations;
import ResponseDto.ExceptionDto;
import ResponseDto.ExtensionResponseDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import javax.xml.crypto.Data;

public class RoomProcessor {

    public static String processRoomCreate(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token)){
            JsonObject ro = new JsonObject();
            DatabaseOperations.scores.put(token, 0);
            ro.addProperty("success", true);
            ro.addProperty("gameId", 0);
            GameOperations.generateField();
            for (var i: DatabaseOperations.scores.keySet()){
                DatabaseOperations.scores.remove(i);
            }
            return gson.toJson(ro);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }

    public static String processRoomList(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token)){
            JsonObject ro = new JsonObject();
            JsonArray gamesContainer = new JsonArray();
            JsonObject currentGame = new JsonObject();
            currentGame.addProperty("id", 0);
            currentGame.addProperty("description", "Yes, this damn server supports only one game! Deal with it!");
            gamesContainer.add(currentGame);
            ro.add("games", gamesContainer);
            return gson.toJson(ro);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }

    public static String processRoomEnter(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        int gameId = request.get("gameId").getAsInt();

        if (DatabaseOperations.isValidToken(token) && gameId == 0){
            JsonObject ro = new JsonObject();
            DatabaseOperations.scores.put(token, 0);
            ro.addProperty("success", true);
            ro.addProperty("gameId", 0);
            return gson.toJson(ro);
        }

        if (gameId != 0){
            final ExceptionDto exceptionDto = new ExceptionDto("Wrong game Id! This server supports only one game with id=0");
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }
    public static String processRoomLeave(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token) && DatabaseOperations.getNameByToken(token) != null && DatabaseOperations.scores.get(token) != null){
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("success", true);
            DatabaseOperations.scores.remove(token);
            return gson.toJson(responseJson);
        } else if (!DatabaseOperations.isValidToken(token)) {
            final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("You don't belong to this room!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }
}
