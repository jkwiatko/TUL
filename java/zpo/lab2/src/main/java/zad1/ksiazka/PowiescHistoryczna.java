package zad1.ksiazka;

public class PowiescHistoryczna extends Ksiazka {

    public PowiescHistoryczna(String author, String title, Integer numberOfPages) {
        super(author, title, numberOfPages);
    }

    @Override
    public String toString() {
        return super.toString() + " | History Novel";
    }
}
