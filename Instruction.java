package body;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Instruction {
    private Config config = new Config();
    private FileManager file = new FileManager();
    private static final String LET_RED = "\u001B[31m";
    private static final String LET_RESET = "\u001B[0m";
    private boolean passw = false;

    private void account() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Wybierz opcje:");
        System.out.println("register - nowe konto");
        System.out.println("login - zaloguj");
        String option = scan.nextLine();
        switch (option){
            case ("register"):
                file.register();
                break;
            case ("login"):
                file.login();
                break;
        }
    }
    public void start() throws IOException {
        String l = "Start";
        Scanner scan = new Scanner(System.in);
        account();
        if (file.passw)
            passw = true;
            file.passw = false;
        if (passw) {
            while (!l.equals("Koniec")) {

                System.out.println("\n" + LET_RED + "Wybierz kategorie:");
                System.out.println(LET_RESET + "Pieniądze - dodaj / wypłać");
                System.out.println("Informacje - informacje o uzytkowniku");
                System.out.println("Zmiana - zmiana hasła");
                System.out.println("Wyloguj - wylogowywanie");
                System.out.println("Koniec - koniec");
                l = scan.nextLine();

                switch (l) {
                    case ("Pieniądze"):
                        file.cash();
                        break;
                    case ("Zmiana"):
                        file.changePass();
                        break;
                    case ("Informacje"):
                        file.information();
                        break;
                    case ("Wyloguj"):
                        System.out.println("wylogowywanie ....");
                        passw = false;
                        start();
                    case ("Koniec"):
                }
            }
        } else {
            start();
        }
    }
}
