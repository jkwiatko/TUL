package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AStar {
    public static List<City> getShortestRoute(City startingCity, List<City> cities) {
        List<City> shortestRoute = new ArrayList<>();
        shortestRoute.add(startingCity);
        if (cities.size() == 0) {
            return shortestRoute;
        }

        City closestCity = cities.get(0);
        City nextClosestCity = null;
        double closestCityScore = 0.0;
        List<City> nextCities = cities.stream().filter(city -> city != cities.get(0)).collect(Collectors.toList());
        if (!nextCities.isEmpty()) {
            nextClosestCity = nextCities.get(0);
            for (int i = 1; i < nextCities.size(); i++) {
                if (closestCity.distance(nextClosestCity) > closestCity.distance(nextCities.get(i))) {
                    nextClosestCity = nextCities.get(i);
                }
            }
            closestCityScore = startingCity.distance(closestCity) + closestCity.distance(nextClosestCity);
            System.out.print("\nstarting in " + startingCity.name + " -> " + closestCity.name + " (" + startingCity.distance(closestCity) + ") -> " + nextClosestCity + " (" + closestCity.distance(nextClosestCity) + ") " + " =  " + closestCityScore + "\n");
            nextCities.stream().forEach(c -> System.out.print("[" + c.name + "]"));
        }

        for (int i = 1; i < cities.size(); i++) {
            if (!nextCities.isEmpty()) {
                double score = 0.0;
                City city = cities.get(i);
                nextCities = cities.stream().filter(c -> c != city).collect(Collectors.toList());
                System.out.print("\n other possible better route: " + startingCity.name + " -> " + city.name + " (" + startingCity.distance(city) + ") ");
                for (int j = 0; j < nextCities.size(); j++) {
                    score = startingCity.distance(city) + Math.floor(city.distance(nextCities.get(j)));
                    System.out.print("\n -> " + nextCities.get(j).name + " (" + Math.floor(city.distance(nextCities.get(j))) + ")" + " =:" + score + " ] ");
                    if (closestCityScore > score) {
                        closestCityScore = score;
                        closestCity = city;
                        nextClosestCity = nextCities.get(j);
                        System.out.print("\n ------changing path to " + closestCity.name + " -> " + nextClosestCity.name + " = " + closestCityScore + " --------------");
                    }
                }
            } else if (startingCity.distance(closestCity) > startingCity.distance(cities.get(i))) {
                closestCity = cities.get(i);
            }
        }

        cities.remove(closestCity);
        shortestRoute.addAll(getShortestRoute(closestCity, cities));
        return shortestRoute;
    }
}