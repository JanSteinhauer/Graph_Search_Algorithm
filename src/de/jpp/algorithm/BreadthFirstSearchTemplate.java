package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.*;
import de.jpp.factory.SearchStopFactory;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.w3c.dom.Node;

import java.util.*;

public abstract class BreadthFirstSearchTemplate<N,A, G extends Graph<N,A>> implements SearchAlgorithm {
    private boolean stopped;
    private Graph graph;
    private N start;
    private SearchResultImpl searchResult = new SearchResultImpl();


    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setStart(N start) {
        this.start = start;
    }

    public SearchResultImpl getResult() {
        return searchResult;
    }

    public void setResult(SearchResultImpl result) {
        this.searchResult = result;
    }

    abstract double getDistance(N node, SearchResultImpl searchResult, NodeInforamtion nodeInforamtion);



    protected  NodeInforamtion getNodeInformation(Edge edge, double gewicht){
        return new NodeInforamtion(edge,gewicht); //1.Konstruktor wird ausgelöst und und gesettet
    }

    protected void openIfShorter(N node, NodeInforamtion nodeInforamtion){
        NodeInforamtion info = searchResult.getInformation(node);

        double infoDistance =  nodeInforamtion.getDistance();
        double aktuelleDistance =info.getDistance();

        if(aktuelleDistance>infoDistance){
            searchResult.open(node, nodeInforamtion);
        }


    }

    public void initialize(Graph g, N n){ //Initialize(GraphG, Vertexs)
        List<N> allnodes = (List<N>) g.getNodes();
        for (int i = 0; i < allnodes.size(); i++) {//foreachu∈Vdo
            searchResult.statusMap.put(allnodes.get(i), NodeStatus.UNKOWN); //u.color=white
            NodeInforamtion nodeInforamtion1 = new NodeInforamtion();
            nodeInforamtion1.setDistance(Integer.MAX_VALUE);
            searchResult.infoMap.put(allnodes.get(i), nodeInforamtion1);
        }//todo bin mir nicht sicher ob man diese Zeile weglassen kann
        searchResult.setOpen(n);//s.color=gray
        searchResult.getInformation(n).setDistance(0);
    }

    /**
     * Starts the search process. Stops the search with the specified strategy
     *
     * @param type
     * @return
     */
    @Override
    public SearchResult findPaths(SearchStopStrategy type) {


        initialize(graph, start);
        /*searchResult.open(start, nodeinfo);*/

        LinkedList<N> linkedListAll = new LinkedList<>();
        LinkedList<N> linkedListOpen = new LinkedList<>();
        LinkedList<N> linkedListClose = new LinkedList<>();

        linkedListAll.addFirst(start);

        while (!linkedListAll.isEmpty()){
            N currentNode = linkedListAll.getFirst();
            linkedListAll.remove(currentNode);
            /*if(type.stopSearch(currentNode)){//aktueller Knoten // hier das ! weg
                stop();
                return searchResult;
            }*/
            if(isStopped()){  //5 mal random in Methode verteilt
                return searchResult;
            }


            NodeInforamtion currentInfo = new NodeInforamtion();
            if(currentNode == start){
                System.out.println("Ich bin am Anfang");
            }
            try {
                if(getSearchResult().getPredecessor(currentNode).isPresent()){
                    currentInfo = new NodeInforamtion();
                    currentInfo.setPredecessor((Edge) searchResult.getPredecessor(currentNode).get());
                }
            }catch (Exception e){

            }



            Collection<Edge<N,A>> nachbarn = graph.getNeighbours(currentNode);
            for(Edge<N,A> ed : nachbarn){

                //todo hier muss wahrscheinlich noch eine Schleife Abfangen

                NodeInforamtion nodeInforamtionUnKnow = new NodeInforamtion();
                nodeInforamtionUnKnow.setPredecessor(ed);
                //searchResult.infoMap.put(ed.getDestination(), nodeInforamtionUnKnow);
                //todo hier beim Gewicht angreifen

                nodeInforamtionUnKnow= getNodeInformation(ed, getDistance(searchResult,ed.getDestination(),ed)); //

                if(searchResult.statusMap.get(ed.getDestination())==NodeStatus.UNKOWN){
                    searchResult.open(ed.getDestination(), nodeInforamtionUnKnow);
                }else {
                    openIfShorter(ed.getDestination(),nodeInforamtionUnKnow);
                }

                //searchResult.open(ed.getDestination(), nodeInforamtionUnKnow);
                if(searchResult.statusMap.get(ed.getDestination())==NodeStatus.OPEN && !linkedListAll.contains(ed.getDestination())){{
                    linkedListAll.addLast(ed.getDestination());
                }}

            }
            searchResult.setClosed(currentNode); //u.color=black
            if(type.stopSearch(currentNode)){//aktueller Knoten // hier das ! weg
                stop();
                return searchResult;
            }
            if(isStopped()){  //5 mal random in Methode verteilt
                return searchResult;
            }


            linkedListAll.sort(new Comparator<N>() {
                @Override
                public int compare(N o1, N o2) {
                    double eins = searchResult.getInformation(o1).getDistance();
                    double zwei = searchResult.getInformation(o2).getDistance();
                    return Double.compare(eins,zwei);
                }
            });


        }
        return searchResult;
    }

    /**
     * Starts the search process with a non-stopping search strategy
     *
     * @return the result of the search algorithm
     */
    @Override
    public SearchResult findAllPaths() {
        SearchStopFactory searchStopFactory = new SearchStopFactory();
        return findPaths(searchStopFactory.expandAllNodes());
    }

    abstract double getDistance(SearchResultImpl searchResult, N anfangsnode, Edge<N,A> edge);

    /**
     * Returns the observable search result so listener can be added before executing the search
     *
     * @return the observable search result
     */
    @Override
    public ObservableSearchResult getSearchResult() {
        return searchResult;
    }

    /**
     * Returns the start node of this search
     *
     * @return the start node of this search
     */
    @Override
    public Object getStart() {
        return start;
    }

    /**
     * Returns the graph on which to search
     *
     * @return the graph on which to search
     */
    @Override
    public Graph getGraph() {
        return graph;
    }

    /**
     * This method stops the search. No further nodes will be added to the open or closed list, the searchResult won´t change anymore and no listener must be called after this method
     */
    @Override
    public void stop() {
        stopped = true;
        List<N> nodelist = (List<N>) searchResult.getAllKnownNodes();
        for (int i = 0; i < nodelist.size(); i++) {
            NodeStatus currentNodestatus = searchResult.getNodeStatus(nodelist.get(i));
            if(currentNodestatus == NodeStatus.OPEN){
                searchResult.statusMap.put(nodelist.get(i),NodeStatus.UNKOWN);
            }
        }

    }


}

