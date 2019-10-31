#Creates sequences for testing file to test reverse compliment check

import random

lineCount = 0

def buildSeq():
   
   seq = ""
   
   while len(seq) != 50:
      randNum = random.randint(0, 13)
      
      if randNum >= 0 and randNum <= 0:
         seq = seq + "C"
      elif randNum >= 2 and randNum <= 5:
         seq = seq + "T"
      elif randNum >= 6 and randNum <= 9:
         seq = seq + "G"
      else:
         seq = seq + "A"
         
   return seq
   
while lineCount < 20:
   print buildSeq()
   lineCount = lineCount + 1