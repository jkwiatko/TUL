CREATE DATABASE IF NOT EXISTS PortalA;
USE PortalA;
/*TABLICE SLOWNIKOWE*/
CREATE TABLE IF NOT EXISTS Pomieszczenie
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(32) NOT NULL
);
CREATE TABLE IF NOT EXISTS Rodzaj
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(32) NOT NULL
);
/*THAT IS ALL*/
--------------------------------
CREATE TABLE IF NOT EXISTS Administrator
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, -- Błąd
    nazwa VARCHAR(32) NULL,
    login VARCHAR(32) NOT NULL,
    haslo VARCHAR(32) NOT NULL,
    aktywny INT NOT NULL
);
--------------------------------
CREATE TABLE IF NOT EXISTS Nieruchomosc
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, -- Błąd
    administrator_id INT UNSIGNED NOT NULL,
    miasto VARCHAR(32) NULL, 
    ulica VARCHAR(32) NULL, 
    numer INT NULL,
	
    FOREIGN KEY (administrator_id)
    REFERENCES Administrator(id)
);

CREATE TABLE IF NOT EXISTS Uzytkownik
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    nazwa VARCHAR(32) NULL, 
    login VARCHAR(32) NOT NULL, 
    hasło VARCHAR(32) NOT NULL, 
    aktywny INT UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS Lokal
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nieruchomosc_id INT UNSIGNED NOT NULL, 
    uzykownik_id INT UNSIGNED,
    numer VARCHAR(5),
	
    FOREIGN KEY (nieruchomosc_id)
    REFERENCES Administrator(id), 
    
    FOREIGN KEY (uzykownik_id)
    REFERENCES Uzytkownik(id)
);

CREATE TABLE IF NOT EXISTS Wodomierz
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    lokal_id INT UNSIGNED NOT NULL,
    rodzaj_id INT UNSIGNED NOT NULL, 
    pomieszczenie_id INT UNSIGNED NOT NULL, 
    numer INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (lokal_id)
    REFERENCES Lokal(id), 
    
    FOREIGN KEY (rodzaj_id)
    REFERENCES Rodzaj(id),
    
    FOREIGN KEY (pomieszczenie_id)
    REFERENCES Pomieszczenie(id)
);

CREATE TABLE IF NOT EXISTS Odczyt
(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    wodomierz_id INT UNSIGNED,
    data_odczytu date NOT NULL,
    wartosc FLOAT NOT NULL,
    
    FOREIGN KEY (wodomierz_id)
    REFERENCES Wodomierz(id)
);
