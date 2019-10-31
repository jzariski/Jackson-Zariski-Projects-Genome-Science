def reverseGCheck(seqR):
   reverseG = 0
   for x in seqR:
      if x in 'Gg':
         reverseG = reverseG + 1
   
   return float(self.gPercent) <= reverseG * 100.0 / len(seqR) \
                                    <= float(self.GPercent)
                                    

seq1 = 'AAAAAAAAAGAAAAAAAAA'
print reverseGCheck(seq1)