- Add unit tests to the code (test splices and such)
- Change all apis to use DTOS
- Validate fields in all DTOS
- Check the registration and login process in POSTMAN
- Make an API for logged-in user to be able to reserve a screening of a movie
- Add a bunch of movies to the database
- Allow not logged-in user to browse screenings
- Categorize screenings by dates
- Add Pagination
- Add cache
- Come up with the structure of the project
- Add CI/CD
- Add Liquidbase




Repertuar - Czym jest repertuar?

To będzie lista filmów podlegających ekranizacji w danym kinie
Musi być podzielona na datę ekranowania, każdy dzień będzi pokazywał jedynie filmy, które są danego dnia ekranizowane
Film może być ekranizowany w różnych godzinach, w różnych salkach

Trzeba się upewnić, że:
- Salki są blokowane na czas trwania filmu i żaden inny film nie może jej na daną porę zarezerwować


Sam film musi zawierać:
- ID
- Tytuł
- Datę premiery
- Czas trwania
- Opis filmu
- 
- Gatunek filmu
- 
- Obsadę
- Reżysera
- Produkcję
- Język orygnalny
- Ograniczenia wiekowe

Poza tym:
- Datę ekranizacji jednej instancji
- Godzinę ekranizacji jednej instancji
- Numer / Nazwę salki
- Tryb wyświetlania: Sam dźwięk czy dźwięk i napisy
- Język dźwięku
- Język napisów


Z tego wynika iż:
- Film, jako ogół, musi być jedną jednostką zawierającą generalne informacje o dziele
- Ekranizacja filmu, musi być osobnym bytem, podłączonym do filmu, który zawierał by już bardziej konkretne informacje
- 