package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.SearchStopStrategy;

public class ImplexpandAllNodes implements SearchStopStrategy {
    SearchStopStrategy stopSearch = (Object lastClosedNode) -> {return false;};
    @Override
    public boolean stopSearch(Object lastClosedNode) { //todo Diese Ausprägung soll den Algorithmus nie stoppen.
        return false;                                   // ergo stopSearch soll immer false returnen
    }
}
