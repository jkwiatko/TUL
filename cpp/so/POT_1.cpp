#include<iomanip>
#include<iostream>
#include<cmath>
#include<windows.h>
#include<process.h>
#include<cstdlib>
#include<cstdio>


using namespace std;



UINT WINAPI Obliczenia(LPVOID);
UINT WINAPI Srednia(LPVOID);

struct PARM {
       int nData;
       char zn;
       double* W, *S;
       };

int main(int argc, char *argv[]) 		 // Watek3a
{

	HANDLE hMapFile;
if (argc <= 1 ) { puts("Brak Danych"); return 10; }
 else {sscanf(argv[1], "%p", &hMapFile);} // pobranie wartoœci uchwytu od rodzica

puts(".......poczatek procesu POT_1........");

BYTE *pMapFile = (BYTE *)MapViewOfFile(hMapFile, FILE_MAP_ALL_ACCESS, 0,0, 0);
if(pMapFile==NULL) { printf("in Map1bDz MapVievOfFile error: %d\n", GetLastError());
            getchar(); return 1; }

double cos=5;
double W[70000];
DWORD sizeW=70000*sizeof(double),Count=2;
HANDLE hMojeEvent = NULL;

memcpy(W, pMapFile, sizeW);
memcpy(pMapFile+sizeof(double), W, sizeW);

HANDLE hWatek[2];
hWatek[1]=hWatek[0]=NULL;
UINT ID_G1=0, ID_G2=0;
PARM  parmGA = {15, 'A',W,&cos},
            parmGB = {70000, '@',W,&cos};

DWORD T1 = GetTickCount();
puts("Uruchamiam procesy potomne");

hWatek[0] = (HANDLE)_beginthreadex(NULL, 0, Obliczenia, &parmGA, 0, &ID_G1);
hWatek[1] = (HANDLE)_beginthreadex(NULL, 0, Srednia, &parmGB, 0, &ID_G2);



hMojeEvent=OpenEvent(EVENT_ALL_ACCESS, FALSE, "MojeEvent");
	if(hMojeEvent==NULL) {  printf("OpenEvent error:"); getchar(); return 1; }
WaitForMultipleObjects(Count,hWatek, TRUE,INFINITE);
memcpy(pMapFile, &cos, sizeof(double));
UnmapViewOfFile (pMapFile);// zwolnienie pamieci przeznaczonej na odwzorowanie pliku
CloseHandle (hMapFile);
PulseEvent(hMojeEvent);
return 0;


return 0;
}

UINT WINAPI Srednia(LPVOID  parametr)
{
    puts(".......Srednia........");
PARM *parm = (PARM*)parametr;
double Srednia=0;
for(int i=0;i<70000;++i)
Srednia+=(parm->W)[i];
Srednia/=70000;
*parm->S=Srednia;
cout<<Srednia<<endl;
}

UINT WINAPI Obliczenia(LPVOID  parametr)
{
     puts(".......Oblieczenia........");
    PARM *parm = (PARM*)parametr;
    int n=parm->nData;
	char zn=parm->zn;
for (int k1=0; k1 < n; k1++){
      for (int k2=0; k2<20000; k2++) pow(sin(k1),3.3)* pow(cos(k1),2.2);
      cout << zn <<" ";
      } cout << "\n";
}

