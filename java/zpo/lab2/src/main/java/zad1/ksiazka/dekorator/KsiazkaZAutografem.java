package zad1.ksiazka.dekorator;

import zad1.Publikacja;

public class KsiazkaZAutografem extends KsiazkaDekorator {

   private String autograf;

    public KsiazkaZAutografem(Publikacja publikacja, String autograf) throws CannotApplyThisDecoratorException {
        super(publikacja);
        this.autograf = autograf;
    }

    public String getAuthor() {
        return super.getAuthor();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public String getNumberOfPages() {
        return super.getNumberOfPages();
    }

    @Override
    public String toString() {
        return super.toString() + " | z autografem: " + autograf;
    }
}
