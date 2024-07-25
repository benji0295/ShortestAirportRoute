import java.util.*;

public class Dijkstra {
  public static Map<String, Integer> dijkstra(Graph graph, String source) {
    Map<String, Integer> distances = new HashMap<>();
    Map<String, Boolean> visited = new HashMap<>();

    for (String airport : graph.getAdjList().keySet()) {
      distances.put(airport, Integer.MAX_VALUE);
      visited.put(airport, false);
    }

    distances.put(source, 0);

    for (int i = 0; i < graph.getAdjList().size(); i++) {
      String u = findMinDistance(distances, visited);
      visited.put(u, true);

      for (Edge edge : graph.getEdges(u)) {
        String v = edge.destination;

        if (!visited.get(v) && (distances.get(u) + edge.weight < distances.get(v))) {
          distances.put(v, distances.get(u) + edge.weight);
        }
      }
    }
    return distances;
  }

  private static String findMinDistance(Map<String, Integer> distances, Map<String, Boolean> visited) {
    int minDistance = Integer.MAX_VALUE;
    String minDistanceVertex = null;

    for (Map.Entry<String, Integer> entry : distances.entrySet()) {
      if (!visited.get(entry.getKey()) && entry.getValue() < minDistance) {
        minDistance = entry.getValue();
        minDistanceVertex = entry.getKey();
      }
    }
    return minDistanceVertex;
  }
}
