package com.example.elektrocalc;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Json {
    public static JSONObject equations = readJson();

    private static JSONObject readJson(){
        JSONObject json = new JSONObject();
        try (BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(Main.class.getResource("formulas.json")).getFile()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getEquation(String equationName){
        JSONObject to_search = equations.getJSONObject(equationName);
        return to_search.getString("equation");
    }

    public static String extractKey(String equationName){
        String to_search_string = "";
        for (String key : equations.keySet()) {
            if (key.contains("Ohms")) {
                to_search_string = key;
                break;
            }
        }
        return to_search_string;
    }

}
