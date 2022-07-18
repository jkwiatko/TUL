package zad1.ksiazka;

import zad1.Publikacja;

public class Ksiazka implements Publikacja {
    private String author;
    private String title;
    private Integer numberOfPages;

    public Ksiazka() {
    }

    public Ksiazka(String author, String title, Integer numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public String getNumberOfPages() {
        return null;
    }

    @Override
    public String toString() {
        return author + " | " + title + " | " + numberOfPages;
    }
}
