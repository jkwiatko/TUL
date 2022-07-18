package zad1.ksiazka;

public class Thriller extends Ksiazka {

    public Thriller(String author, String title, Integer numberOfPages) {
        super(author, title, numberOfPages);
    }


    @Override
    public String toString() {
        return super.toString() + " | Thriller";
    }
}
