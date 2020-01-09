import java.util.*;
import java.io.*;
import java.awt.*;


public class TestStuff {
   public static void main(String[] args) throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      System.out.print("Normal File: ");
      File normalFile = new File(console.nextLine());
      System.out.print("ReverseFile: ");
      File reverseFile = new File(console.nextLine());

      CompareMeta testMeta = new CompareMeta(normalFile, reverseFile);
      testMeta.parseAndPrint();
   }
}