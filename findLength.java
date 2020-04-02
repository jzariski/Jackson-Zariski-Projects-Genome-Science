import java.io.*;
import java.awt.*;
import java.util.*;

public class findLength {
   public static void main(String[] args) throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      int ans = 0;
      int max = 0;
      
      while (ans == 0) {
         
         System.out.println();
         System.out.print("File name: ");
         File currFile = new File(console.next());
         Scanner fileScan = new Scanner(currFile);
         Map<Integer, Integer> currMap = new TreeMap<Integer, Integer>();

         
         while (fileScan.hasNextLine()) {
         
            String currLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(currLine);
                     
            if (currLine.charAt(0) == 'G' ||    
                currLine.charAt(0) == 'A' ||
                currLine.charAt(0) == 'T' ||
                currLine.charAt(0) == 'C') {
               
               int currLength = currLine.length();
               
               if (currMap.keySet().contains(currLength)) {
                  currMap.put(currLength, currMap.get(currLength) + 1);
               } else {
                  currMap.put(currLength, 1);
               }
            }
         }
         
         Set<Integer> finalSet = new HashSet<Integer>();
         
         for (int length : currMap.keySet()) {
            if (currMap.get(length) == max) {
               finalSet.add(length);
            }
            if (currMap.get(length) > max) {
               finalSet.clear();
               finalSet.add(length);
               max = currMap.get(length);
            }
         }
         System.out.print("Sequence length of mode: ");
         System.out.println(finalSet);
         max = 0;
         
         System.out.println();
      }
   }
}
               
            
            
                   
   
   