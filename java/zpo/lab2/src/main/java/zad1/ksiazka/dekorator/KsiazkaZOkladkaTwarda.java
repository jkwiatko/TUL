package zad1.ksiazka.dekorator;

import zad1.Publikacja;

public class KsiazkaZOkladkaTwarda extends KsiazkaDekorator {


    public KsiazkaZOkladkaTwarda(Publikacja publikacja) throws CannotApplyThisDecoratorException {
        super(publikacja);
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
        return super.toString() + " | " + "Ksiazka w twardej okladce";
    }
}
