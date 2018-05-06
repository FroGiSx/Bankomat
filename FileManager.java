package body;


import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class FileManager {
    private String fileName = "User.txt";
    boolean passw = false;
    private String pass;
    private String login;
    private int money;

    private void setLogin(String login){
        this.login = login;
    }
    private void setPass(String pass){
        this.pass = pass;
    }

    public void information() throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scann = new Scanner(file);
        Scanner scan = new Scanner(System.in);
        String imie = "";
        String hasło;

        while (!imie.equals(login + ":")){
            imie = scann.nextLine();
        }
        hasło = scann.nextLine();
        String moneys = scann.nextLine();
        money = Integer.parseInt(moneys.replace("  pieniądze: ",""));

        System.out.println("login: " + login);
        System.out.println("hasło: " + hasło.replace("  hasło: ",""));
        System.out.println("Stan konta: " + money);
        System.out.println("kliknij enter aby cofnąć");
        scan.nextLine();
    }
    public void createFile() {
        File file = new File(fileName);

        boolean fileExists = file.exists();
        if(!fileExists) {
            try {
                fileExists = file.createNewFile();
            } catch (IOException e) {
                System.out.println("Nie udało się utworzyć pliku");
            }
        }

        if(fileExists)
            System.out.println("Plik istnieje lub został utworzonony");
    }
    public void login() throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scann = new Scanner(file);
        Scanner scan = new Scanner(System.in);

        String login1, hasło , hasło2 , moneys;
        String imie = "";

        System.out.println("Podaj login");
        login1 = scan.nextLine();
        setLogin(login1);
        while (!imie.equals(login1 + ":")){
            imie = scann.nextLine();
        }
        System.out.println("Podaj hasło");
        hasło = scan.nextLine();
        hasło2 = scann.nextLine().replace("  hasło: ", "");
        setPass(hasło2);
        moneys = scann.nextLine();
        money = Integer.parseInt(moneys.replace("  pieniądze: ",""));
        if (hasło.equals(hasło2)){
            System.out.println("Zalogowano");
            passw = true;
        }
    }
    public void register(){
        String login, hasło, hasło2;
        Scanner scan = new Scanner(System.in);
        File file = new File(fileName);
        System.out.println("podaj login");
        login = scan.nextLine();
        System.out.println("podaj hasło");
        hasło = scan.nextLine();
        System.out.println("powtórz hasło");
        hasło2 = scan.nextLine();
        Random r = new Random();
        if (hasło.equals(hasło2)){
            int rmoney = r.nextInt(10000);
            System.out.println("Na start dajemy ci " +rmoney+ " pieniędzy");
            System.out.println("kliknij enter aby potwierdzić");

            try(
                    FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter writer = new BufferedWriter(fileWriter)
            ){
                Scanner scann = new Scanner(file);
                if (scan.hasNextLine()){
                    writer.newLine();
                }
                writer.write(login + ":");
                writer.newLine();
                writer.write("  hasło: " + hasło);
                writer.newLine();
                writer.write("  pieniądze: " + rmoney);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Utworzono" + "\n");
        } else {
            System.out.println("hasła się nie zgadzają");
        }
    }
    public void changePass() {
        try
        {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder oldText = new StringBuilder();
            Scanner scan = new Scanner(System.in);
            while((line = reader.readLine()) != null)
            {
                oldText.append(line).append("\r\n");
            }
            reader.close();
            System.out.println("Podaj stare hasło");
            String oldPass = scan.nextLine();
            if (oldPass.equals(pass)) {
                System.out.println("Podaj nowe hasło");
                String newPass = scan.nextLine();

                String newtext = oldText.toString().replaceAll(oldPass, newPass);

                FileWriter writer = new FileWriter(fileName);
                System.out.println("Zmieniono");
                writer.write(newtext);
                writer.close();
            }
            else {
                System.out.println("Złe hasło");
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public void cash(){
        Scanner scan = new Scanner(System.in);

        String a;
        int b;
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder oldText = new StringBuilder();
            while((line = reader.readLine()) != null)
            {
                oldText.append(line).append("\r\n");
            }
            reader.close();
            int oldMoney = money;

            System.out.println("Wybierz opcje:");
            System.out.println("Wpłać - wpłać pieniądze");
            System.out.println("Wypłać - wypłać pieniądze");
            a = scan.nextLine();
            switch (a){
                case ("Wpłać"):
                    System.out.println("ile chcesz wpłacić ?");
                    b = scan.nextInt();
                    int newMoney = oldMoney + b;
                    String nMoney = Integer.toString(newMoney);
                    String oMoney = Integer.toString(oldMoney);
                    String newText = oldText.toString().replaceAll(oMoney,nMoney);
                    System.out.println("aktualny stan konta wynosi: " + nMoney);
                    FileWriter writer = new FileWriter(fileName);
                    writer.write(newText);
                    writer.close();
                    break;

                case ("Wypłać"):
                    System.out.println("ile chcesz wypłacić ?");
                    b = scan.nextInt();
                    if (b <= oldMoney) {
                        newMoney = oldMoney - b;
                        nMoney = Integer.toString(newMoney);
                        oMoney = Integer.toString(oldMoney);
                        newText = oldText.toString().replaceAll(oMoney, nMoney);
                        System.out.println("aktualny stan konta wynosi: " + nMoney);
                        FileWriter write = new FileWriter(fileName);
                        write.write(newText);
                        write.close();
                    } else {
                        System.out.println("Nie masz tyle pieniędzy");
                    }
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
