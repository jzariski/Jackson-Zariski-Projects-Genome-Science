import java.io.*;
import java.awt.*;
import java.util.*;

public class FileAnalyzer {
    public static void main(String[] args) throws FileNotFoundException{

        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.print("Input file name: ");
        String inputFileName = console.next();
        System.out.print("fastq or bed? ");
        String answer = console.next();

        AnalyzeFile test = new AnalyzeFile(inputFileName, answer);

        System.out.println();

        System.out.println("Choose an action:");
        System.out.println("1: Check G");
        System.out.println("2: Get Info");
        System.out.println("3: Check Plus/Minus");
        System.out.println("4: Exit");

        System.out.print("Enter 1,2,3, or 4: ");
        int choice = console.nextInt();

        while (choice == 1 || choice == 2 || choice == 3) {
            if (choice == 1) {
                System.out.println();
                System.out.print("Percent to check? ");
                double checkPercent = Double.parseDouble(console.next());
                test.checkGPercent(checkPercent);
            } else if (choice == 2) {
                test.getInfo();
            } else if (choice == 3) {
                test.checkPlusMinus();
            }
            System.out.println();

            System.out.println("Choose an action:");
            System.out.println("1: Check G");
            System.out.println("2: Get Info");
            System.out.println("3: Check Plus/Minus");
            System.out.println("4: Exit");
            System.out.print("Enter 1,2,3, or 4: ");
            choice = console.nextInt();
        }

    }

}