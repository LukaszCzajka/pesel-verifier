# pesel-verifier
Program umożliwia weryfikację numeru PESEL wpisanego ręcznie przez użytkownika oraz weryfikację wielu numerów PESEL wczytanych z pliku tekstowego. Wynik weryfikacji zawięra informację czy podany PESEL jest poprawny, oraz w przypadku jeśli jest poprawny otrzymujemy takie informacje jak: płeć, datę urodzenia, oraz wiek "właściciela" numeru PESEL. Wynik jest wyświetlany w interfejsie graficznym oraz w przypadku weryfikacji wielu numerów wynik jest zapisywany do pliku o nazwie „.DoZapisu.txt”.

Program powstał w ramach nauki i oponowania podstaw języka Java oraz biblioteki Swing w celu uruchomienia programu w wersji okienkowej.  

# Jak uruchomić
Aby uruchomić program należy pobrać repozytorium a następnie otworzyć pobrany folder w IDE (np. IntelliJ IDEA). Następny krok to kompilacja oraz uruchomienie pliku z główną klasą src/Main.java.

# Wygląd aplikacji
![obraz](https://user-images.githubusercontent.com/106930800/232336669-91535cf9-d858-417d-9b18-4a6b08322b05.png)

Po wybraniu opcji pierwszej, tj. Weryfikuj wpisując PESEL, użytkownik może wprowadzić numer PESEL, następnie klikając przycisk „Weryfikuj” pokaże się wynik weryfikacji:

![obraz](https://user-images.githubusercontent.com/106930800/232336691-c4b26ac4-1f72-4663-ad63-a545769c2859.png)

Po wybraniu opcji drugiej, tj. „Weryfikuj PESEL z pliku” oraz uzupełnieniu pliku ”DoWeryfikacji.txt”* o numery PESEL które użytkownik chce zweryfikować, użytkownik otrzyma wyniki weryfikacji każdego rekordu w formie listy, oraz dodatkowo wyniki te zostaną zapisane w pliku „DoZapisu.txt”

*aby poprawnie zweryfikować numery PESEL z pliku txt, powinny być one zapisane każdy osobno w nowej linii.

![obraz](https://user-images.githubusercontent.com/106930800/232336698-6d596a0c-4c11-4824-94eb-b12ada9180e0.png)


Wszystkie dostępne i widoczne numery PESEL zostały losowo wygenerowane do celów testowych za pomocą strony:
https://pesel.cstudios.pl/o-generatorze/generator-on-line
