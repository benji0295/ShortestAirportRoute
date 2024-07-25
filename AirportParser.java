import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AirportParser {
  public static Map<String, Airport> parseAirports(String filePath) {
    Map<String, Airport> airports = new HashMap<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      boolean isFirstLine = true;
      while ((line = bufferedReader.readLine()) != null) {
        if (isFirstLine) {
          isFirstLine = false;
          continue;
        }
        String[] parts = line.split(",");
        if (parts.length != 4) continue;

        String code = parts[0].trim();
        String name = parts[1].trim();
        double latitude = Double.parseDouble(parts[2].trim());
        double longitude = Double.parseDouble(parts[3].trim());

        airports.put(code, new Airport(code, name, latitude, longitude));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return airports;
  }
}
