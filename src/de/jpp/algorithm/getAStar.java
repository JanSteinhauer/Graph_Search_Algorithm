package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.*;
import de.jpp.factory.SearchFactory;
import de.jpp.factory.SearchStopFactory;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.WeightedGraph;
import org.w3c.dom.Node;

import java.util.*;

public class getAStar<G extends WeightedGraph<N,A>,N, A> extends BreadthFirstSearchTemplate<N,A,G> implements  EstimationFunction<N> { //todo  //priority queue open if shorter //if dist checken //wenig unterschied zu breitensuche und vgl //sobland knoten entdeckt abschließen
    //alle offen /Unknown knoten anschauen open if shorter
    //breitensuche -> nur unknown knoten anschauen
    SearchResultImpl searchResult;
    G graph;
    N start;
    N dest;
    EstimationFunction<N> estimationFunction;
    double zwischenkosten;

    public getAStar(G graph, N start, N dest, EstimationFunction<N> estimationFunction) {
        super();
        this.graph = graph;
        this.start = start;
        this.dest = dest;
        this.estimationFunction = estimationFunction;

        setGraph(graph);
        setStart(start);

        searchResult = new SearchResultImpl();
    }

    //

    public static void main(String[] args) {

        //getAStar dijkstraSearch = new getAStar(twoDimGraph, eins);
        /*SearchStopFactory searchStopFactory = new SearchStopFactory();
        dijkstraSearch.findPaths(searchStopFactory.expandAllNodes());*/
        System.out.println("Lol");
    }



    /*@Override //Nachbarn in Map speichern -> Map mit allen Nachbarn durchgehen -> schauen ob mindist kleiner ist -> am ende von jeder Iteration predecessors setzen

    double getDistance(N node, SearchResultImpl searchResult, NodeInforamtion nodeInforamtion) {//todo node und nodeinformation sind fürs ende

    }*/
    public  double getEstimatedDistance(N node, N dest){

        return  estimationFunction.getEstimatedDistance(node,dest);

    }



    @Override
    double getDistance(Object node, SearchResultImpl searchResult, NodeInforamtion nodeInforamtion) {
        /*N start = (N) getStart();
        NodeInforamtion nodeInforamtionStart = searchResult.getInformation(start);
        nodeInforamtionStart.setDistance(0); // anfangs node auf null setzen, alle anderen auf maxvalue
        Map<Node, NodeInforamtion> infoMap = new HashMap<>();
        infoMap.putAll(searchResult.infoMap);
        for(Map.Entry<Node, NodeInforamtion> entry : infoMap.entrySet()){
            NodeInforamtion nodeInforamtion1 = searchResult.getInformation(entry.getKey());
            nodeInforamtion1.setDistance(Integer.MAX_VALUE);
        }



        PriorityQueue<NodeInforamtion> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(nodeInforamtionStart);

        while (!Objects.isNull(nodeInforamtionStart.getPredecessor())){
            NodeInforamtion nodeInforamtion1 = priorityQueue.poll();
            double weight = nodeInforamtion1.getDistance(); //distanz die du schon hattes + knoten dist //speichern nodeInformationmap
            //todo  https://gist.github.com/artlovan/a07f29e16ab725f8077157de7abdf125
            //todo https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/others/Dijkstra.java
            // gibt es so eine art min distance


        }*/
        return 0;
    }

    @Override
    double getDistance(SearchResultImpl searchResult, Object anfangsnode, Edge edge) {
        N node = (N) anfangsnode;
        if(searchResult.getInformation(node).getPredecessor() == null){
            /*Edge<N,A> ed = (Edge<N, A>) searchResult.getPredecessor(anfangsnode).get();*/
            return  getEstimatedDistance(node, dest);
        }else{
            double kostenZwischen=1;
            /*Edge<N,A> ed = (Edge<N, A>) searchResult.getPredecessor(anfangsnode).get();*/
            double estDist = getEstimatedDistance(node,dest);
            N PreStart = (N) dest;
            if(searchResult.getPathTo(node).isPresent()){
                List<Edge> edgeListSize = (List<Edge>) searchResult.getPathTo(node).get();
                kostenZwischen = edgeListSize.size() +1;
            }
            return kostenZwischen +estDist;
        }


    }


















    /*@Override
    public SearchResult findPaths(SearchStopStrategy type) {
        N start = (N) getStart();
        Graph graph = getGraph();
        NodeInforamtion nodeInforamtion = searchResult.getInformation(start);




        //todo vorher war hier eine priority queue
        LinkedList<N> nodequeue = new LinkedList<>();//Q=newPriorityQueue(V,d)
        nodequeue.add(start);//Q.Enqueue(s)
        while (!Objects.isNull(nodequeue)){ //while notQ.Empty()do
            N currentNode = nodequeue.poll(); //u=Q.Dequeue()
            NodeInforamtion nodeInforamtion1 = (NodeInforamtion) searchResult.infoMap.get(currentNode);
            double currentDistanceMin = nodeInforamtion1.getDistance(); //u=Q.ExtractMin()
            if(!type.stopSearch(currentNode)){//aktueller Knoten
                stop();
                return searchResult;
            }
            if(isStopped()){
                return searchResult;
            }

            Map<N,Edge<N,A>> nachbarn = getNachbarn(start); //  foreachv∈Adj[u]do
            for(Map.Entry<N,Edge<N,A>> entry : nachbarn.entrySet()){ //foreachv∈Adj[u]do
                if(searchResult.getNodeStatus(currentNode) == NodeStatus.UNKOWN){ //ifv.color==whitethen
                    if(Relax(currentDistanceMin, currentNode, entry.getKey(), entry.getValue() )){
                        nodequeue.add(entry.getKey()); //Q.DecreaseKey(v,v.d)
                    }
                }
            }
            searchResult.setClosed(currentNode); //u.color=black

        }*/



    /*    Node start = (Node) getStart();
        NodeInforamtion nodeInforamtionStart = searchResult.getInformation(start);
        nodeInforamtionStart.setDistance(0); // anfangs node auf null setzen, alle anderen auf maxvalue
        Map<Node, NodeInforamtion> infoMap = new HashMap<>();
        infoMap.putAll(searchResult.infoMap);
        for(Map.Entry<Node, NodeInforamtion> entry : infoMap.entrySet()){
            NodeInforamtion nodeInforamtion1 = searchResult.getInformation(entry.getKey());
            nodeInforamtion1.setDistance(Integer.MAX_VALUE);
        }


        Map<N, Edge<N,A>> Nachbarn = new HashMap<>(); //todo hier werden die Nachbarn gesucht
        List<Edge<N,A>> getAllEdges = (List<Edge<N, A>>) graph.getEdges();
        List<N> getAllNodes = (List<N>) searchResult.getAllKnownNodes();
        for (int i = 0; i < getAllNodes.size(); i++) {
            for (int j = 0; j < getAllEdges.size(); j++) {
                if(getAllEdges.get(j).getDestination().equals(getAllNodes.get(i))){
                    Nachbarn.put(getAllNodes.get(i),getAllEdges.get(j));
                }
            }
        }

        for(Map.Entry<N,Edge<N,A>> entry : Nachbarn.entrySet()){
            Edge tempoEdge = entry.getValue();
            double dist = (double) tempoEdge.getAnnotation().get();// darin ist die distance gespeichert
            // double weight = entry.getValue().distance
        }
        //am ende von jeder Iteration predecessors stetzen
*/
/*        return super.findPaths(type);
    }*/

    public  boolean Relax(double u, N currentNode, N neighbourNode, Edge<N,A> edge){
        NodeInforamtion nodeInforamtion1 = (NodeInforamtion) searchResult.infoMap.get(currentNode);
        double currentDistanceMin = nodeInforamtion1.getDistance(); //u=Q.ExtractMin()
        NodeInforamtion nodeInforamtion2 = (NodeInforamtion) searchResult.infoMap.get(neighbourNode);
        double currentDistanceMin2 = nodeInforamtion1.getDistance(); //u=Q.ExtractMin()
        //todo ich glaube (u,v) ist folgendes
        if(currentDistanceMin > currentDistanceMin2 + (double) edge.getAnnotation().get()){
            searchResult.setOpen(neighbourNode);
            currentDistanceMin = currentDistanceMin2 + (double) edge.getAnnotation().get();
            //todo was heißt v.π=u
            return true;

        }
        return false;
    }



    public Map<N, Edge<N,A>> getNachbarn(N node){
        Map<N, Edge<N,A>> Nachbarn = new HashMap<>(); //todo hier werden die Nachbarn gesucht
        List<Edge<N,A>> getAllEdges = (List<Edge<N, A>>) graph.getEdges();
        List<N> getAllNodes = (List<N>) getSearchResult().getAllKnownNodes();
        for (int i = 0; i < getAllNodes.size(); i++) {
            for (int j = 0; j < getAllEdges.size(); j++) {
                if(getAllEdges.get(j).getDestination().equals(getAllNodes.get(i))){
                    Nachbarn.put(getAllNodes.get(i),getAllEdges.get(j));
                }
            }
        }
        return Nachbarn;
    }
}
//Nachbarn in Map speichern -> Map mit allen Nachbarn durchgehen -> schauen ob mindist kleiner ist -> am ende von jeder Iteration predecessors setzen