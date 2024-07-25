import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RouteParser {
  public static void parseRoutes(String filePath, Graph graph) {

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(",");
        String source = parts[0].trim();
        String destination = parts[1].trim();
        int distance = Integer.parseInt(parts[2].trim());
        graph.addEdge(source, destination, distance);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
