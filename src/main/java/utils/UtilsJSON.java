package utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class UtilsJSON {
    public boolean equalJSON(JSONObject jsonObject1, JSONObject jsonObject2) {
        if (jsonObject1.length() != jsonObject2.length()) {
            System.out.println("Different number of keys");
            return false;
        }

        for (String key : jsonObject1.keySet()) {
            if (!jsonObject2.has(key)) {
                System.out.println("Missing key: " + key);
                return false;
            }

            Object value1 = jsonObject1.get(key);
            Object value2 = jsonObject2.get(key);

            if (value1 instanceof JSONArray && value2 instanceof JSONArray) {
                continue;
            }

            if (value1 instanceof JSONObject && value2 instanceof JSONObject) {
                if (!equalJSON((JSONObject) value1, (JSONObject) value2)) {
                    return false;
                }
            } else if (!value1.equals("IGNORE") && !value2.equals("IGNORE") && !value1.equals(value2)) {
                System.out.println("Different value for key: " + key);
                System.out.println("Expected: " + value2);
                System.out.println("Actual: " + value1);
                return false;
            }
        }

        return true;
    }
}
