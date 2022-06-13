package de.jpp.factory;

import de.jpp.algorithm.ImplexpandAllNodes;
import de.jpp.algorithm.ImplmaxNodeCount;
import de.jpp.algorithm.StartToDestStrategy;
import de.jpp.algorithm.interfaces.SearchStopStrategy;

public class SearchStopFactory {

    /**
     * Returns a SearchStopStrategy which never stops on its own
     *
     * @param <N> the exact types of nodes in the graph underlying the search
     * @return a SearchStopStrategy which never stops on its own
     */
    public <N> SearchStopStrategy<N> expandAllNodes() {
         SearchStopStrategy<N> stopSearch = (N lastClosedNode) -> {return false;};
        return stopSearch;
    }

    /**
     * Returns a SearchStopStrategy which stops after a specified number of nodes are CLOSED
     *
     * @param maxCount the maximum number of nodes which may be CLOSED
     * @param <N>      the exact type of nodes in the graph underlying the search
     * @return a SearchStopStrategy which stops after a specified number of nodes are CLOSED
     */

    public <N> SearchStopStrategy<N> maxNodeCount(int maxCount) {
        return new SearchStopStrategy() { //anonyme klasse
            int aktuellePos =1;//todo hier mit Zahlen rumprobieren bringt es nicht
            @Override
            public boolean stopSearch(Object lastClosedNode) {
                if(maxCount == aktuellePos){
                    return true;
                }else {
                    aktuellePos += 1;
                    return false;
                }
            }
        };//distance berechnung anders bei jeden such algo
    }

    /**
     * Returns a SearchStopStrategy which stops after a specified destination has been reached
     *
     * @param dest the destination
     * @param <N>  the exact type of nodes in the graph underlying the search
     * @return a SearchStopStrategy which stops after a specified destination has been reached
     */
    public <N> StartToDestStrategy<N> startToDest(N dest) {
       /* throw new UnsupportedOperationException("not supported yet!");*/
        StartToDestStrategy startToDestStrategy = new StartToDestStrategy();
        startToDestStrategy.setDest2(dest);
        return startToDestStrategy;
    }

}
