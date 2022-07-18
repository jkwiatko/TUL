package zad1.wydawnictwo;

import zad1.ksiazka.Ksiazka;

public abstract class Wydawnictwo {

    protected String author;

    public static Wydawnictwo getInstance(String author) {
        Wydawnictwo wydawnictwo;
        switch (author) {
            case "Józef Ignacy Kraszewski" : wydawnictwo = new WydawnictwoPoematow("Józef Ignacy Kraszewski");
            break;
            case "Stephen King" :  wydawnictwo = new WydawnictwoThrillerow("Stephen King");
            break;
            case "Henryk Sienkiewicz" :  wydawnictwo = new WydawnictwoPowiesciHistorycznych("Henryk Sienkiewicz");
            break;
            default:  wydawnictwo = null;
        }
        return wydawnictwo;
    }

    public abstract Ksiazka createBook(String title, Integer pageCount);
}
