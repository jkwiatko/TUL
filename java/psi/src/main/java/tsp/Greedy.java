package tsp;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    public static List<City> getShortestRoute(City startingCity, List<City> cities) {
        List<City> shortestRoute = new ArrayList<>();
        shortestRoute.add(startingCity);
        if(cities.size() == 0) {
            return shortestRoute;
        }

        City closestCity = cities.get(0);
        for (int i=1; i<cities.size(); i++) {
            if(closestCity.distance(startingCity) > startingCity.distance(cities.get(i)) ) {
                closestCity = cities.get(i);
            }
        }

        cities.remove(closestCity);
        shortestRoute.addAll(getShortestRoute(closestCity, cities));
        return shortestRoute;
    }
}
