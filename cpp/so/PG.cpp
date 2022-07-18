#include<iomanip>
#include<iostream>
#include<cmath>
#include<windows.h>
#include<process.h>
#include<cstdlib>
#include <cstdio>


using namespace std;
 
double Generuj(float a, float b) 
 { double w = (a + (b - a)*(double)rand()/RAND_MAX); 
      for (long k=0; k < 200; k++) log(pow((pow(sin(k)+1.1,3.3)),2.2)); 
   return floor(w * 100 + 0.5)/100; } 

void WatekGlowny(int, char);
void DispV(int, double *, char *);
UINT WINAPI GenVec(LPVOID);
UINT WINAPI BubbleSort(LPVOID);   
//int const maxData = 30000 ;
double X[10000], Y[20000],Z[40000],W[70000],Srednia;
DWORD sizeX=10000*sizeof(double),sizeY=20000*sizeof(double),sizeZ=40000*sizeof(double),sizeW=40000*sizeof(double);
struct PARM {
       int nData;
       double a, b, *X;
       char zn;
       HANDLE* hwatek;
       };

int main() 		 // Watek3a
{
HANDLE hWatek1=NULL, hWatek2=NULL, hWatek3=NULL, hWatek4a=NULL, hWatek4b=NULL, hWatek4c=NULL, hWatek4[3];
UINT ID_1=0, ID_2=0, ID_3=0, ID_4a=0, ID_4b=0,ID_4c=0;
HANDLE hMojeEvent = NULL;



//int nData = 30000;
PARM  parm1X = {10000, -99, 0, X, 'X',},
            parm2Y = {20000, 0, 99, Y, 'Y'},
             	parm3Z = {40000, 100, 99, Z, 'Z'};

PARM  parm4a = {10000, 0, 0, X, 'A',&hWatek1},
             parm4b = {20000, 0, 0, Y, 'B',&hWatek2},
			 	parm4c = {40000, 0, 0, Z, 'C',&hWatek3};

DWORD T1 = GetTickCount();
hWatek1 = (HANDLE)_beginthreadex(NULL, 0, GenVec, &parm1X, 0, &ID_1);
hWatek2 = (HANDLE)_beginthreadex(NULL, 0, GenVec, &parm2Y, 0, &ID_2);
hWatek3 = (HANDLE)_beginthreadex(NULL, 0, GenVec, &parm3Z, 0, &ID_3);

 
hWatek4[0]=hWatek4a = (HANDLE)_beginthreadex(NULL, 0, BubbleSort, &parm4a, 0, &ID_4a);
hWatek4[1]=hWatek4b = (HANDLE)_beginthreadex(NULL, 0, BubbleSort, &parm4b, 0, &ID_4b);
hWatek4[2]=hWatek4c = (HANDLE)_beginthreadex(NULL, 0, BubbleSort, &parm4c, 0, &ID_4c);

DWORD Count=3;
WaitForMultipleObjects(Count,hWatek4, TRUE,INFINITE);


DWORD sizeW=sizeof(double)*70001;
char cmd1[128];
HANDLE hMapFile=NULL;		// uchwyt do obiektu reprezentujacego plik zmapowany
BYTE *pMapFile;

SECURITY_ATTRIBUTES sa;
sa.nLength = sizeof(sa);  sa.lpSecurityDescriptor = NULL;
sa.bInheritHandle = TRUE;   // zmiana uchwytu na dziedziczny.


hMapFile=CreateFileMapping (INVALID_HANDLE_VALUE, &sa, PAGE_READWRITE, 0, sizeW,  NULL);
	if(hMapFile==NULL) { printf("CreateFileMapping error: %d\n", GetLastError());
                        getchar(); return 1; }

pMapFile = (BYTE *)MapViewOfFile (hMapFile, FILE_MAP_ALL_ACCESS, 0,  0,  0);
if(pMapFile==NULL) { printf("in Map1aDz MapVievOfFile error: %d\n", GetLastError());
                        getchar(); return 1; }

STARTUPINFO si = {0};  si.cb = sizeof(STARTUPINFO);  PROCESS_INFORMATION  pi;
sprintf(cmd1, "POT_1 %p", hMapFile);
BOOL OK = CreateProcess(0,cmd1, 0,0, TRUE, 0,0,0, &si,&pi);
	   if(!OK) { printf("CreateProcess error: %d\n", GetLastError());
                        getchar(); return 1; }
                        
memcpy(pMapFile,X, sizeX);
BYTE* pMapFile2=pMapFile+sizeX;
memcpy(pMapFile2,Y, sizeY);
pMapFile2+=sizeY;
memcpy(pMapFile2,Z, sizeZ);



/*   WatekGlowny(300, '*');
cout << "czas = " << GetTickCount() - T1 << endl;
DispV(50, X, "vektor X:");
DispV(50, Y, "vektor Y:");
DispV(50, Z, "vektor Z:");
*/
hMojeEvent = CreateEvent(NULL, TRUE,FALSE, "MojeEvent");
	if(hMojeEvent==NULL){  printf("CreateEvent error:");  getchar(); return 1;}

WaitForSingleObject(hMojeEvent, INFINITE);

cout << "Czytanie z pliku"<<endl;
memcpy(W,pMapFile+sizeof(double), sizeW);
memcpy(&Srednia,pMapFile, sizeof(double));
cout<<"Srednia: "<<Srednia<<endl;
DispV(50, W, "vektor W:");





cout << "Koniec Programu"; 
 cin.get();
return 0;
}

UINT WINAPI GenVec(LPVOID  parametr)
{
PARM *parm = (PARM*)parametr;
int n = parm->nData; 
double oda = parm->a, dob = parm->b, *X = parm->X;
for (int i = 0; i < n; i++){
	   if (i%100 == 0) cout << parm->zn; 
     X[i] = Generuj(oda, dob);
     }
}

UINT WINAPI BubbleSort(LPVOID  parametr)
{ 
DWORD T1 = GetTickCount();
PARM *parm = (PARM*)parametr;
cout<<*(parm->hwatek)<<endl;
WaitForSingleObject(*(parm->hwatek), INFINITE);
int size = parm->nData;  
double *X = parm->X, w;
for (int i = 1; i < size; i++){
   if (i%100 == 0) cout << parm->zn; 
  for (int j = size-1; j >= i; j--)
      if (X[j] < X[j - 1]) { w=X[j-1]; X[j-1]=X[j]; X[j]=w; };
      }
      
cout << "\nczasSort" << parm->zn <<'='<< GetTickCount() - T1 << "mS\n";
}


void DispV(int ile, double V[], char *text )
{
  cout << text;  
  for(int i=0; i < ile; i++) {
     if (i%8 == 0) cout << endl;     
     cout << setw(9) << V[i];
     }
     cout << endl;
} 

void WatekGlowny(int n, char zn)              // d³ugotrwa³e obliczenia
{
for (int k1=0; k1 < n; k1++){   
      for (int k2=0; k2<20000; k2++) pow(sin(k1),3.3)* pow(cos(k1),2.2); 
      cout << zn <<" ";
      } cout << "\n";
}


