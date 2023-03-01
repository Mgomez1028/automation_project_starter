package equifax.automation.jpvalidations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import lombok.SneakyThrows;
import net.thucydides.core.annotations.Step;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GettingDataFromFiles {

    @Step("Getting expected data from local files")
    public HashMap<String, String> gettingExpectedData() {

        AbstractMap.SimpleImmutableEntry<String, String> valuesToFilter = new AbstractMap.SimpleImmutableEntry<>("personIdNumber1", "40226832110");
        String key = valuesToFilter.getKey();
        String idRecord = valuesToFilter.getValue();

        String inputDataFilePath = "src/test/resources/data/domino/jce_registration/inputfiles/CONSUMER_IDENTITY_JCE_REGISTRATION_1Record_aut.txt";
        String mappingConfiguration = "src/test/resources/data/domino/jce_registration/jceMappingConfiguration.json";
        //GET THE RECORD IN THE FILE
        List<Map<String, String[]>> recordsFound = readTxt(inputDataFilePath);
        String[] record = getRecordByKey(idRecord, recordsFound);
        //GET THE CONFIGURATION BY DOMAIN
        List<JsonObject> stdConfigWithTarget = readerJsonFileConfiguration(mappingConfiguration);

        HashMap<String, String> expectedResult = mergeSTDWithRecord(stdConfigWithTarget, record);
        return expectedResult;
    }

    public HashMap<String, String> mergeSTDWithRecord(List<JsonObject> stdConfigWithTarget, String[] record) {
        HashMap<String, String> mappedRecord = new HashMap<>();
        for (JsonObject member : stdConfigWithTarget) {
            String key = member.get("target").getAsString();
            String value = record[member.get("index").getAsInt()];
            mappedRecord.put(key, value);
        }
        return mappedRecord;
    }

    public List<Map<String, String[]>> readTxt(String nombreArchivo) {
        List<Map<String, String[]>> listaMapas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                Map<String, String[]> map = new HashMap<>();
                String[] datos = linea.split("\\|");
                map.put(datos[0], datos);
                listaMapas.add(map);
            }

            return listaMapas;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getRecordByKey(String key, List<Map<String, String[]>> listaMapas) {
        List<String[]> record = listaMapas.stream().map(r -> r.get(key)).collect(Collectors.toList());
        return record.get(0);
        // TODO REFACTOR METHOD
    }

    @SneakyThrows
    public List<JsonObject> readerJsonFileConfiguration(String mappingConfigurationFilePath) {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(mappingConfigurationFilePath));

        LinkedTreeMap<String, Object> readConfiguration = gson.fromJson(br, LinkedTreeMap.class);
        String readConfigurationString = gson.toJson(readConfiguration);
        JSONObject jsonObject = new JSONObject(readConfigurationString);
        JSONArray rawConfigurationFields = jsonObject.getJSONArray("fields");

        List<JsonObject> stdConfigurationFieldsWithTarget = rawConfigurationFields.toList().stream()
                .map(obj -> gson.fromJson(gson.toJsonTree(obj), JsonObject.class))
                .filter(obj -> obj.get("target") != null)
                .collect(Collectors.toList());

        return stdConfigurationFieldsWithTarget;
    }
}
