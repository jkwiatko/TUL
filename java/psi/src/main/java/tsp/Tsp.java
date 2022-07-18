package tsp;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static tsp.CityFactory.createCities;

public class Tsp {

    private static Scanner scanner = new Scanner(System.in);
    private static int shortestRouteIndex;

    public static void main(String[] args) {
        List<City> shortestRoute;

        System.out.println("How many cities ?");
        List<City> cities = createCities(Integer.parseInt(scanner.nextLine()));

        System.out.println("name a starting city:");
        CityNames pickedCityName = CityNames.valueOf(scanner.nextLine());
        City pickedCity = cities.stream().filter(city -> CityNames.valueOf(city.name).equals(pickedCityName)).findFirst().get();

        System.out.println("Choose algorithm:\na)A*\nb)Brute Force\nc)Greedy");
        String algorithmOption = scanner.nextLine();
        switch (algorithmOption) {
            case "a":
                System.out.println("starting A*....");
                shortestRoute = AStar.getShortestRoute(pickedCity, cities.stream().filter(city -> city != pickedCity).collect(Collectors.toList()));
                presentRoute(shortestRoute, CityFactory.calculateRouteCost(shortestRoute));
                break;
            case "b":
                System.out.println("starting Brute Force....");
                shortestRoute = Brute.getShortestRoute(pickedCity, CityFactory.createPermutation(cities));
                presentRoute(shortestRoute, CityFactory.calculateRouteCost(shortestRoute));
                break;
            case "c":
                System.out.println("starting Greedy....");
                shortestRoute = Greedy.getShortestRoute(pickedCity, cities.stream().filter(city -> city != pickedCity).collect(Collectors.toList()));
                presentRoute(shortestRoute, CityFactory.calculateRouteCost(shortestRoute));
                break;
            default:
                System.out.println("There is no option: " + algorithmOption);
        }
        System.out.println("Bye");
    }

    public static void presentRoute(List<City> route, double cost) {
        System.out.println("Shortest route is ");
        for(City city : route) {
            System.out.println(city.toString());
        }
        System.out.println("Summary cost of it is: " + cost + " units");
    }
}
