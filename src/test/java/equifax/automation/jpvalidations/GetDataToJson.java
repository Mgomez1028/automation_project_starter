package equifax.automation.jpvalidations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.serenitybdd.core.Serenity;

import java.util.LinkedHashMap;
import java.util.Set;

public class GetDataToJson {
    public static JsonObject getDataAsObject() {
        LinkedHashMap<String, String> dataResponse = Serenity.sessionVariableCalled("journalresponsedata");
        Set<String> keys = dataResponse.keySet();
        String keySearch = keys.stream().findFirst().get();//cambiar estrategia
        return new Gson().fromJson(dataResponse.get(keySearch), JsonObject.class);
    }
}
