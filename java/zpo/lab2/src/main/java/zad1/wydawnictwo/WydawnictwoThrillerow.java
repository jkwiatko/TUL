package zad1.wydawnictwo;

import zad1.ksiazka.Ksiazka;
import zad1.ksiazka.Thriller;

public class WydawnictwoThrillerow extends Wydawnictwo {

    public WydawnictwoThrillerow(String author) {
        this.author = author;
    }

    @Override
    public Ksiazka createBook(String title, Integer pageCount) {
        return new Thriller(author, title, pageCount);
    }
}
