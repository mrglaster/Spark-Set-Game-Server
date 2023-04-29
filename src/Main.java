import RequestProcessor.AuthProcessor;
import RequestProcessor.RegisterProcessor;
import RequestProcessor.RoomProcessor;
import RequestProcessor.GameProcessor;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        /*
        Spark.init();
        String applicationType = "application/json";
        Spark.post("/user/register", applicationType , RegisterProcessor::processRegisterRequest);
        Spark.post("/user/login", applicationType, AuthProcessor::processAuth);
        Spark.post("/set/room/create", applicationType, RoomProcessor::processRoomCreate);
        Spark.post("/set/room/list", applicationType, RoomProcessor::processRoomList);
        Spark.post("/set/room/enter", applicationType, RoomProcessor::processRoomEnter);
        Spark.post("/set/field", applicationType, GameProcessor::processGetField);
        Spark.post("/set/pick", applicationType, GameProcessor::processPick );
        Spark.post("/set/add", applicationType, GameProcessor::processAdd);
        Spark.post("/set/scores", applicationType, GameProcessor::processScores);
        */
        System.out.println(Game.GameOperations.generateField());
    }

}