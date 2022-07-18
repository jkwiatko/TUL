package zad1.wydawnictwo;

import zad1.ksiazka.Ksiazka;
import zad1.ksiazka.Poemat;

public class WydawnictwoPoematow extends Wydawnictwo {

    public WydawnictwoPoematow(String author) {
        this.author = author;
    }

    @Override
    public Ksiazka createBook(String title, Integer pageCount) {
        return new Poemat(author, title, pageCount);
    }
}
