#  Projektowanie aplikacji internetowych

1. Napisz program, ktory pobiera liste plikow z linii polecen i wyswietla liczbe wierszy kazdego z nich. Program powinien utworzyc jeden watek dla kazdego z plikow i uzyc tych watkow do zliczenia liczby wierszy kazdego z plikow rownoczenie. Utworz wersje programu, ktora odczytuje pliki nie jednoczesnie a sekwencyjnie. Porownaj wydajnosc wielowatkowego i jednowatkowego programu uzywajac System.currentTimeMillis() do okreslenia czasu wykonania. Porownania dokonaj dla dwoch, trzech i pieciu plikow.

2. Napisz program laczacy sie ze strona podana jako argument wywolania programu i wypisujacy wszystkie znalezione na niej linki i adresy email wykorzystujac w tym celu wyrazenia regularne (pakiet java.util.regex). Oprocz tego program ma zapisac do pliku wszystkie parametry polaczenia, adres IP komputera na ktorym znajduje sie strona oraz naglowek strony (zawartosc sekcji <head>).

3. Echo klient i echo serwer wielowatkowy (dla ulatwienia zacznij od jednowatkowego)

4. Aplikacja obslugujaca bank czasu. Klient zglasza (i wycofuje) uslugi jakie moze wykonac i ich terminy, moze tez zarzadac wyswietlenia wszystkich dostepnych uslug w banku i zarezerwowac sobie wybrana usluge. Serwer rozsyla komunikaty o nowych, zarezerwowanych, niewykorzystanych i wycofanych uslugach i terminach do wszystkich klientow. Nalezy zadbac o odpowiednia synchronizacje dostepu do zasobow i ich aktualizacje.

5. Napisz program, ktory zasumuluje nastepujace zjawiska: deadlock, livelock, starvation. Rodzaj symulacji powinien byc parametrem wywolania programu. Zrob ograniczenie czasowe na dane zjawisko, tak aby program sie nie zawiesil.

## Projekt:
Gra sieciowa. Reguly gry:
gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie
komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
Serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
Konfiguracja serwera w pliku XML.