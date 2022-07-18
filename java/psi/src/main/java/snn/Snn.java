package snn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snn {
    private static Random random = new Random();
    private static List<Point> testList = new ArrayList<>();
    private static List<Point> teachingList = new ArrayList<>();

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(random);
        teachingList = PointFactory.generateTeachingPoints();
        testList = PointFactory.generateTestList(10);
        perceptron.teach(teachingList);
        perceptron.classify(testList);
        testList.forEach(System.out::println);
    }
}
