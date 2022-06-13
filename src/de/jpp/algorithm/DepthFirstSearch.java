package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.*;
import de.jpp.factory.SearchStopFactory;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.w3c.dom.Node;

import java.util.*;

public class DepthFirstSearch<N,A> implements SearchAlgorithm {//node mit N ersetzen
    // bei Dijkstra mit N arbeiten
    boolean stopped; //== druch equals ersetzen
    private Graph graph;
    private N start;

    private SearchResultImpl searchResult = new SearchResultImpl();

    public HashMap<N,Edge<N,A>> hashMap = new HashMap<>();

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
        this.start = (N) start;
    }

    public SearchResultImpl getResult() {
        return searchResult;
    }

    public void setResult(SearchResultImpl result) {
        this.searchResult = result;
    }

    public static void main(String[] args) {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        /*XYNode eins = new XYNode("eins", 1,1);
        XYNode zwei = new XYNode("zwei",2,2);
         XYNode drei = new XYNode("drei",3,3);
        XYNode vier = new XYNode("vier",4,4);
        XYNode funf = new XYNode("funf",5,5);
        twoDimGraph.addNodes(eins,zwei ,drei ,vier );
        twoDimGraph.addEdge(eins, zwei);
        twoDimGraph.addEdge(eins, drei);
        twoDimGraph.addEdge(zwei, vier);*/
        XYNode cero = new XYNode("cero",0,0);
        XYNode uno = new XYNode("uno",1,1);
        XYNode dos = new XYNode("dos",2,1);
        XYNode tres = new XYNode("tres",3,0);
        XYNode dosNegativo = new XYNode("dosNegat", 2,-1);
        XYNode cuatro = new XYNode("cuatro",4,0);
        twoDimGraph.addNodes(cero, uno, dos);
        twoDimGraph.addEdge(cero, uno);
        twoDimGraph.addEdge(uno,dos);
      //leeres optional für alle getPredecessors machen
        DepthFirstSearch breadthFirstSearch = new DepthFirstSearch(twoDimGraph, cero);
        SearchStopFactory searchStopFactory = new SearchStopFactory();
        breadthFirstSearch.searchResult.addNodeClosedListener((node,information)->breadthFirstSearch.stop());
        breadthFirstSearch.findPaths(searchStopFactory.expandAllNodes());
        //breadthFirstSearch.findPaths(searchStopFactory.maxNodeCount(3));
        System.out.println("lol");

    }



    public DepthFirstSearch(Graph graph, N start){
        super();
        this.graph = graph;
        setGraph( graph);
        setStart(start);
    }

    private void expand(N node, SearchStopStrategy searchStopStrategy){

    }

    public double getDistance(Node node, SearchResult result, NodeInforamtion nodeInforamtion){

        return  0;
    }



    @Override
    public SearchResult findPaths(SearchStopStrategy type) { // da ist der Komplette Algo drin
        stopped = false;
        Graph graph = getGraph();
        Stack<N> OpenStack = new Stack<>();
        Stack<N> CloseStack = new Stack<>();
        searchResult.clear();
        boolean AnfangsknotenIstImGraph = false;
        Collection<N> nodecollection = graph.getNodes();
        for (N node : nodecollection){
            if(node.equals(start) ){
                AnfangsknotenIstImGraph = true;
            }

        }
        if(!AnfangsknotenIstImGraph){
            searchResult.statusMap.put(start,NodeStatus.UNKOWN);
            NodeInforamtion nodeInforamtion = new NodeInforamtion();
            Edge<N,A> edge = new Edge<>(start,start, Optional.empty());
            nodeInforamtion.setDistance(0);
            nodeInforamtion.setPredecessor(edge);

            searchResult.infoMap.put(start,nodeInforamtion);
            searchResult.setOpen(start);
            searchResult.setClosed(start);
            return searchResult;
        }



        initialize(graph);


        searchResult.setOpen(start);
        System.out.println("Ich bin bis hier in gekommen");
        OpenStack.push(start);

        while (!OpenStack.isEmpty()){

            if(stopped){
                return searchResult;
            }
            N currentNode = OpenStack.pop();
            CloseStack.push(currentNode);

            for(Map.Entry<N,Edge<N,A>> entry: hashMap.entrySet()){
                if(entry.getValue().getDestination().equals(currentNode)){
                    System.out.println("hier wird der Predecessor gesetzt");
                    searchResult.getInformation(currentNode).setPredecessor(entry.getValue());
                }
            }
            N nachbarNode = null;
            System.out.println("fertig mit Predecessoren setzten");

            hashMap.clear();

            Collection<Edge<N,A>> nachbarn2= graph.getNeighbours(currentNode);
            for(Edge<N,A> ed : nachbarn2){
                nachbarNode = ed.getDestination();
                searchResult.getInformation(nachbarNode).setDistance(2.0);
                hashMap.put(nachbarNode,ed);
            }

           /* Collection<Edge<N,A>> nachbarn= graph.getEdges();
            for(Edge<N,A> e: nachbarn){
                System.out.println("Es werden gerade nachbarn gefunden");

                if(e.getStart().equals(currentNode)){
                    nachbarNode = e.getDestination();
                    searchResult.getInformation(nachbarNode).setDistance(2.0);
                    hashMap.put(nachbarNode,e);
                }
            }*/

            for(Map.Entry<N,Edge<N,A>> stacknode: hashMap.entrySet()){
                if(searchResult.getNodeStatus(stacknode.getKey()).equals(NodeStatus.UNKOWN)){
                    searchResult.setOpen(stacknode.getKey());
                    OpenStack.push(stacknode.getKey());
                }
            }



            if(type.stopSearch(currentNode)){
                System.out.println(" es wird rückwärts druchl");
                int groeße = CloseStack.size();
                for (int i = 0; i < groeße; i++) {
                    N ruckwaertsnode = CloseStack.pop();
                    searchResult.setClosed(ruckwaertsnode);
                }
                stop();
                return searchResult;
            }
            /*if(stopped == true){
                return searchResult;
            }*/
        }

        int gros = CloseStack.size();
        for (int i = 0; i < gros; i++) {
            N ruckwaertsnode = CloseStack.pop();
            searchResult.setClosed(ruckwaertsnode);
            System.out.println("Es wird gerade umgedreht"); //todo vll hier einfach hinschreiben rd hsz divh getan
            if(stopped){
                return searchResult;
            }
        }
        return searchResult;

    }

    public void DFSVisit(Graph g, N n) {

        searchResult.setOpen(n); //u.d=time;u.color=gray
        Map<N,Edge<N,A>> nachbarn = getNachbarn(n); //  foreachv∈Adj[u]do
        for(Map.Entry<N,Edge<N,A>> entry : nachbarn.entrySet()){ //foreachv∈Adj[u]do
            if(searchResult.getNodeStatus(entry.getKey()) == NodeStatus.UNKOWN){ //ifv.color==whitethen
                //todo hat das was mit den Predecessor zutun v.π=u; PIII
                DFSVisit(g, entry.getKey());
            }
        }


    }

    public void initialize(Graph g){ //Initialize(GraphG)
        List<N> allnodes = (List<N>) g.getNodes(); //foreachu∈Vdo
        for (int i = 0; i < allnodes.size(); i++) {//foreachu∈Vdo
            searchResult.statusMap.put(allnodes.get(i), NodeStatus.UNKOWN); //u.color=white
            NodeInforamtion nodeInforamtion1 = new NodeInforamtion();
            searchResult.infoMap.put(allnodes.get(i), nodeInforamtion1);
        }
        /*Edge start = new Edge(getStart(), getStart(),Optional.empty());
        searchResult.getInformation(getStart()).setPredecessor(start);*/

        //distance brauchen wir ja hier noch nicht
    }

    public Stack<N> returnAllNodesClosed(){
        List<N> allnodes = (List<N>) searchResult.getAllKnownNodes();
        Stack<N> allnodesClosed = new Stack<>();
        for (int i = 0; i < allnodes.size(); i++) {
            if(searchResult.getInformation(allnodes.get(i)).getNodeStatus() == NodeStatus.CLOSED){
                allnodesClosed.add(allnodes.get(i));
            }
        }
        return allnodesClosed;
    }

    public  Map<N, Edge<N,A>> getNachbarn(N node){
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
        return Nachbarn;
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

    /**
     * Returns the observable search result so listener can be added before executing the search
     *
     * @return the observable search result
     */
    @Override
    public ObservableSearchResult getSearchResult() {
        return searchResult; //todo hier kommt die null pointer exception her
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



        /*initialize(graph);
        int time = 0;

        List<N> allnodes = (List<N>) graph.getNodes();
        for (int i = 0; i < allnodes.size(); i++) {
            if(searchResult.statusMap.get(allnodes.get(i)) == NodeStatus.UNKOWN){ //ifu.color==whitethenDFSVisit(G,u)
                DFSVisit(graph, allnodes.get(i));
                searchResult.setClosed(allnodes.get(i));
            }

        }
        return searchResult;
        //startnode in stack -> nachbarn durchgehen -> alle unknown nachbarn geöffnet -> wenn node von allen Nachbarn durch dann geclosed -> while stack not empty mache weiter mit foreach irgendwas suchen
        //-> nachdem node geschlossen -> stopsearch ture wenn ja dann abbruch
        NodeInforamtion<N,A> startNodeInfo = result.getInformation(start);
        Deque<NodeInforamtion<N,A>> stack = new LinkedList<>();  // hier nochmal einen Stack reinmachen
        stack.push(startNodeInfo);

        Stack<N> nodesOffen = new Stack<>();
        nodesOffen.addAll(searchResult.getAllOpenNodes());
        Stack<N> nodesZu = new Stack<>();
        nodesZu.addAll(returnAllNodesClosed());


            while (Objects.isNull(searchResult.getPredecessor(startNodeInfo))){
                if(type.stopSearch(start)){ //abbruchbedingung
                    return searchResult;
                }

                NodeInforamtion current = stack.pop(); //push auf 2Stack; //predecessors setzen //nachbarn finden for Schleife graph.getEdges
                //schauen ob edge.getstart == current == nachbar
                //wenn Nachbarn unkown auf sTack -> setOpen -> Abbruchbedinung
                //alle bearbeiteten Knoten schließen nur wenn abgebrochen wird

                if(current.getNodeStatus() == NodeStatus.OPEN){//todo vorsicht diese Zeile soll gegen Schleifen im Graph helfen
                    current.setNodeStatus(NodeStatus.CLOSED);
                    System.out.println(current);
                    N zwischenspeichernode = (N) current.getStartNode();
                    Map<N, Edge<N,A>> getNeighbours = getNachbarn((N) current.getDestNode());
                    NodeInforamtion<N,A> zwischenspeicherNodeInfo = result.getInformation(current.getStartNode()); // hier soll quasi die nächst hintere Element genommen werden
                    stack.push(zwischenspeicherNodeInfo);
                    start = (N) zwischenspeichernode; // hier werden die einzelnen
                }


            }*/

/*Collection<Edge<N,A>> nachbarn= graph.getNeighbours(currentNode);
            for(Edge<N,A> ed : nachbarn){
                searchResult.getInformation(ed.getDestination()).setPredecessor(ed);
            }
            System.out.println("hier habe ich nicht abgebrochen");
            for(Edge<N,A> ed : nachbarn){
                if(searchResult.getNodeStatus(ed.getDestination()) == NodeStatus.UNKOWN){
                    OpenStack.push(ed.getDestination());
                    searchResult.setOpen(ed.getDestination());
                }
            }*/