import java.util.*;
import java.io.*;

public class verifyReverse {

   public static void main(String[] args) throws FileNotFoundException {
      
      Scanner console = new Scanner(System.in);
      System.out.print("File name: ");
      String fileName = console.nextLine();
      
      File file = new File(fileName);
      Scanner input = new Scanner(file);
      int totalPass = 0;
      int totalFail = 0;
      
      while(input.hasNextLine()) {
         String seq = input.nextLine();
         if (seq.charAt(0) == 'A' || seq.charAt(0) == 'T' || seq.charAt(0) == 'C' || seq.charAt(0) == 'G') {
            int gCount = 0;
            int cCount = 0;
            for (int i = 0; i <= seq.length() - 1; i++) {
               if (seq.charAt(i) == 'G') {
                  gCount++;
               }
               if (seq.charAt(i) == 'C') {
                  cCount++;
               }
            }
            double gPercent = (double) gCount / (double) seq.length();
            double cPercent = (double) cCount / (double) seq.length();
            if (gPercent <= 0.25) {
               System.out.println("Forward pass:" + gPercent);
               totalPass++;
            } else if (cPercent <= 0.25) {
               System.out.println("Reverse pass:" + cPercent);
               totalPass++;
            } else {
               totalFail++;
            }
         }
      }
      System.out.println("Total Pass: " + totalPass);
      System.out.println("Total Fail: " + totalFail);
  }
}
         
            
   
      