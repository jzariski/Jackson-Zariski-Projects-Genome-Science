import java.io.*;
import java.awt.*;
import java.util.*;

public class CompareMeta {
   
   private File normFile;
   private File revFile;
   
   public CompareMeta(File normalFile, File reverseFile) throws FileNotFoundException {
      normFile = normalFile;
      revFile = reverseFile; 
   }
   
   public void parseAndPrint() throws FileNotFoundException {
   
      Scanner normalInput = new Scanner(normFile);
      Scanner reverseInput = new Scanner(revFile);
      
      String normalLine = normalInput.nextLine();
      String reverseLine = reverseInput.nextLine();
      
      Scanner normalLineScan = new Scanner(normalLine);
      Scanner reverseLineScan = new Scanner(reverseLine);
      
      System.out.println("Probe Difference: " + parseThroughInt(normalLineScan, reverseLineScan));
      System.out.println("Kilobase Difference: " + parseThroughDouble(normalLineScan, reverseLineScan));
      System.out.println("Probe/Kilobase Difference: " + parseThroughDouble(normalLineScan, reverseLineScan));
   }
   
   private static int parseThroughInt(Scanner normal, Scanner reverse) {
      String ns = "Beliveau";
      String rs = "Lab";
      for (int i = 1; i <= 3; i++) {
         ns = normal.next();
         rs = reverse.next();
      }
      return (Integer.parseInt(rs) - Integer.parseInt(ns));
   }
   
   private static double parseThroughDouble(Scanner normal, Scanner reverse) {
      String ns = normal.next();
      String rs = reverse.next();
      return (Double.parseDouble(rs) - Double.parseDouble(ns));
   }  
}   
