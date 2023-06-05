import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.UtilsJSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UtilsJSONTest {

    private final UtilsJSON utilsJSON = new UtilsJSON();

    @ParameterizedTest
    @CsvSource({
            "JSON_Actual1.json, JSON_Expected1.json, false",
            "JSON_Actual2.json, JSON_Expected2.json, true",
            "JSON_Actual3.json, JSON_Expected3.json, false",
            "JSON_Actual4.json, JSON_Expected4.json, false",
            "JSON_Actual5.json, JSON_Expected5.json, true"
    })
    public void verifyCompareJSON(String actualFile, String expectedFile, boolean expectedResult) throws FileNotFoundException {
        String filePathActual = new File("src/test/resources/" + actualFile).getAbsolutePath();
        JSONObject jsonObjectActual = new JSONObject(new JSONTokener(new FileReader(filePathActual)));
        String filePathExpected = new File("src/test/resources/" + expectedFile).getAbsolutePath();
        JSONObject jsonObjectExpected = new JSONObject(new JSONTokener(new FileReader(filePathExpected)));

        Assertions.assertEquals(expectedResult, utilsJSON.equalJSON(jsonObjectActual, jsonObjectExpected));
    }
}
