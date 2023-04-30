package RequestProcessor;
import Database.DatabaseOperations;
import Game.Card;
import Game.GameOperations;
import ResponseDto.ExceptionDto;
import ResponseDto.ExtensionResponseDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import java.util.List;

public class GameProcessor {
    public static String processGetField(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token) && DatabaseOperations.isInGame(token)){
            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("success", true);
            responseObject.addProperty("score", DatabaseOperations.getScores(token));
            responseObject.addProperty("status", GameOperations.getGameStatus());
            System.out.println("THERE IS SET IN CURRENT FIELD: " + GameOperations.isSetInCurrent());
            responseObject.add("cards", GameOperations.getCurrentGameField());
            return gson.toJson(responseObject);
        } else if (!DatabaseOperations.isInGame(token)){
            final ExceptionDto exceptionDto = new ExceptionDto("You aren't even in the game!");
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }

    public static String processPick(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        List<JsonElement> cards =  request.get("cards").getAsJsonArray().asList();
        if (DatabaseOperations.isValidToken(token) && cards.size() == 3){
            int cardOne = cards.get(0).getAsInt();
            int cardTwo = cards.get(1).getAsInt();
            int cardThree = cards.get(2).getAsInt();
            if (GameOperations.isSetById(cardOne, cardTwo, cardThree)){
                DatabaseOperations.scores.put(token, DatabaseOperations.scores.get(token) + 1);
                JsonObject response = new JsonObject();
                response.addProperty("success", true);
                response.addProperty("isSet", true);
                response.addProperty("scores", DatabaseOperations.getScores(token));
                int cntr = 0;
                for (int i = 0; i < GameOperations.currentGameField.size(); i++) {
                    int currentId = Card.fromJsonObject(GameOperations.currentGameField.get(i).getAsJsonObject()).getId();
                    if (currentId == cardOne || currentId == cardTwo || currentId == cardThree){
                        cntr+=1;
                        GameOperations.currentGameField.remove(i);
                        if (cntr == 3) break;
                    }
                }
                return gson.toJson(response);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("isSet", false);
                response.addProperty("scores", DatabaseOperations.getScores(token));
                return gson.toJson(response);
            }
        } else if (cards.size() != 3){
            final ExceptionDto exceptionDto = new ExceptionDto("Wrong amount of cards!");
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }

    public static String processAdd(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token) && GameOperations.canAdd()){
            GameOperations.addCards();
            JsonObject jo = new JsonObject();
            jo.addProperty("success", true);
            return gson.toJson(jo);
        } else if (!GameOperations.canAdd()) {
            final ExceptionDto exceptionDto = new ExceptionDto("Can't add!");
            final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
            return gson.toJson(extensionResponseDto);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }

    public static String processScores(Request req, Response res){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject request = new Gson().fromJson(req.body(), JsonObject.class);
        res.type("application/json");
        String token = request.get("accessToken").getAsString();
        if (DatabaseOperations.isValidToken(token)){
            JsonObject jo = new JsonObject();
            jo.addProperty("success", true);
            jo.add("users", DatabaseOperations.getServerScores());
            return gson.toJson(jo);
        }
        final ExceptionDto exceptionDto = new ExceptionDto("Invalid token!");
        final ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(false, exceptionDto);
        return gson.toJson(extensionResponseDto);
    }


}
