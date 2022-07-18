package zad1;

import sun.util.resources.LocaleData;
import zad1.ksiazka.Ksiazka;
import zad1.ksiazka.dekorator.CannotApplyThisDecoratorException;
import zad1.ksiazka.dekorator.KsiazkaZAutografem;
import zad1.ksiazka.dekorator.KsiazkaZObwoluta;
import zad1.ksiazka.dekorator.KsiazkaZOkladkaZwykla;
import zad1.wydawnictwo.Wydawnictwo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Main {

    public static void main (String[] args) {
        Publikacja publikacja = new Ksiazka("Adam Mickiewicz", "Dziady", 130);
        try{
            publikacja = new KsiazkaZAutografem(publikacja, "Drogiej Hani - Adam Mickiewicz");
            publikacja = new KsiazkaZOkladkaZwykla(publikacja);
            publikacja = new KsiazkaZObwoluta(publikacja);
        } catch (CannotApplyThisDecoratorException e) {
            System.out.println("Cannot apply this decoration");
        }
        System.out.println(publikacja.toString());

        Wydawnictwo w = Wydawnictwo.getInstance("Józef Ignacy Kraszewski");
        Ksiazka k = w.createBook("testbook", 280);
        System.out.println(k);

        LocalDate ww2Start = LocalDate.of(1939, 9, 1);
        LocalDate ww2End = LocalDate.of(1945, 8, 5);

        long between = ChronoUnit.DAYS.between(ww2Start, ww2End) + 1;
        System.out.println(between);

        LocalDate sixteen = LocalDate.of(2016,1,1);
        System.out.println(sixteen.plusDays(68).getDayOfMonth());
        System.out.println(sixteen.plusDays(68).getMonth());

        int counter = 0;
        LocalDate twentyNine = LocalDate.of(1994,1,29);
        while(twentyNine.isBefore(LocalDate.now())) {
           if(twentyNine.isLeapYear()) {
                counter++;
            }
           twentyNine = twentyNine.plusYears(1);
        }
        System.out.println(counter);
    }
}
