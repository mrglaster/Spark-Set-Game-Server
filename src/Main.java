import RequestProcessor.AuthProcessor;
import RequestProcessor.RegisterProcessor;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Spark.init();
        Spark.post("/user/register", "application/json" , RegisterProcessor::processRegisterRequest);
        Spark.get("/user/auth", "application/json", AuthProcessor::processAuth);

    }

}