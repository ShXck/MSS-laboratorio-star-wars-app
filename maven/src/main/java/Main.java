import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.swing.*;
import java.io.IOException;


class Character {
    public String name;
    public String height;
    public String birth_year;
}

public class Main {

    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet request = new HttpGet("https://swapi.dev/api/people");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status// HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);

                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    JsonNode node = mapper.readTree(result).at("/results");

                    String[][] data = new String[node.size()][3];

                    ArrayNode arr_node = (ArrayNode) node;

                    for (int i = 0; i < arr_node.size(); i++) {
                        Character c = mapper.readValue(arr_node.get(i).toString(), Character.class);
                        data[i][0] = c.name;
                        data[i][1] = c.height;
                        data[i][2] = c.birth_year;
                    }
                }

            } finally {
                response.close();
            }
        } catch (
                IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
