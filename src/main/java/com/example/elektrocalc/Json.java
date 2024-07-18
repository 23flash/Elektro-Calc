package com.example.elektrocalc;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Json {
    public static JSONObject formulas= readJson();

    private static JSONObject readJson(){
        JSONObject json = new JSONObject();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/json/formulas.json"))) {
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

    public static String getFormula(String formulaName){
        JSONObject to_search = formulas.getJSONObject(formulaName);
        String to_search_string = to_search.getString("formula");
        return to_search_string;
    }

}
