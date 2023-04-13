import java.time.LocalDate;

public class PeselOwner {
    private int age;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;

    //gettery
    public int getDayOfBirth() {
        return dayOfBirth;
    }
    public int getMonthOfBirth() {
        return monthOfBirth;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public int getAge() {
        return age;
    }

    LocalDate localDate = LocalDate.now(); // zwraca obecną datę

    void getInformation(int[] tabPesel, boolean correctPesel){

        if(correctPesel) {
            bDay(tabPesel);
            genderCheck(tabPesel);
            checkAge();
       }
    } //KONIEC METODY getInformation

    void bDay(int[] tabPesel){
        int century; // obliczane stulecie urodzin
        if(tabPesel[2] == 8 || tabPesel[2] == 9){
            century = 18;
            tabPesel[2] -= 8;
        } else if (tabPesel[2] == 0 || tabPesel[2] == 1) {
            century = 19;
        } else {
            century = 20;
            tabPesel[2] -= 2;
        }
        dayOfBirth = (tabPesel[4] * 10) + tabPesel[5];                //day
        monthOfBirth = tabPesel[2] * 10 + tabPesel[3];                  //month
        yearOfBirth = century * 100 + tabPesel[0] * 10 + tabPesel[1];  //year
    }//KONIEC METODY bDay

    char genderCheck(int[] tabPesel){
        char gender;
        if(tabPesel[9] % 2 == 0){
            return gender = 'F';
        }else {
            return gender = 'M';
        }
    }// KONIEC METODY genderCheck

    void checkAge(){ //obliczanie aktualnego wieku
        int currentDay = localDate.getDayOfMonth();              //obecna data, dzień
        int currentMonth = localDate.getMonthValue();            //obecna data, miesiąc
        int currentYear = localDate.getYear();                   //obecna data, rok
        int lengthOfCurrentMonth = localDate.lengthOfMonth();    //informacja ile dni ma obecny miesiąc

        if(dayOfBirth > currentDay){
            currentDay += lengthOfCurrentMonth;
            currentMonth = currentMonth -1;
        }
        if(monthOfBirth > currentMonth){
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }
        int calculatedDay = currentDay - dayOfBirth;
        int calculatedMonth = currentMonth - monthOfBirth;
        int calculatedYear = currentYear - yearOfBirth;

        // powyższe 3 zmienne mogą posłużyć gdybyśmy chcieli wyświetlić konkretny wiek użytkownika. tj
        //ile przeżył lat, ile miesięcy, ile dni
        //warto zauważyć, że dzięki localDate dostajemy bardzo dokładne wyniki - co do dnia, uwzględniające równiez lata przestępne
        age = calculatedYear; // ustawia aktulny wiek właściciela pesel
    }//KONIEC METODY changeAge
}// KONIEC KLASY
