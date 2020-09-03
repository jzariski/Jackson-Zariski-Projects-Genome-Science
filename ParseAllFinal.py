import pandas as pd
class ParseAllFinal:
    
    def __init__(self):
        pass

    ## Takes large list of probes as input
    ## Shortens it to range of start-stop
    def shortenList(self, start, stop, file):
        
        bigList = open(file, "r")

        finalList = []

        for line in bigList:
            
            tempList = line.split()

            tempStart = int(tempList[0])
            tempStop = int(tempList[1])

            
            if tempStart >= start and tempStop <= stop:
                finalList.append(line)

        finalFinalList = []
        for line in finalList:
            newList = line.split()
            finalFinalList.append(newList)
        
        bigList.close()
        return finalFinalList
    
    ## Shortens input list in range int1-int2
    ## Returns larger shortened list
    def createBigList(self, int1, int2, text, fileName):
        biggerList =[]
        if text:
            print("Starting list shorten\n")
        shortenedList = self.shortenList(int1, int2, fileName)
        for probe in shortenedList:
            biggerList.append(probe)
        if text:
            print ("Done shortening lists")
        return biggerList

    ## Parses through list of probes and optimizes it for
    ## range int1-int2
    ##
    ## If text is true, updates on progress are prtinted to termninal
    ## Returns optimized list of probes based on g-percent with no overlap
    def parseThrough(self, finalProbeSet, int1, int2, text):

        coordinateSet = set()
        bestProbes = []
        totalProbes = len(finalProbeSet)
        probesParsed = 0

        for probe in finalProbeSet:
            start = int(probe[0])
            end = int(probe[1])
            noOverlap = True
            currNum = start

            while (currNum <= end) and noOverlap:
                if currNum in coordinateSet:
                    noOverlap = False
                else:
                    coordinateSet.add(currNum)
                    currNum = currNum + 1
            
            if noOverlap:
                bestProbes.append(probe)
            
            probesParsed = probesParsed + 1
            
            if probesParsed % 1000 == 0:
                if text:
                    print(str(probesParsed) + " out of " + str(len(finalProbeSet)) + " parsed")
        
        if text:
            print("Done parsing")
        percentCoverage = ((len(coordinateSet) * 1.0) / (int2 - int1)) * 100
        if text:
            print("Final Percent Coverage: " + str(percentCoverage))
        kb = ((int2 - int1) * 1.0) / 1000
        probesKB = len(bestProbes) / kb
        if text:
            print("Probes per kilobase: " + str(probesKB))
        return bestProbes





