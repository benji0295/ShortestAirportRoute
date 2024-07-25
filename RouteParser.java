import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RouteParser {
  public static void parseRoutes(String filePath, Graph graph, Map<String, Airport> airports) {

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(",");

        if (parts.length != 4) continue;

        String source = parts[0].trim();
        String destination = parts[1].trim();
        if (airports.containsKey(source) && airports.containsKey(destination)) {
          double distance = calculateDistance(
                airports.get(source).latitude,
                airports.get(source).longitude,
                airports.get(destination).latitude,
                airports.get(destination).longitude
          );

          graph.addEdge(source, destination, (int) distance);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

    final int Radius = 6371;

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 = lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
              * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return Radius * c;
  }
}
