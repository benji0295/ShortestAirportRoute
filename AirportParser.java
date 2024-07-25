import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AirportParser {
  public static Map<String, String> parseAirports(String filePath) {
    Map<String, String> airports = new HashMap<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(",");
        String code = parts[0].trim();
        String name = parts[1].trim();
        airports.put(code, name);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return airports;
  }
}
