package zad1.ksiazka.dekorator;
import zad1.Publikacja;

public abstract class KsiazkaDekorator implements Publikacja {
    private final Publikacja publikacja;

    public Publikacja getPublikacja() {
        return publikacja;
    }

    public KsiazkaDekorator (Publikacja publikacja) throws CannotApplyThisDecoratorException {
        if(this instanceof KsiazkaZAutografem) {
            if(sprwadzCzyJuzMaAutograf(publikacja))  {
                throw new CannotApplyThisDecoratorException();
            }
        } else if(this instanceof KsiazkaZOkladkaZwykla || this instanceof KsiazkaZOkladkaTwarda) {
            if(sprwadzCzyJuzMaOkladke(publikacja)) {
                throw new CannotApplyThisDecoratorException();
            }
        } else if (this instanceof KsiazkaZObwoluta) {
            if(!sprwadzCzyMozeMiecObwolutke(publikacja)) {
                throw new CannotApplyThisDecoratorException();
            }
        }

        this.publikacja = publikacja;
    }

    public String getAuthor() {
        return publikacja.getAuthor();
    }

    public String getTitle() {
        return publikacja.getTitle();
    }

    public String getNumberOfPages() {
        return publikacja.getNumberOfPages();
    }

    @Override
    public String toString() {
        return publikacja.toString();
    }

   private boolean sprwadzCzyJuzMaAutograf(Publikacja publikacja) {
        if(publikacja instanceof KsiazkaDekorator) {
            if(publikacja instanceof KsiazkaZAutografem) {
                return true;
            }
            else {
               return sprwadzCzyJuzMaAutograf(((KsiazkaDekorator) publikacja).publikacja);
            }
        }
        return false;
   }

   private boolean sprwadzCzyJuzMaOkladke(Publikacja publikacja){
       if(publikacja instanceof KsiazkaDekorator) {
           if(publikacja instanceof KsiazkaZOkladkaTwarda || publikacja instanceof KsiazkaZOkladkaZwykla) {
               return true;
           }
           else {
              return sprwadzCzyJuzMaOkladke(((KsiazkaDekorator) publikacja).publikacja);
           }
       }
       return false;
   }

   private boolean sprwadzCzyMozeMiecObwolutke(Publikacja publikacja) {
       if(publikacja instanceof KsiazkaDekorator) {
           if (!sprwadzCzyJuzMaOkladke(publikacja)) {
               return false;
           } else if (publikacja instanceof KsiazkaZObwoluta) {
               return false;
           } else {
              sprwadzCzyJuzMaAutograf(((KsiazkaDekorator) publikacja).publikacja);
              return true;
           }
       }
       return false;
   }
}
