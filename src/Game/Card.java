package Game;

import com.google.gson.JsonObject;

public class Card {

    private int id;
    private int color;
    private int shape;
    private int fill;
    private int count;

    public Card(int id, int color, int shape, int fill, int count) {
        this.id = id;
        this.color = color;
        this.shape = shape;
        this.fill = fill;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    /**Converts a Cart to JsonObject*/
    public JsonObject toJson(){
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("id", this.getId());
        resultJson.addProperty("color", this.getColor());
        resultJson.addProperty("shape", this.getShape());
        resultJson.addProperty("fill", this.getFill());
        resultJson.addProperty("count", this.getCount());
        return resultJson;
    }

    /**Builds a card by JsonObject*/
    public static Card fromJsonObject(JsonObject jsonCard){
        return new Card(jsonCard.get("id").getAsInt(), jsonCard.get("color").getAsInt(), jsonCard.get("shape").getAsInt(), jsonCard.get("fill").getAsInt(), jsonCard.get("count").getAsInt());
    }





}
