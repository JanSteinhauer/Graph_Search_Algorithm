package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.SearchStopStrategy;
import org.w3c.dom.Node;


public class StartToDestStrategy<N> implements SearchStopStrategy<N> {
    private N dest;

    @Override
    public boolean stopSearch(N lastClosedNode) {
        if(lastClosedNode.equals(dest)){
            return true;
        }else{
            return false;
        }
    }

    public void setDest2(N dest) {
        this.dest = dest;
    }

    /**
     * Returns the destination node of this search
     *
     * @Returns the destination node of this search
     */
    public N getDest() {
        return (N) dest;
    }
    public void setDest(N dest){
        this.dest =  dest;
    }
}
