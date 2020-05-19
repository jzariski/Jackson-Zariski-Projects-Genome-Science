import java.io.*;
import java.awt.*;
import java.util.*;

public class AnalyzeFile {

    private String file;
    private boolean bed;
    private boolean fastq;


    public AnalyzeFile(String inputFile, String type) {
        this.file = inputFile;
        
        File testFile = new File(this.file);
        
        if (!testFile.exists()) {
         throw new IllegalArgumentException("File does not exist");
        }

        if (type.equals("bed")) {
            this.bed = true;
            this.fastq = false;
        } else if (type.equals("fastq")) {
            this.fastq = true;
            this.bed = false;
        } else {
            throw new IllegalArgumentException("Input a bed or fastq file");
        }

    }

    public void checkGPercent(double percent) throws FileNotFoundException {
        System.out.println();
        if (this.bed) {
            this.checkGPercentBed(percent);
        } else if (this.fastq) {
            this.checkGPercentFastq(percent);
        }
    }
    
    public void getInfo() throws FileNotFoundException {
      System.out.println();
      if (this.bed) {
        
         this.getInfoBed();
      } else if (this.fastq) {
          
          this.getInfoFastq();
      }
    }

    public void checkPlusMinus() throws FileNotFoundException {
        System.out.println();
        if (this.bed) {
            this.checkPlusMinusBed();
        } else if (this.fastq) {
            this.checkPlusMinusFastq();
        }
    }

    private void checkGPercentBed(double percent) throws FileNotFoundException {
        File currFile = new File(this.file);
        Scanner fileScan = new Scanner(currFile);
        int numRight = 0;
        int numWrong = 0;
        

        while (fileScan.hasNextLine()) {
         
            String currLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(currLine);
            String seq = lineScan.next();

      
            
            seq = lineScan.next();

            
            while (seq.charAt(0) != 'G' &&    
                   seq.charAt(0) != 'A' &&
                   seq.charAt(0) != 'T' &&
                   seq.charAt(0) != 'C') {
                   
                seq = lineScan.next();
            }    
                     
                           
            int currLength = seq.length();
            double gNum = 0.0;
            for (int i = 0; i < currLength; i++) {
                if (seq.charAt(i) == 'G') {
                    gNum = gNum + 1.0;
                }
            }
            
            double length = currLength * 1.0;

            double compPercent = gNum / length;
            if (compPercent <= percent / 100) {
                numRight++;
            } else {
                numWrong++;
            }
        }

        System.out.println("Number passing G Pecent Check: " + numRight);
        System.out.println("Number failing G Percent Check: " + numWrong);  
        
    }

    private void checkGPercentFastq(double percent) throws FileNotFoundException {
        File currFile = new File(this.file);
        Scanner fileScan = new Scanner(currFile);
        int numRight = 0;
        int numWrong = 0;

        while (fileScan.hasNextLine()) {
         
            String seq = fileScan.nextLine();

            if (seq.charAt(0) == 'G' ||    
                seq.charAt(0) == 'A' ||
                seq.charAt(0) == 'T' ||
                seq.charAt(0) == 'C') {
                     
                           
                int currLength = seq.length();
                double gNum = 0.0;

                for (int i = 0; i < currLength; i++) {
                    if (seq.charAt(i) == 'G') {
                        gNum = gNum + 1.0;
                    }
                }
                
                double length = currLength * 1.0;
            
                double compPercent = gNum / length;
                if (compPercent <= percent / 100) {
                    numRight++;
                } else {
                    numWrong++;
                }
            }
        }

        System.out.println("Number passing: " + numRight);
        System.out.println("Number failing: " + numWrong);      
    }
    
    private void getInfoBed() throws FileNotFoundException {
         File currFile = new File(this.file);
         Scanner fileScan = new Scanner(currFile);

         Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
         Map<Integer, Double> countTempMap = new HashMap<Integer, Double>();
    
         double tempSum = 0;
         int totalSeq = 0;

         
         while (fileScan.hasNextLine()) {
         
            String currLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(currLine);
            String seq = lineScan.next();
            totalSeq = totalSeq + 1;
            
            while (seq.charAt(0) != 'G' &&    
                   seq.charAt(0) != 'A' &&
                   seq.charAt(0) != 'T' &&
                   seq.charAt(0) != 'C') {
                   
               seq = lineScan.next();
            }    
                     
                           
            int currLength = seq.length();            
            if (!countMap.containsKey(currLength)) {
               countMap.put(currLength, 1);
            } else {
               countMap.put(currLength, countMap.get(currLength) + 1);
            }
            
            double temp = lineScan.nextDouble();
            tempSum = tempSum + temp;
            
            if (!countTempMap.containsKey(currLength)) {
               countTempMap.put(currLength, temp);
            } else {
               countTempMap.put(currLength, countTempMap.get(currLength) + temp);
            }
         }
         
         System.out.println();
         double avgTemp = tempSum / totalSeq;
         System.out.println("Count of sequences by length");
         System.out.println();
         System.out.println(countMap);
         System.out.println();
         System.out.println("Count of Sequences by avg melting temp");
         System.out.println();
         Map<Integer, Double> finalMap = new TreeMap<Integer, Double>();
         for (int length : countMap.keySet()) {
            double atemp = countTempMap.get(length) / countMap.get(length);
            finalMap.put(length, atemp);
         }
         System.out.println(finalMap);
         System.out.println();
         System.out.println("Total Average Melting Temp: " + avgTemp);
         System.out.println();
    }

    private void getInfoFastq() throws FileNotFoundException {
        File currFile = new File(this.file);
        Scanner fileScan = new Scanner(currFile);
        HashMap<Integer, Integer> finalMap = new HashMap<Integer, Integer>();

        while (fileScan.hasNextLine()) {
         
            String seq = fileScan.nextLine();

            if (seq.charAt(0) == 'G' ||    
                seq.charAt(0) == 'A' ||
                seq.charAt(0) == 'T' ||
                seq.charAt(0) == 'C') {
                     
                           
                int currLength = seq.length();
                if (!finalMap.keySet().contains(currLength)) {
                    finalMap.put(currLength, 1);
                } else {
                    finalMap.put(currLength, finalMap.get(currLength) + 1);
                }
            }
        }
        System.out.println("Count of sequences by length");
        System.out.println();
        System.out.println(finalMap);

    }

    private void checkPlusMinusBed() throws FileNotFoundException {
        File currFile = new File(this.file);
        Scanner fileScan = new Scanner(currFile);
        int numRight = 0;
        int numWrong = 0;
        int numPlus = 0;
        int numMinus = 0;

        while (fileScan.hasNextLine()) {
         
            String currLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(currLine);
            String seq = lineScan.next();

            while (!seq.equals("+") && !seq.equals("-")) {
                seq = lineScan.next();
            }

            if (seq.equals("+")) {
                numPlus++;
            } else if (seq.equals("-")) {
                numMinus++;
            }

            seq = lineScan.next();

            
            while (seq.charAt(0) != 'G' &&    
                   seq.charAt(0) != 'A' &&
                   seq.charAt(0) != 'T' &&
                   seq.charAt(0) != 'C') {
                   
                seq = lineScan.next();
            }    
                     
        }

        System.out.println("Sequences on Forward Strand: " + numPlus);
        System.out.println("Sequences on Reverse Strand: " + numMinus);  
        
    }
    
    private void checkPlusMinusFastq() throws FileNotFoundException {
        File currFile = new File(this.file);
        Scanner fileScan = new Scanner(currFile);
        int numPlus = 0;
        int numMinus = 0;

        while (fileScan.hasNextLine()) {
         
            String seq = fileScan.nextLine();

            if (seq.charAt(0) == '@') {
                     
                           
                int seqLength = seq.length();
                String plusMinus = Character.toString(seq.charAt(seqLength - 1));
                
                if (plusMinus.equals("+")) {
                  numPlus++;
                } else if (plusMinus.equals("-")) {
                  numMinus++;
                }
             }        
         }
        System.out.println("Sequences on Forward Strand: " + numPlus);
        System.out.println("Sequences on Reverse Strand: " + numMinus);
      }
}