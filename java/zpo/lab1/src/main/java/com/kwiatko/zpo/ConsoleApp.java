package com.kwiatko.zpo;

import java.util.*;


public class ConsoleApp {
    public static void main( String[] args) {
        int number = 0b1101_1000;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj uztkowniku!\n Na jaki system chciał bys przekonwertowac liczbe 0b1101_1000?");
        System.out.println("a) dziesietny");
        System.out.println("b) trojkowy");
        System.out.println("c) szesnastwkowy");
        System.out.println("d) wolal bym uzyc funkcji do sprawdzania peselu");
        System.out.println("e) chce zebys mi przeczytal liczbe cyfra po cyfrze \n");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "a" :
                System.out.println(number);
                break;
            case "b" :
                System.out.println(Integer.toOctalString(number));
                break;
            case "c" :
            System.out.println(Integer.toHexString(number));
                break;
            case "d" :
                System.out.println("Podaj numer pesel");
                try {
                    System.out.println(generateDataFromPesel(scanner.nextLine()));
                } catch (InvalidPeselExcpetion e) {
                    System.out.println("Przykro mi ale podales nie wlasciwy numer pesel");
                }
                break;
            case "e" :
                System.out.println("Podaj 3 cyfrowa liczbe do przeczytania\n");
                try {
                    readNumber(scanner.nextLine());
                } catch (NotThreeDigitNumberException e) {
                    System.out.println("Ta liczba nie byla trzycyfrowa!");
                }
                break;
            default:
                System.out.println("Przykro mi ale taka opcja nie zostala przewidziana!");
        }
    }


    private static DaneZPeselu generateDataFromPesel(String pesel) throws InvalidPeselExcpetion{
        int centuryShift = 80;
        int startingCentury = 1800;
        int day;
        int month;
        int year;
        DaneZPeselu.Gender gender;

        if(pesel.length() != 11 || !pesel.matches("^[0-9]*$")) {
            throw new InvalidPeselExcpetion();
        }

        year = Integer.parseInt(pesel.substring(0,2));
        month = Integer.parseInt(pesel.substring(2,4));
        day = Integer.parseInt(pesel.substring(4,6));

        for(int i=0; i < 5; ++i) {
            if(month - centuryShift > 0 && month - centuryShift < 13) {
                month -= centuryShift;
                year += startingCentury + i * 100;
                break;
            }
            centuryShift = (centuryShift + 20) % 100;
        }

        if(Integer.parseInt(pesel.substring(10,11)) % 2 == 0){
            gender = DaneZPeselu.Gender.Women;
        } else {
            gender = DaneZPeselu.Gender.Men;
        }

        return new DaneZPeselu(day, month, year, gender);
    }

    public static void readNumber(String numberString) throws NotThreeDigitNumberException {
        if(!numberString.matches("-?[0-9]{3}")) {
            throw new NotThreeDigitNumberException();
        }
        char[] numbers = numberString.toCharArray();
        for(char number : numbers) {
            switch (number) {
                case '1' : System.out.print("jeden ");
                    break;
                case '2' : System.out.print("dwa ");
                    break;
                case '3' : System.out.print("trzy ");
                    break;
                case '4' : System.out.print("cztery ");
                    break;
                case '5' : System.out.print("piec ");
                    break;
                case '6' : System.out.print("szesc ");
                    break;
                case '7' : System.out.print("sieden ");
                    break;
                case '8' : System.out.print("osiem ");
                    break;
                case '9' : System.out.print("dziewiec ");
                    break;
                case '-' : System.out.print("minus ");
                    break;
                default:
                    System.out.println("Cos poszlo nie tak....");
            }
        }
    }
}
