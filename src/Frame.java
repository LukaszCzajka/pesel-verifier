import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame implements ActionListener {
    JFrame frame = new JFrame("Weryfikator PESEL");
    JTextField jtf;         //pole do wpisania nr pesel przez użytkownika
    JButton goVerBtn;
    JList jlist;        //lista
    JScrollPane jscrp; //opakowanie listy
    JLabel verResult; // wynik weryfikacji
    JLabel fileInfo;
    JLabel fileLocation;
    JLabel infoAboutVerification;

    String peselStr;
    public Frame(String peselStr) {
        this.peselStr = peselStr;
    }
    FileManager fileManager;

    Frame(){
        fileManager  = new FileManager();

        //ogólne ustawienia okna app
        frame.setLayout(new FlowLayout()); //layout okna app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// niezbędne do poprawnego i całkowitego zamnięcia app
        frame.setResizable(false); // uniemozliwia użytkownikowi zmiane wielkości okna app

        //buttony
        JButton btn1 = new JButton("Weryfikuj wpisując PESEL");
        JButton btn2 = new JButton("Weryfikuj PESEL z pliku");
        goVerBtn = new JButton("Weryfikuj");

        //pola tekstowe JLabel
        JLabel textWhatToDo = new JLabel("Wybierz w jaki sposób chcesz zweryfikować nr PESEL", JLabel.CENTER);
        verResult = new JLabel("Wynik weryfikacji: ", JLabel.CENTER);
        fileInfo = new JLabel(    "Aby zweryfikowac nr pesel z pliku, powinny one znajdować się w pliku \"DoWeryfikacji.txt \"");
        fileLocation = new JLabel("Lokalizacja pliku to: " + fileManager.getFileDoWeryfikacji().getAbsolutePath());
        infoAboutVerification = new JLabel("Plik z wynikiem weryfikacji gotowy." + fileManager.getFileDoZapisu().getAbsolutePath());

        //Lista i jej elementy
        String testList[] = new String[fileManager.getListLenght()];
        jlist = new JList<>(testList); // stworzenie widocznej listy
        jscrp = new JScrollPane(jlist); //opakowuje liste panelem przewijającym

        //pole do wpisywania przez użytkownika, TextFiled
        jtf = new JTextField(25); // ustawienie długości pola jtf

        // ustawienie wielkości poszczególnych komponentów
        frame.setSize(650,500);
        textWhatToDo.setPreferredSize(new Dimension(550, 20));
        verResult.setPreferredSize(new Dimension(550, 20));
        jscrp.setPreferredSize(new Dimension(500, 300)); //rozmiar listy

        //nasłuchiwanie elementów
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        jtf.addActionListener(this);
        goVerBtn.addActionListener(this);

        // dodanie do frame poszczególnych komponentów
        frame.add(textWhatToDo);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(jtf);
        frame.add(goVerBtn);
        frame.add(verResult);
        frame.add(fileInfo);
        frame.add(fileLocation);
        frame.add(jscrp);
        frame.add(infoAboutVerification);

        //ustawienie wybranych komponentów jako niewidoczne aby można było wyświetlać konkretne po wybraniu akcji
        jtf.setVisible(false);
        goVerBtn.setVisible(false);
        verResult.setVisible(false);
        jscrp.setVisible(false);
        infoAboutVerification.setVisible(false);

        //konieczne aby cała aplikacja okienkowa/frame była widoczna
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        Pesel pesel = new Pesel();
           if(e.getActionCommand().equals("Weryfikuj wpisując PESEL")) {
               jtf.setVisible(true); // pole do wpisania
               goVerBtn.setVisible(true);
               verResult.setVisible(false);
               jscrp.setVisible(false);
               fileInfo.setVisible(false);
               fileLocation.setVisible(false);
               infoAboutVerification.setVisible(false);

           }else if(e.getActionCommand().equals("Weryfikuj PESEL z pliku")){
               jtf.setVisible(false);
               goVerBtn.setVisible(false);
               verResult.setVisible(false);
               jscrp.setVisible(true);
               fileInfo.setVisible(true);
               fileLocation.setVisible(true);
               infoAboutVerification.setVisible(true);

               // uruchamianie metod z klasy FileManager
               fileManager.checkFile();
               fileManager.readFile();
               fileManager.saveFile();

               //obsługa wyświetlanej listy po weryfikacji
               frame.revalidate(); // niezbędne do wyświetlania ramki listy po pierwszym kliknięciu po uruchumieniu aplikacji
               jlist.setListData(fileManager.peselFromFile.toArray());//aktualizuje zawartość listy jlist za pomocą
                                                                      //metody setListData() przekazajuąc jako argument liste nr pesel
               fileManager.peselFromFile.clear(); //czyści liste aby przy ponownym kliknięciu nie sumować listy, natomiast wykonywać od zera metode readFile()

            } else if (e.getActionCommand().equals("Weryfikuj")){
            jscrp.setVisible(false);
            verResult.setVisible(true);
            peselStr = jtf.getText();
            pesel.enterPesel();
            verResult.setText(pesel.verificationPesel(peselStr));
        }
    }
}
