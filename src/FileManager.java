import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {  //klasa odpowiedzialna za wczytywanie i zapisywanie plików oraz ich weryfikację

    Pesel pesel = new Pesel();
    public File fileDoWeryfikacji = new File("DoWeryfikacji.txt");
    public File fileDoZapisu = new File("DoZapisu.txt");
    List<String> peselFromFile = new ArrayList<>();// do weryfikacji pliku
   // int checkerMenu;
    void checkFile(){
        //pesel.checkerMenu = 2//nie dziala, dlaczeho?
        //pesel.setCheckerMenu(2); nie dziala, dlaczego?
        boolean doWeryfikacjiExist = fileDoWeryfikacji.exists();  //czy plik istnieje
        boolean doZapisuExist = fileDoZapisu.exists();
        System.out.println("czy dower istnieje?: " + doWeryfikacjiExist);
        System.out.println("czy dozap istnieje?: " + doZapisuExist);

        if(!doWeryfikacjiExist || !doZapisuExist) {            // jeśli jeden lub drugiplik nie itsnieje - tworzy go
            try {
                FileWriter writer = new FileWriter("DoWeryfikacji.txt");
                System.out.println("Plik DoWeryfikacji utworzony.");
                writer = new FileWriter("DoZapisu.txt");
                System.out.println("Plik DoZapisu utworzony.");


            } catch (IOException e) {
                System.out.println("Błąd przy tworzeniu pliku DoWeryfikacji.txt");
            }
        }
        System.out.println("W pliku DoWeryfikacji.txt powinny się znajdować nr PESEL, które chcesz zweryfikować.");
        System.out.println("Lokalizacja pliku to: " + fileDoWeryfikacji.getAbsolutePath());
        System.out.println("Pamiętaj, aby numery PESEL były pod sobą oraz nie zawierały żadnych dodatkowych znaków");

    } //KONIEC METODY checkFile

    void readFile() {
        try {
            Scanner fileScanner = new Scanner(fileDoWeryfikacji); // odczytuje wskazany plik(file)
            int i = 0;
            while (fileScanner.hasNext()){ // sprawdza kazdą linie pliku, jeśli nie ma następnej lini - koniec pętli
                peselFromFile.add(fileScanner.nextLine()); // przypisuje do listy kolejną linie. = kolejny index listy = kolejna linia pliku
                i++;
            }
            System.out.println("LIsta " + peselFromFile.size());

        } catch (IOException e){ //komunikat do niespodziewanego błędu
            System.out.println("Błąd w odczycie pliku DoWeryfikacji.txt");
        }
    }//KONIEC METODY readFile
}//KONIEC KLASY FileManager
