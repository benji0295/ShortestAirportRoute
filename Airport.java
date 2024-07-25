
/**
 * Represent airports with their 3 letter code, the name of the airport, the latitude, and longitude.
 */

public class Airport {
  String code;
  String name;
  double latitude;
  double longitude;

  public Airport(String code, String name, double latitude, double longitude) {
    this.code = code;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
