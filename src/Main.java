import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;
        Pesel pesel = new Pesel();


        while (!exitMenu) {

            System.out.println("co chcesz zrobic?");
            System.out.println("1. Zweryfikować PESEL wpisując go w programie");
            System.out.println("2. Zweryfikować PESEL zaczytując z pliku");
            System.out.println("3. Wyjscie");

            int choiceMenu = scanner.nextInt();

            switch (choiceMenu) {
                case 1 -> {
                    System.out.println("weryfikujemy pesel wpisujac");
                    pesel.enterPesel();
                    pesel.showResult();
                }
                case 2 -> {
                    FileManager fileManager = new FileManager();
                    System.out.println("Weryfikacja nr PESEL nastąpi po odczycie z pliku.");
                    fileManager.checkFile();
                    pesel.checkerMenu = 2; // pozwala ustawic chcecker menu w Pesel.showResults.
                    pesel.showResult();

                }
                case 3 -> {

                    //poniewaz nr PESEL to dane wrażliwe, po zamknięciu programu pliki DoWeryfikacji.txt oraz DoZapisu.txt zostaną usunięte. czy chcesz kontynuować?
                    exitMenu = true;
                }
                default -> System.out.println("nie wybrano poprawnej opcji menu. dostępne opcje 1,2,3.");
            }
        }
    }
}