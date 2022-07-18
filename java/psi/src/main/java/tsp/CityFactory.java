package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CityFactory {

    private static Random random = new Random();

    static public List<City> createCities(int howMany) {
        List<City> cities = new ArrayList<>();
        for(int i=0; i < howMany; i++) {
            City city = new City(CityNames.values()[i].name() , Math.abs(random.nextInt(100)),  Math.abs(random.nextInt(100)));
//            City city = new City(CityNames.values()[i].name() , i+1,  i+1);
            System.out.println("...created city: " + city.toString());
           cities.add(city);
        }
        return cities;
    }

    static public List<List<City>> createPermutation(List<City> cities) {
        List<List<City>> permutationList = new ArrayList<>();
        if(cities.size() == 0) {
            permutationList.add(new ArrayList<>());
            return permutationList;
        }

        for(int i=0; i < cities.size(); i++) {
            City iCity = cities.get(i);
            List<City> allButCity = new ArrayList<>();
            for(City city : cities) {
                if(city != iCity) {
                    allButCity.add(city);
                }
            }

            List<List<City>> subPermutationList = createPermutation(allButCity);

            for(List<City> simpleList : subPermutationList) {
                simpleList.add(iCity);
            }
            permutationList.addAll(subPermutationList);
        }
        return permutationList;
    }

    public static double calculateRouteCost(List<City> rout) {
        double cost = 0;
        for(int i=0; i<rout.size()-1; i++) {
            cost += rout.get(i).distance(rout.get(i+1));
        }

        return cost;
    }
}
