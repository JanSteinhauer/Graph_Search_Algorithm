package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.NodeStatus;
import de.jpp.model.interfaces.Edge;
import org.w3c.dom.Node;

import java.util.Objects;
import java.util.Optional;

public class NodeInforamtion<N,A> {
    private Edge<N,A> predecessor;
    private double distance;
    private N startNode;
    private N destNode;
    private NodeStatus nodeStatus;

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }


    public N getStartNode() {
        return startNode;
    }

    public void setStartNode(N startNode) {
        this.startNode = startNode;
    }

    public N getDestNode() {
        return destNode;
    }

    public void setDestNode(N destNode) {
        this.destNode = destNode;
    }

    public NodeInforamtion(Edge predecessor, double distance){
        this.predecessor = predecessor;
        this.startNode = (N) predecessor.getStart();
        this.destNode = (N) predecessor.getDestination();
        this.distance = distance;

    }

    public NodeInforamtion(){


    }


//    Die Klasse NodeInformation ist eine reine Datenklasse. Sie soll in SearchResult einem Knoten zugeordnet werden und für diesen immer den aktuellen Vorgänger auf einem kürzesten Weg vom Startknoten und das Gewicht des gesamten, kürzesten Weges zu ihm. Beispiel: Der kürzeste Weg ist A -> B -> C -> D wobei jede Kante das Gewicht 3 hat. Dann enthält das zu D passende NodeInformation-Objekt die Kante C -> D als Vorgängerkante und 9 als Gewicht.
//    Implementieren Sie zusätzlich zum Klassendiagramm entsprechende Getter und Setter.
    //todo ich sehe es so das es rekursiv implementiert werden muss um die distance herauszubekommen

    //todo frage wie check ich, dass ob ich die beiden Vars selber ermitteln muss oder ob diese gegeben werden

//    public void rekursiveDistance(Edge edge, double distance ){
//        distance = (double) edge.getAnnotation().get(); //todo es wird angenommen, dass die Dist in annotation gespeichert wird
//        if(Objects.isNull(edge.getDestination().))
//
//    }

    /*public double distanceBerechnen(Edge edge,double vorherig){
        try {==
            vorherig = (double) predecessor.getAnnotation().get();
        }catch (Exception e){
            return vorherig;
        }
    }*/

    public void setPredecessor(Edge predecessor) {
        this.predecessor = predecessor;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Edge getPredecessor() {

        return predecessor;
    }

    public Optional<Edge> getPredecessorOptional() {
        if(Objects.isNull(predecessor)){
            return Optional.empty();
        }else {
            return Optional.of(predecessor);
        }
    }

    public double getDistance() {
        /*if(Objects.isNull(startNode) || Objects.isNull(destNode)){
            throw new IllegalArgumentException("NodeInformation getDistance startNode oder destNOde ist null");
        }*/
        return distance;
    }





}
