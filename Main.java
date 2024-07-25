import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String airportFilePath = "/Users/bensmith/IdeaProjects/CS608/ShortestAirportRoute/airport.csv";
        String routeFilePath = "/Users/bensmith/IdeaProjects/CS608/ShortestAirportRoute/route.csv";

        Map<String, String> airports = AirportParser.parseAirports(airportFilePath);
        Graph graph = new Graph();

        // Load routes into the graph
        RouteParser.parseRoutes(routeFilePath, graph);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter departure airport (3-letter code): ");
        String departure = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter arrival airport (3-letter code): ");
        String arrival = scanner.nextLine().trim().toUpperCase();

        if (!airports.containsKey(departure) || !airports.containsKey(arrival)) {
            System.out.println("Invalid airport code.");
            return;
        }

        Map<String, Integer> distances = Dijkstra.dijkstra(graph, departure);
        List<String> path = findPath(graph, departure, arrival, distances);

        if (path.isEmpty()) {
            System.out.println("No route found.");
        } else {
            System.out.println("Shortest route: " + path);
            int totalDistance = distances.get(arrival);
            System.out.println("Total distance: " + totalDistance + " km");
        }
    }

    private static List<String> findPath(Graph graph, String start, String end, Map<String, Integer> distances) {
        List<String> path = new LinkedList<>();
        String current = end;
        while (!current.equals(start)) {
            path.add(0, current);
            int minDistance = Integer.MAX_VALUE;
            String next = null;
            for (Edge edge : graph.getEdges(current)) {
                if (distances.get(edge.destination) < minDistance) {
                    minDistance = distances.get(edge.destination);
                    next = edge.destination;
                }
            }
            if (next == null) {
              System.out.println("No path exists from " + start + " to " + end);
              return new LinkedList<>();
            }
            current = next;
        }
        path.add(0, start);
        return path;
    }
}
