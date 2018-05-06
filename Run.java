package run;

import body.FileManager;
import body.Instruction;

import java.io.IOException;
import java.util.Scanner;


public class Run {
    public static void main(String[] args) throws IOException {
        String ver = "Bankomat v0.2";

        Scanner scan = new Scanner("User.txt");
        Instruction instruction = new Instruction();
        FileManager file = new FileManager();

        System.out.println(ver);
        file.createFile();
        instruction.start();
    }

}
