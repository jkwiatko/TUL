package zad1.ksiazka;

public class Poemat extends Ksiazka {

    public Poemat(String author, String title, Integer numberOfPages) {
        super(author, title, numberOfPages);
    }

    @Override
    public String toString() {
        return super.toString() + " | Poeam";
    }
}
