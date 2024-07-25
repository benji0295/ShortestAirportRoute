

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String airportFilePath = "/Users/bensmith/IdeaProjects/CS608/ShortestAirportRoute/airport.csv";
        String routeFilePath = "/Users/bensmith/IdeaProjects/CS608/ShortestAirportRoute/route.csv";

        try {
          // Parse the airport data
          Map<String, Airport> airports = AirportParser.parseAirports(airportFilePath);
          Graph graph = new Graph();

          // Parse the route data and load it to the graph
          RouteParser.parseRoutes(routeFilePath, graph, airports);

          Scanner scanner = new Scanner(System.in);

          // User input for departure and arrival airports
          System.out.print("Enter departure airport (3-letter code): ");
          String departure = scanner.nextLine().trim().toUpperCase();

          System.out.print("Enter arrival airport (3-letter code): ");
          String arrival = scanner.nextLine().trim().toUpperCase();

          // Check if the airports exist in the parsed data
          if (!airports.containsKey(departure) || !airports.containsKey(arrival)) {
              System.out.println("Invalid airport code.");
              return;
          }

          // Run the Dijkstra algorithm to find the shortest route
          Map<String, Integer> distances = Dijkstra.dijkstra(graph, departure);
          List<String> path = findPath(graph, departure, arrival, distances);

          // Print the results
          if (path.isEmpty()) {
              System.out.println("No route found.");
          } else {
              System.out.println("Shortest route: " + path);
              int totalDistance = distances.get(arrival);
              System.out.println("Total distance: " + totalDistance + " km");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    /**
     * Find the path from start to end using the distances calculated by the Dijkstra algorithm
     */

    private static List<String> findPath(Graph graph, String start, String end, Map<String, Integer> distances) {
        List<String> path = new LinkedList<>();
        String current = end;

        // Traverse from the end to the start to construct the path
        while (!current.equals(start)) {
            path.add(0, current); // Add the current airport to the beginning of the path
            int minDistance = Integer.MAX_VALUE;
            String next = null;

            // Find the next airport in the path with the minimum distance
            for (Edge edge : graph.getEdges(current)) {
                if (distances.get(edge.destination) < minDistance) {
                    minDistance = distances.get(edge.destination);
                    next = edge.destination;
                }
            }

            // If no next airport is found, there is no path
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
