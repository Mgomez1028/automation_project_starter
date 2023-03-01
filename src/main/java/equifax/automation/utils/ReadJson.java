package equifax.automation.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class ReadJson {
    public static JsonElement getJson() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader("src/test/resources/schemas/JsonJournaling.json");
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }

    public static JsonElement getJson(String pathJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader("src/test/resources/schemas/JsonJournaling.json");
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }
}
