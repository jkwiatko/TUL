#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* laczenie(char* a, char* b);
int strlenght(char* c);

int main(void)
{
	//Deklaracje potrzebnych zmiennych
	char bufor[100],niebufor[50];
	char* pierwszy, *drugi, *wynikowy, *temp=bufor;
	int dlugosc=0,i=1;

	//Komunikat pobierajacy 1 string
	printf("Jestem programem sklejajacym ciagi znakow!\n\nPodaj 1 ciag znakow\n");
	scanf("%s",bufor);
	//gets(bufor);

	//Alkowanie Pamieci i przypisanie jej stringu 1
	dlugosc=strlenght(bufor) + 1;
	pierwszy= (char*)malloc(dlugosc * sizeof(char));
	while(*pierwszy++ = *temp++)i++;
	pierwszy-=i;
	temp=bufor;
	i=1;

    //Komunikat pobierajacy 2 string
	printf("Podaj 2 ciag znakow\n");
	scanf("%s",bufor);
	//gets(bufor);

	//Alkowanie pamieci i przypisanie stringu 2
	dlugosc=strlenght(bufor) + 1;
	drugi= (char*)malloc(dlugosc * sizeof(char));
	while(*drugi++ = *temp++)i++;
	drugi-=i;

	//Laczenie stringow
	wynikowy = laczenie(pierwszy,drugi);
		
	printf("\nWynik to:%s",wynikowy);
	//while(*wynikowy)printf("%c",*wynikowy); zapytac!

	//Zwolnienie Pamieci
	free(pierwszy);
	free(drugi);
	free(wynikowy);

	return 0;
}

char* laczenie(char * a, char* b)
{
	int rozmiar;
	char* wsk,*temp;


	//Alokacja
	rozmiar= strlenght(a) + strlenght(b) + 1;
	wsk=(char*)malloc(rozmiar * sizeof(char));
	temp=wsk;

	//Sklejanie
    while(*temp++ = *a++ );
	temp--;

	//while(*(wsk-1)++ = *a++) -> błedny zapis spowodowany tym, ze wsk-1 oznacza modyfikacje zminnej ktorej nie mozna modyfikowac;
	while(*temp++ = *b++);

	//Zwracanie wskaznika
	return wsk;
}

int strlenght(char* c)
{
	int i=0;
	while(*c++)
    i++;
	return i;
}
