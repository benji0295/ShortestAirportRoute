Shortest Airport Route Documentation

Overview

This program takes user input in the form of a 3-letter airport code for both a starting airport and a destination airport and uses Dijkstra’s algorithm to determine the shorted distance between the two. The program parses data from two CSV files that contain airport data and route data and models the data as a graph that is used in the algorithm.

Modeling and Implementation

Airports

Airports are modeled with the Airport class in Airport.java. Within the class are the following variables:
• code: The 3-letter code for the airport
• name: The full name of the airport
• latitude: The latitude coordinate of the airport
• longitude: The longitude coordinate of the airport

The Airport class is used within the AirportParser class that reads data from the airport CSV file and creates Airport objects. The parsed Airport objects are then stored in a HashMap that designates the key as the airport code.

Routes

Routes are modeled as edges in a graph. The RouteParser class reads data from the routes CSV file and adds edges to the Graph object. Every edge is a direct route from one airport to another measured in kilometers, that is calculated using the Haversine formula within the RouteParser class.

The Graph class stores the adjacency list of the graph in which every airport code is linked to an Edge object. Each Edge object contains a destination airport code and the distance.

Dijkstra’s Algorithm

Dijkstra’s Algorithm is used within the program to determine the shortest path from the starting airport to destination airport. To start, the algorithm initializes the starting airport distance to 0 and the distances to all other airports to infinity. It then iterates through each route until it determines the route with the shortest distance. The Dijkstra class implements the algorithm and returns a map of the shortest distance to every airport.

Using the Program

Ensure that the CSV files are formatted correctly with a header row and the following data for each file:
• route.csv: departure airport, arrival airport, aircraft, and operator
• airport.csv: airport code, airport name, latitude coordinate, and longitude coordinate

The latitude and longitude coordinates must be valid double values.

The airport codes the user input must match airport codes within the airport.csv.
