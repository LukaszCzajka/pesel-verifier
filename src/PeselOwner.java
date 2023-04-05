import java.time.LocalDate;

public class PeselOwner {
    char gender;
    int age;
    int dayOfBirth;
    int monthOfBirth;
    int yearOfBirth;

    Pesel pesel = new Pesel();

    LocalDate localDate = LocalDate.now(); // zwraca obecną datę

    int[] tabPesel = pesel.getTabPesel();
    boolean correctPesel = pesel.getCorrectPesel();

    void getInformation(int[] tabPesel, boolean correctPesel){

        if(correctPesel) {
            bDay(tabPesel);
            genderCheck(tabPesel);
            checkAge();

       }
    } //KONIEC METODY getInformation

    void bDay(int tabPesel[]){
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

    char genderCheck(int tabPesel[]){
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
        }
        if(monthOfBirth > currentMonth){
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }
        int calculatedDay = currentDay - dayOfBirth;
        int calculatedMonth = currentMonth - monthOfBirth;
        int calculatedYear = currentYear - yearOfBirth;
    }//KONIEC METODY changeAge

}// KONIEC KLASY
