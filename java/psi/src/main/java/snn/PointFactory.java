package snn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointFactory {

    public static List<Point> generateTeachingPoints() {
        List<Point> teachingList = new ArrayList<>();
        Point p;

        p = new Point(-8, 20, 1);
        teachingList.add(p);
        p = new Point(2, 20, 1);
        teachingList.add(p);
        p = new Point(4, 25, 1);
        teachingList.add(p);
        p = new Point(-9,-10, 1);
        teachingList.add(p);
        p = new Point(-6, -5, 1);
        teachingList.add(p);

        p = new Point(5, 9, 0);
        teachingList.add(p);
        p = new Point(-2, -10, 0);
        teachingList.add(p);
        p = new Point(-6, -25, 0);
        teachingList.add(p);
        p = new Point(1, -10, 0);
        teachingList.add(p);
        p = new Point(4, -16, 0);
        teachingList.add(p);

        return teachingList;
    }

    public static List<Point> generateTestList(int listSize) {
        List<Point> testList = new ArrayList<>();
        Random ranodom = new Random();
        Point p;

        for(int i=0; i<listSize; i++){
            p = new Point(ranodom.nextInt(20 + 20 + 1) - 20, ranodom.nextInt(20 + 20 + 1) - 20);
            testList.add(p);
        }
        return testList;
    }

}
