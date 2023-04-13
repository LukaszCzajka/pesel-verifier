public class Pesel {


    private boolean correctPesel;
    private final int[] tabPesel = new int[11];
    public boolean getCorrectPesel() {
        return correctPesel;
    }
    public int[] getTabPesel() {
        return tabPesel;
    }
    String strPesel ;
    Frame frame = new Frame(strPesel);

    //---------------------------- Metody
    public String enterPesel() { // wprowadzenie nr PESEL
        strPesel = frame.peselStr;
        return strPesel;
    } // KONIEC METODY enterPesel

    public String verificationPesel(String strPesel){

        PeselOwner peselOwner = new PeselOwner();

        try {
            Long.parseLong(strPesel); // sprawdzamy czy wprowadzony ciąg jest liczbą, jeśli ciąg znaków jest niepoprawny, wykonuje sięinstrukcja 'catch (NumberFormatException e)' - czyli wyświetla się stosowny kominkat oraz następuje ponowne wywołanie metody enterPesel();
            if (strPesel.length() == 11) {
                strToIntPesel(strPesel);
                verByPattern();
                if (correctPesel) {
                    peselOwner.getInformation(getTabPesel(), getCorrectPesel());
                    String showDetalsPesel;// do wyświetlania szczegółowych info o nr pesel w jednym srtringu
                    showDetalsPesel = " | Płeć: " + peselOwner.genderCheck(getTabPesel()) + "  |  Data urodzin: " + peselOwner.getDayOfBirth() + "." + peselOwner.getMonthOfBirth() + "." + peselOwner.getYearOfBirth() + "r." + "  |  Wiek: " + peselOwner.getAge();
                    return strPesel + " - Pesel poprawny!" + showDetalsPesel;
                } else {
                     return strPesel + " - PESEL NIEPOPRAWNY";
                }
            } else if (strPesel.length() < 11) {
                correctPesel = false;
                return strPesel + " - PESEL NIEPOPRAWNY - zbyt krótki";
            } else {
                correctPesel = false;
                return strPesel + " - PESEL NIEPOPRAWNY - zbyt długi";
            }
        } catch (NumberFormatException e) { // jeśli nie ciąg znaków niepoprawny, wyświetla się stosowny kominkat
            correctPesel = false;
            return strPesel +  " - PESEL NIEPOPRAWNY - dopuszczalne tylko cyfry";
        }
    } // KONIEC METODY verificationPesel

    public void strToIntPesel(String strPesel) {

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
}//koniec klasy Pesel
