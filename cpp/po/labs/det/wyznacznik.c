#include <stdio.h>
#include <stdlib.h>
double det(double * matrix, int n);
double* matrix_alloc(double *matrix,int stopien);
void matrix_free(double* pointer);

int main(void)
{
	FILE *fp;

	double macierz[100]={0},wynik;
	int i,j,n=0,m=0;
	char liczba[4], kontrola;

	//Otwarcie pliku
	if((fp=fopen("macierz.txt","r"))==NULL)
	{
		printf("Błąd otwarcia pliku.\n");
		exit(1);
	}

	//Okreslenie stopnia macierzy
	do
	{
		kontrola=fgetc(fp);
		if(kontrola==' '|| kontrola=='\n')n++; 
		if(kontrola=='\n')m++;
	}
	while(!feof(fp));
	if(n%m==0)
		n/=m;
	fseek(fp, 0L, SEEK_SET);
	
	//Test kwadratowosci
	if(n!=m)
	{
		printf("\n\nPodana macierz nie jest kwadratowa\n\n");
		fclose(fp);
		return 1;
	}
	
	//Wczytanie macierzy z pliku
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
		{
			fscanf(fp, "%s",liczba);
				
				//Obsluga nieprawidlowych wartosci
				if(atof(liczba)==0 && *liczba!='0')
				{
					printf("\n\nPodana macierz zawiera bledne wartosci. Wiersz:%d Kolumna:%d\n\n",i+1,j+1);
					fclose(fp);
				return 2;
				}	
			macierz[i*n+j]=atof(liczba);			
		}
	
	//Wyswietlenie macierzy
	for (i=0;i<n;i++)
	{
		printf("\n");
		for(j=0;j<n;j++)
			printf(" %f",macierz[i*n+j]);
	}
	printf("\n");
	
	wynik=det(macierz,n);
	printf("\n\n%f\n",wynik);
	printf("\n%d\n",n);
	
	//Zamkniecie pliku
	fclose(fp);
	return 0;
}

double* matrix_alloc(double *matrix,int stopien)
{
	double* wsk;	
	wsk=(double*)malloc(stopien*stopien *sizeof(double));
		
	return wsk;
}

void matrix_free(double* pointer)
{
	free(pointer);
}


double det(double * matrix, int n)
{
	double* minor;
	double sum=0.0;
	double sign=1.0;
	int i=0,j=0,k=0,m=0;

	/* rozpatrz przypadki szczególne */
	if(n==1) return matrix[0];
	if(n==2) return matrix[0]*matrix[3]-matrix[2]*matrix[1];
	
	minor=matrix_alloc(matrix,n-1);
	for(k=0;k<n;k++)			
	{	
		m=0;
		for(i=1;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
								
				if(j==k)continue;
				minor[m++]=matrix[i*n+j];
			}
		}
		
		sum+=sign*matrix[k]*det(minor,n-1);	
		sign = -sign;
	}
	matrix_free(minor);
	return sum;
}




