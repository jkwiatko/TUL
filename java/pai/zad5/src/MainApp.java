package zad5;

import zad5.ProblemGenerator.Problem;

class MainApp{
public static void main(String[] args) {
        ProblemGenerator pg = new ProblemGenerator();
        if(args[0].equals("deadlock"))
            pg.set(Problem.DEADLOCK).start();
        else if(args[0].equals("livelock"))
            pg.set(Problem.LIVELOCK).start();
        else if(args[0].equals("starvation"))
            pg.set(Problem.STARVATION).start();
        else System.out.println("Wrong arguemnt!");
    }
}
