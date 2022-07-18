package zad1.wydawnictwo;

import zad1.ksiazka.Ksiazka;
import zad1.ksiazka.PowiescHistoryczna;

public class WydawnictwoPowiesciHistorycznych extends Wydawnictwo {


    public WydawnictwoPowiesciHistorycznych(String author) {
        this.author = author;
    }

    @Override
    public Ksiazka createBook(String title, Integer pageCount) {
        return new PowiescHistoryczna(author, title, pageCount);
    }
}
