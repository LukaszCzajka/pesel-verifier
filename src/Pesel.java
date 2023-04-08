import java.io.*;
import java.util.Scanner;
public class Pesel {
    //dodać weryfikacja na zasadzie miesiąc z liczbą 31 a dni więcej niz 31 - odrzucony
    //zająć się poprawnym wyświetlaniem wieku uż

    private String strPesel;
    private int checkerMenu;
    private boolean correctPesel;
    private final int[] tabPesel = new int[11];

    public void setCheckerMenu(int checkerMenu) {
        this.checkerMenu = checkerMenu;
    }

    public boolean getCorrectPesel() {
        return correctPesel;
    }
    public int[] getTabPesel() {
        return tabPesel;
    }

    Scanner scanner = new Scanner(System.in);

    //---------------------------- Metody

    public String enterPesel() { // wprowadzenie nr PESEL
        System.out.println("prosze podac nr pesel: ");
        strPesel = scanner.nextLine();
        checkerMenu = 1;
        return strPesel;
    } // KONIEC METODY enterPesel

    public String verificationPesel(String strPesel){

        try {
            Long.parseLong(strPesel); // sprawdzamy czy wprowadzony ciąg jest liczbą, jeśli ciąg znaków jest niepoprawny, wykonuje sięinstrukcja 'catch (NumberFormatException e)' - czyli wyświetla się stosowny kominkat oraz następuje ponowne wywołanie metody enterPesel();

            if (strPesel.length() == 11) {
                strToIntPesel();
                verByPattern();
                if (correctPesel) {
                     correctPesel = true;
                    return "Pesel poprawny!";
                } else {
                     correctPesel = false;
                     return "PESEL NIEPOPRAWNY";
                }
            } else if (strPesel.length() < 11) {
                correctPesel = false;
                return "PESEL NIEPOPRAWNY - zbyt krótki";
            } else {
                correctPesel = false;
                return "PESEL NIEPOPRAWNY - zbyt długi";
            }
        } catch (NumberFormatException e) { // jeśli nie ciąg znaków niepoprawny, wyświetla się stosowny kominkat oraz następuje ponowne wywołanie metody enterPesel();
            //System.out.println("PESEL powinien składać się wyłącznie z cyfr. Wprowadź tylko cyfry.");
           // enterPesel();
            correctPesel = false;
            return "PESEL NIEPOPRAWNY - dopuszczalne tylko cyfry";
           // return "PESEL powinien składać się wyłącznie z cyfr. Wprowadź tylko cyfry.";
        }
    } // KONIEC METODY verificationPesel

    private void strToIntPesel() {

        for (int i = 0; i < strPesel.length(); i++) {
            char znak = strPesel.charAt(i);                      //tworzymy z każdego znaku stringa 1 char/znak
            int cyfra = Character.getNumericValue(znak);         // w/w char/znak zamieniamy na int
            tabPesel[i] = cyfra;                                 // wygenerowany int przypisujemy do tablicy. pętla przechodzi całego stringa dając całą tablice

        }
    }//KONIEC METODY strToIntPesel

    private boolean verByPattern(){ //weryfikacja nr PESEL za pomocą wzoru dostępnego na https://www.gov.pl/web/gov/czym-jest-numer-pesel
        int check;
        int lastCheck;

        check = tabPesel[0] + 3*tabPesel[1] + 7*tabPesel[2] + 9*tabPesel[3] + tabPesel[4] + 3*tabPesel[5] + 7*tabPesel[6] + 9*tabPesel[7] + tabPesel[8] + 3*tabPesel[9];
        lastCheck = check % 10;

        if((lastCheck - 10) * (-1) == tabPesel[10] && (tabPesel[4] * 10 + tabPesel[5]) < 32 ){
            // weryfikacja cyfry kontrolnej. ((tabPesel[4] * 10 + tabPesel[5]) < 32) - dodatkowo sprawdza czy podany pesel ma miesiąc urodzenia < 13 oraz dzień urodzenia < 32. Gdyby nie ten warunek pesel 48954584584 byłby poprawny, choć w rzeczyistości nie jest.
            return correctPesel = true;
        }else {
            return correctPesel = false;
        }
    }//KONIEC METODY verification Pesel

    void showResult() throws FileNotFoundException {
        FileManager fileManager = new FileManager();
        PeselOwner peselOwner = new PeselOwner();

        if(checkerMenu == 1){ //wykonuje i prezentuje dla menu 1
            verificationPesel(strPesel);
            peselOwner.getInformation(tabPesel, correctPesel);
            if (correctPesel) { // prezentacja informacji o nr PESEL tylko jeśli jest poprawny
                System.out.println(verificationPesel(strPesel) + "\t|  Płeć: " + peselOwner.genderCheck(tabPesel) + "\t|  Data urodzin: " + peselOwner.getDayOfBirth() + "." + peselOwner.getMonthOfBirth() + "." + peselOwner.getYearOfBirth() + "r." + "\t|  Wiek: " + peselOwner.getAge());
            } else {
                System.out.println(verificationPesel(strPesel));
            }
        } else if (checkerMenu == 2) {//wykonuje i prezentuje dla menu 2
            PrintWriter writer = new PrintWriter("DoZapisu.txt");
            fileManager.readFile();
            System.out.println("Pozycje wypisane w pliku: " + fileManager.peselFromFile.size());

            for (int i = 0; i < fileManager.peselFromFile.size(); i++) { // pętla pozwala zweryfikować każdą linię pliku
                strPesel = fileManager.peselFromFile.get(i); // strPesel jako niezbędny argument do metody weryfkiacji. w pętli przypisuje do siebie element listy a w następnej itereacji kolejny aż do końca listy
                //wyświetlenie w koncoli:
                System.out.println("Wynik dla nr PESEL: " + strPesel);
                verificationPesel(strPesel); // odpalane aby można było wykonać peselOwner.showInformation(tabPesel, correctPesel);
                peselOwner.getInformation(tabPesel, correctPesel); // wykonuje funkcje które obliczają: wiek, daty urodzenia, płeć
                System.out.println(verificationPesel(strPesel));// + "\t|  Płeć: " + peselOwner.genderCheck(tabPesel) + "\t|  Data urodzin: " + peselOwner.getDayOfBirth() + "." + peselOwner.getMonthOfBirth() + "." + peselOwner.getYearOfBirth() + "r." + "\t|  Wiek: " + peselOwner.getAge());
                if (correctPesel){     // prezentacja informacji o nr PESEL tylko jeśli jest poprawny
                    System.out.println("Płeć: " + peselOwner.genderCheck(tabPesel) + "\t|  Data urodzin: " + peselOwner.getDayOfBirth() + "." + peselOwner.getMonthOfBirth() + "." + peselOwner.getYearOfBirth() + "r." + "\t|  Wiek: " + peselOwner.getAge());
                }
                System.out.println("---------------------------------");

                //zapis w pliku:
                writer.println(strPesel + "\t| " + verificationPesel(strPesel));
                if (correctPesel){     // prezentacja informacji o nr PESEL tylko jeśli jest poprawny
                    writer.println("Płeć: " + peselOwner.genderCheck(tabPesel) + "\t|  Data urodzin: " + peselOwner.getDayOfBirth() + "." + peselOwner.getMonthOfBirth() + "." + peselOwner.getYearOfBirth() + "r." + "\t|  Wiek: " + peselOwner.getAge());
                }
                writer.println("---------------------------------");
            }
            writer.close(); // zamyka zapis pliku. niezbędne do udanego zapisu
            fileManager.peselFromFile.clear(); // czysci listę
            System.out.println("Sprawdzone numery PESEL wraz z wynikiem zostały zapisane w pliku: \"DoZapisu.txt\" w lokalizacji: " + fileManager.fileDoZapisu.getAbsolutePath());
        }
    }//KONIEC METODY showResults
}//koniec klasy Pesel
