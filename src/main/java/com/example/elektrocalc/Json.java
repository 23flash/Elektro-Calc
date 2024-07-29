package com.example.elektrocalc;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Json {
    // Holds the equations loaded from the JSON file
    public static JSONObject equations = readJson();
    /**
     * Reads a JSON file and returns its contents as a JSONObject.
     *
     * @return JSONObject containing data from the JSON file
     */
    private static JSONObject readJson(){
        JSONObject json = new JSONObject();
        try (BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(Main.class.getResource("equations.json")).getFile()))) {
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
    /**
     * Retrieves the equation string for the given equation name from the JSON object.
     *
     * @param equationName the name of the equation to retrieve
     * @return the equation string
     */
    public static String getEquation(String equationName){
        JSONObject to_search = equations.getJSONObject(equationName);
        return to_search.getString("equation");
    }
    /**
     * Retrieves the definition for the given equation name from the JSON object.
     *
     * @param equationName the name of the equation whose definition is to be retrieved
     * @return the definition string
     */
    public static String getDefinition(String equationName){
        JSONObject to_search = equations.getJSONObject(equationName);
        return to_search.getString("definition");}

}
