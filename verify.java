import java.util.*;
import java.io.*;

public class verify {

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
            for (int i = 0; i <= seq.length() - 1; i++) {
               if (seq.charAt(i) == 'G') {
                  gCount++;
               }
            }
            double percent = (double) gCount / (double) seq.length();
            System.out.println(percent);
            if (percent <= 0.25) {
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
         
            
   
      