import java.util.*;
import java.awt.*;
import java.io.*;

public class CompareMetaTerminal {
   public static void main(String[] args) throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      System.out.print("Normal File: ");
      File normalFile = new File(console.nextLine());
      System.out.print("ReverseFile: ");
      File reverseFile = new File(console.nextLine());
      
      Scanner normalInput = new Scanner(normalFile);
      Scanner reverseInput = new Scanner(reverseFile);
      
      String normalLine = normalInput.nextLine();
      String reverseLine = reverseInput.nextLine();
      
      Scanner normalLineScan = new Scanner(normalLine);
      Scanner reverseLineScan = new Scanner(reverseLine);
      
      System.out.println("Probe Difference: " + parseThroughInt(normalLineScan, reverseLineScan));
      System.out.println("Kilobase Difference: " + parseThroughDouble(normalLineScan, reverseLineScan));
      System.out.println("Probe/Kilobase Difference: " + parseThroughDouble(normalLineScan, reverseLineScan)); 
   } 
   
   
   public static int parseThroughInt(Scanner normal, Scanner reverse) {
      String ns = "Beliveau";
      String rs = "Lab";
      for (int i = 1; i <= 3; i++) {
         ns = normal.next();
         rs = reverse.next();
      }
      return (Integer.parseInt(rs) - Integer.parseInt(ns));
   }
   
   public static double parseThroughDouble(Scanner normal, Scanner reverse) {
      String ns = normal.next();
      String rs = reverse.next();
      return (Double.parseDouble(rs) - Double.parseDouble(ns));
   }     
}