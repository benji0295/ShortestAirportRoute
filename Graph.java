import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<String, List<Edge>> adjList;

  public Graph() {
    adjList = new HashMap<>();
  }

  public void addEdge(String source, String destination, int weight) {
    adjList.computeIfAbsent(source, k -> new LinkedList<>()).add(new Edge(destination, weight));
    adjList.computeIfAbsent(destination, k -> new LinkedList<>()).add(new Edge(source, weight));
  }

  public List<Edge> getEdges(String node) {
    return adjList.get(node);
  }

  public Map<String, List<Edge>> getAdjList() {
    return adjList;
  }
}
