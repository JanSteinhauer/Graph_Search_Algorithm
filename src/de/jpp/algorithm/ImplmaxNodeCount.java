package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.SearchStopStrategy;

public class ImplmaxNodeCount implements SearchStopStrategy {
    int maxCount;
    int Anfang = 0;

    public ImplmaxNodeCount(int maxCount){
        this.maxCount = maxCount;
    }

    public  void AlgorithmusWeitermachen(){
        for (int i = 0; i < maxCount; i++) {
            //mach algo weiter
        }

    }


    @Override
    public boolean stopSearch(Object lastClosedNode) {
        if(maxCount == Anfang){
            return true;
        }else {
            Anfang += 1;
            return false;
        }
    }
}
