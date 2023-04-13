import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {  //klasa odpowiedzialna za wczytywanie i zapisywanie plików oraz ich weryfikację

    Pesel pesel = new Pesel();
    private File fileDoWeryfikacji = new File("DoWeryfikacji.txt");
    private File fileDoZapisu = new File("DoZapisu.txt");
    List<String> peselFromFile = new ArrayList<>();// do weryfikacji pliku,
    private int listLenght = peselFromFile.size(); //ilość pozycji w pliku, zmienna stworzona po to aby wysłać wartość do frame
                                            // w celu stworzenia tablicy[peselFromFile
    String strPesel;

    public File getFileDoWeryfikacji() {
        return fileDoWeryfikacji;
    }
    public File getFileDoZapisu() {
        return fileDoZapisu;
    }

    public int getListLenght() {
        return listLenght;
    }

    void checkFile() {
        boolean doWeryfikacjiExist = fileDoWeryfikacji.exists();  //czy plik istnieje
        boolean doZapisuExist = fileDoZapisu.exists();

        if (!doWeryfikacjiExist || !doZapisuExist) {            // jeśli jeden lub drugiplik nie itsnieje - tworzy go
            try {
                FileWriter writer = new FileWriter("DoWeryfikacji.txt");
                System.out.println("Plik DoWeryfikacji utworzony.");
                writer = new FileWriter("DoZapisu.txt");
                System.out.println("Plik DoZapisu utworzony.");

            } catch (IOException e) {
                System.out.println("Błąd przy tworzeniu pliku DoWeryfikacji.txt");
            }
        }
    } //KONIEC METODY checkFile

    void readFile() {
        try {
            Scanner fileScanner = new Scanner(fileDoWeryfikacji); // odczytuje wskazany plik(file)
            int i = 0;
            while (fileScanner.hasNext()) { // sprawdza kazdą linie pliku, jeśli nie ma następnej lini - koniec pętli
                peselFromFile.add(fileScanner.nextLine()); // przypisuje do listy kolejną linie. = kolejny index listy = kolejna linia pliku
                i++;
            }
            listLenght = peselFromFile.size();
        } catch (IOException e) { //komunikat do niespodziewanego błędu
            System.out.println("Błąd w odczycie pliku DoWeryfikacji.txt");
        }
    }//KONIEC METODY readFile

    void saveFile(){
        PeselOwner peselOwner = new PeselOwner();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("DoZapisu.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pozycje wypisane w pliku: " + peselFromFile.size());

        for (int i = 0; i < peselFromFile.size(); i++) { // pętla pozwala zweryfikować każdą linię pliku
                                                        // a następnie zapisać wynik weryfikacji jako każdy nowy wiersz do pliku
            strPesel = peselFromFile.get(i); // strPesel jako niezbędny argument do metody verificationPesel. w pętli przypisuje do siebie element listy a w następnej itereacji kolejny aż do końca listy
            peselOwner.getInformation(pesel.getTabPesel(), pesel.getCorrectPesel()); // wykonuje funkcje które obliczają: wiek, daty urodzenia, płeć
            writer.println(strPesel + "\t| " + pesel.verificationPesel(strPesel)); // zapisuje nr pesel oraz wynik weryfikacji w jednym wierszy pliku "DoZapisu.txt"
            peselFromFile.set(i, pesel.verificationPesel(strPesel)); // przypisanie do listy nowo utworzonych, finalnych stringów
            writer.println("---------------------------------");//odstęp wizualny w pliku między rekordami
        }
        writer.close(); // zamyka zapis pliku. niezbędne do udanego zapisu
    }

}//KONIEC KLASY FileManager

