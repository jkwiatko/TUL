package snn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perceptron {

    private int wX;
    private int wY;
    private int b;

    public Perceptron(Random random) {
        wX = random.nextInt(2);
        wY = random.nextInt(2);
        b = random.nextInt(2);
    }

    public int ask(Point point) {
        int a = wX * point.getX() + wY * point.getY() + b;
        if (a > 0) {
            return 1;
        } else {
            return  0;
        }
    }

    public void classify(List<Point> points) {
        points.forEach(point -> point.setT(ask(point)));
    }

    public void teach(List<Point> points) {
        boolean done;
        do {
            done = true;
            for (Point point : points) {
                if (!point.isLearned()) {
                    int a = point.getX() * wX + point.getY() * wY + b;
                    int y;
                    if (a > 0) {
                        y = 1;
                    } else {
                        y = 0;
                    }
                    int error = point.getT() - y;
                    if (error == 0) {
                        point.setLearned(true);
                    } else {
                        done = false;
                        changeWeights(point, error);
                        changeShift(error);
                    }
                }
            }
        } while (!done);
    }

    private void changeShift(int oldError) {
            b = b+ oldError;
    }

    private void changeWeights(Point point,int oldError) {
        int deltaWX = oldError * point.getX();
        int deltaWY = oldError * point.getY();

        wX = wX + deltaWX;
        wY = wY + deltaWY;
    }
}
