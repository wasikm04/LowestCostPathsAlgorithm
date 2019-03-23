# Algorytm maksymalnego przepływu przy minimalnym koszcie 

## Cel Programu

Celem programu było rozwiazanie problemu znalezienia połaczen pomiedzy
fermami a odbiorcami w postaci sklepów aby sumaryczny koszt był
najmniejszy. Otrzymujac dane o koszcie przewozu jednego jaja, produkcji
kazdej z ferm oraz zapotrzebowaniu sklepów musimy wyznaczyc najbardziej
opłacalne kursy dla dostawców jezdzacych na trasach łaczacych fermy
ze sklepami. Przy czym istnieje droga pomiedzy kazda ferma drobiu i kazdym
sklepem. Algorytm rozwiazania tego problemu opiera sie na algorytmie
Busackera - Gowena.

## Diagram Klas
<img src="https://github.com/wasikm04/LowestCostPathsAlgorithm/blob/master/img/diagram_klas.png" width="800"/>

## Opis Klas i metod

- Models
Pakiet zawiera klasy których instancje słuzyc beda łatwiejszej organizacji
wczytanych z pliku danych i beda dawały mozliwosc aktualizowania
tylko tych danych które chcemy. W skład pakietu wchodza
klasy:
    - Farm
Zawiera pola opisujace ferme jaj, id, nazwe oraz dzienna produkcje.
Metody to gettery i settery.
    - Shop
Klasa która posiada pola id sklepu, nazwe oraz dzienne zapotrzebowanie
na jaja. Metody potrzebne do aktualizacji to gettery
i settery.
    - Path
Zawiera informacje o połaczeniach sklepu z farma czyli w jej skład
wchodza pola id sklepu, id farmy, maksymalny dzienny przewóz
czyli przepustowosc oraz koszt przewozu.
    - Transaction
Instancje tej klasy beda informacje o danych przepływach oraz ich
zródłach i odbiorcach oraz zawierac bedzie metode liczaca koszt
danego przepływu. Klasa posiada nadpisana metode toString
aby łatwo móc wykorzystac ja przy zapisywaniu danych do pliku
wyjsciowego.

- Tools
Pakiet w którego skład beda wchodzic klasy:
    - Control
Metoda main w której program zaczyna swe działanie. W metodzie
tej tworzona jest instancja klasy Algorithm a po wykonaniu
obliczen i wpisaniu ich do listy z transakcjami sa one wpisywane
do pliku wyjsciowego korzystajac z metody write() klasy
Writer.
    - Reader
void read(String filepath, Map farms, Map shops, Map paths)
Metoda wczytuje dane z pliku txt. Sprawdza ilosc danych oraz
ich poprawnosc a w razie nieprawidłowosci rzuca wyjatek.
    - Writer
void write(ArrayList<Transaction> results)
Metoda ta zapisuje do pliku informacje o przeprowadzonych transakcjach,
przechwytuje wyjatek rzucany gdy plik z danymi o tej
nazwie juz istnieje.
    - Algorithm
        - void calculate(ArrayList<Transaction> results) Jest
to główna metoda klasy Algorithm gdzie korzystajac z metod
pomocniczych w kolejnych krokach wyznaczane sa sciezki
przesyłu jajek. Te metode wywołujemy w main przekazujac
liste w której umiescimy ostateczne transakcje.
        - void findLowestCosts() Jest to metoda słuzaca znalezieniu
najtanszego połaczenia do kazdego ze sklepów sposród
wszystkich połaczen w danej iteracji.
        - void updatePathsCosts() Metoda ta korzystajac z wczesniej
wyznaczonych najtanszych kosztów transportu do kazdego
ze sklepów uaktualnia wszystkie sciezki odejmujac od
ich starego kosztu najtanszy dla danego sklepu.
        - Path findBestWay() Dla aktualnych kosztów połaczen farm
z sklepami metoda ta wyznacza połaczenia najtansza, oblicza
dla kazdego potencjał i zwraca połaczenie o najnizszym potencjale
jako najbardziej odpowiednia sciezke przesyłu jajek
w danej sytuacji.
        - int findFlow(Path P) Słuzy do wyznaczenia i zwracania
ile maksymalnie jaj mozna przesłac po wybranej, najlepszej
sciezce P biorac pod uwage pozostała ilosc jaj w fermie, pozostałe
zapotrzebowanie w sklepie, limit przesyłu jaj po tej
sciezce oraz nadmiar i deficyt superzródła i superujscia.
        - void updateInformations(int flow, Path P) Metoda pozwalajaca
na uaktualnienie informacji zawartych we wszystkich
połaczeniach, sklepie oraz farmie majacych zwiazek z ostatnia
transakcja tak by kolejne obliczenia mogły odbywac sie
na prawidłowych danych.

## Wnioski
Program jest uruchamiany w wierszu polecen, znajdujac sie w folderze
z archiwum jar zawierajacym program polecenie uruchomienia wyglada
nastepujaco:
```commandline
    java -jar Projekt_Indywidualny.jar dane_wejsciowe.txt
```
Jako pierwszy argument podajemy sciezke do pliku wejsciowego, w przykładzie
jest to plik dane_wejsciowe.txt znajdujacy sie w tym samym folderze
co plik z naszym programem w formacie jar. Gdy nie podamy argumentu
program wykona sie dla przykładowych danych. Plik wejsciowy musi
byc w formacie txt i posiadac nastepujace sekcje wypełnione danymi:
```commandline
 Fermy drobiu (id | nazwa | dzienna produkcja)
[id] [nazwa] [dzienna produkcja]
 Sklep (id | nazwa | dzienne zapotrzebowanie)
[id] [nazwa] [dzienne zapotrzebowanie]
 Połaczenia ferm i sklepów (id fermy | id sklepu | dzienna maksymalna
liczba przewozonych jaj | koszt przewozu jednego jaja)
[id fermy] [id sklepu] [dzienny maksymalny przewóz jaj] [koszt przewozu jaja]
```
