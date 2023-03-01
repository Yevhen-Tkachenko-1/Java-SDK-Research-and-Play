package research.loader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class ResourceAsStream {

    public static void loadFile() {

        try {
            InputStream is = ResourceAsStream.class.getResourceAsStream("/research/loader/test.json");
            Objects.requireNonNull(is);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));
            System.out.println(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResourceAsStream.loadFile();
    }

}
