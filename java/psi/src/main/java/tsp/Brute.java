package tsp;


import java.util.List;
import java.util.stream.Collectors;

public class Brute {

    static public List<City> getShortestRoute(City startingCity, List<List<City>> allPossibleRoutes) {
        int shortestRouteIndex = 0;
        List<List<City>> allRoutesWithStartingCity = allPossibleRoutes.stream().filter(rout -> rout.get(0) == startingCity).collect(Collectors.toList());

        double minCost = CityFactory.calculateRouteCost(allRoutesWithStartingCity.get(0));
        for(int i=1; i<allRoutesWithStartingCity.size(); i++) {
            double routeCost = CityFactory.calculateRouteCost(allRoutesWithStartingCity.get(i));
            if (minCost > routeCost && startingCity == allRoutesWithStartingCity.get(i).get(0)) {
                minCost = routeCost;
                shortestRouteIndex = i;
            }
        }
        return allRoutesWithStartingCity.get(shortestRouteIndex);
    }
}
