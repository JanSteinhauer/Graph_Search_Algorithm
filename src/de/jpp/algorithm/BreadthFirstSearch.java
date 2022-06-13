package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.NodeStatus;
import de.jpp.algorithm.interfaces.SearchResult;
import de.jpp.algorithm.interfaces.SearchStopStrategy;
import de.jpp.factory.SearchStopFactory;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.w3c.dom.Node;

import java.util.*;

public class BreadthFirstSearch<N, A> extends BreadthFirstSearchTemplate {
    private Graph graph;
    private SearchResultImpl<Object, Object> searchResult = new SearchResultImpl<>();

    public static void main(String[] args) {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        /*XYNode eins = new XYNode("eins", 1,1);
        XYNode zwei = new XYNode("zwei",2,2);
        XYNode drei = new XYNode("drei",3,3);
        XYNode vier = new XYNode("vier",4,4);
        XYNode funf = new XYNode("funf",5,5);
        twoDimGraph.addNodes(eins,zwei ,drei ,vier ,funf);
        twoDimGraph.addEdge(eins, zwei);
        twoDimGraph.addEdge(eins, drei);
        twoDimGraph.addEdge(zwei, vier);
        twoDimGraph.addEdge(vier,funf);*/
       /* XYNode nuli = new XYNode("nuli",0,0);
        XYNode minusEinsY = new XYNode("minusEinsY",1,-1);
        XYNode minusEinsX = new XYNode("minusEinsX",-1,1);
        XYNode eins = new XYNode("eins", 1,1);
        XYNode zwei = new XYNode("zwei",2,0);
        XYNode Minuszwei = new XYNode("Minuszwei",-2,0);
        XYNode drei = new XYNode("drei",0,3);
        XYNode doppelMinus = new XYNode("Minus",-1,-1);
        twoDimGraph.addNodes(nuli, minusEinsY,minusEinsX,eins,zwei,Minuszwei,drei ,doppelMinus);
        twoDimGraph.addEdge(nuli,eins);
        twoDimGraph.addEdge(nuli,zwei);
        twoDimGraph.addEdge(nuli, minusEinsY);
        twoDimGraph.addEdge(minusEinsY, Minuszwei);
        twoDimGraph.addEdge(Minuszwei, doppelMinus);
        twoDimGraph.addEdge(eins,drei);
        twoDimGraph.addEdge(eins, minusEinsX);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(twoDimGraph, nuli);*/
       /* XYNode cero = new XYNode("cero",0,0);
        XYNode MenosunoUno = new XYNode("MenosunoUno", -1,1);
        XYNode Menosdos= new XYNode("MenosDos", -2,0);
        XYNode MenosUno = new XYNode("MenosUno", -1,-1);
        XYNode uno = new XYNode("uno",1,1);
        XYNode dos = new XYNode("dos",2,0);
        XYNode unoMenosUno= new XYNode("unoMenosUno",1,-1);
        XYNode tres = new XYNode("tres",0,3);
        twoDimGraph.addNodes(cero, MenosunoUno, Menosdos, MenosUno, uno, dos, unoMenosUno, tres);
        twoDimGraph.addEdge(cero, MenosunoUno);
        twoDimGraph.addEdge(MenosunoUno, cero);
        twoDimGraph.addEdge(MenosunoUno, Menosdos);
        twoDimGraph.addEdge(Menosdos,MenosunoUno);
        twoDimGraph.addEdge(Menosdos, MenosUno);
        twoDimGraph.addEdge(MenosUno, Menosdos);
        twoDimGraph.addEdge(cero, MenosUno);
        twoDimGraph.addEdge(MenosUno,cero);
        twoDimGraph.addEdge(MenosunoUno,tres);
        twoDimGraph.addEdge(tres, MenosunoUno);
        twoDimGraph.addEdge(cero, unoMenosUno);
        twoDimGraph.addEdge(unoMenosUno, cero);
        twoDimGraph.addEdge(unoMenosUno, dos);
        twoDimGraph.addEdge(dos, unoMenosUno);
        twoDimGraph.addEdge(dos,uno);
        twoDimGraph.addEdge(uno,dos);
        twoDimGraph.addEdge(uno,tres);
        twoDimGraph.addEdge(tres,uno);
        twoDimGraph.addEdge(cero,uno);
        twoDimGraph.addEdge(uno,cero);*/

        XYNode cero = new XYNode("cero",0,0);
        XYNode uno = new XYNode("uno",1,1);
        XYNode dos = new XYNode("dos",2,0);
        XYNode UnomenosUno = new XYNode("UnomenosUno",1,-1);
        XYNode menosUnoUno = new XYNode("menosUnoUno",-1,1);
        XYNode menosdos = new XYNode("menosdos",-2,0);
        XYNode menosUno = new XYNode("menosUno",-1,-1);
        XYNode tres = new XYNode("tres", 0,3);
        twoDimGraph.addNodes(cero,uno,dos,UnomenosUno,menosUno,menosUnoUno,menosdos,tres);
        twoDimGraph.addEdge(cero, uno);
        twoDimGraph.addEdge(uno,cero);
        twoDimGraph.addEdge(uno,dos);
        twoDimGraph.addEdge(dos,uno);
        twoDimGraph.addEdge(UnomenosUno,dos);
        twoDimGraph.addEdge(dos, UnomenosUno);
        twoDimGraph.addEdge(UnomenosUno,cero);
        twoDimGraph.addEdge(cero, UnomenosUno);
        twoDimGraph.addEdge(cero,menosUnoUno);
        twoDimGraph.addEdge(menosUnoUno,cero);
        twoDimGraph.addEdge(menosUnoUno,menosdos);
        twoDimGraph.addEdge(menosdos,menosUnoUno);
        twoDimGraph.addEdge(menosdos,menosUno);
        twoDimGraph.addEdge(menosUno,menosdos);
        twoDimGraph.addEdge(menosUno,cero);
        twoDimGraph.addEdge(cero,menosUno);
        twoDimGraph.addEdge(uno,tres);
        twoDimGraph.addEdge(tres,uno);
        twoDimGraph.addEdge(tres,menosUnoUno);
        twoDimGraph.addEdge(menosUnoUno,tres);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(twoDimGraph, cero);
        SearchStopFactory searchStopFactory = new SearchStopFactory();
        breadthFirstSearch.findPaths(searchStopFactory.expandAllNodes());
        System.out.println("Hello");
    }
    public BreadthFirstSearch(Graph graph, N node){
        super();
        this.graph = graph;
        setGraph(graph);
        setStart(node);

    }

    @Override
    public SearchResult findPaths(SearchStopStrategy type) {
        searchResult = (SearchResultImpl<Object, Object>) super.findPaths(type);
        return searchResult;
    }

    /*double getDistance(N node, SearchResultImpl searchResult, NodeInforamtion nodeInforamtion) {
        Queue<NodeInforamtion<N,A>> queue = new LinkedList<>();
        queue.add(nodeInforamtion);
        while (!Objects.isNull(nodeInforamtion.getPredecessor())){ //hier sollte eigentlich durch searchstopStrategy
            NodeInforamtion<N,A> current = queue.poll();
            if(current.getNodeStatus() == NodeStatus.OPEN){
                current.setNodeStatus(NodeStatus.CLOSED);
                N zwischenspeicherNode = current.getStartNode();
                NodeInforamtion<N,A> zwischenspeicherNodeInfo = searchResult.getInformation(zwischenspeicherNode);
                queue.add(zwischenspeicherNodeInfo);
                node = zwischenspeicherNode;
            }


        }

        return 0;
    }*/

    @Override
    double getDistance(Object node, SearchResultImpl searchResult, NodeInforamtion nodeInforamtion) {
        return 0;
    }

  /*  @Override
    public SearchResult findPaths(SearchStopStrategy type) {
        N start = (N) getStart();
        Graph graph = getGraph();
        NodeInforamtion nodeInforamtion = searchResult.getInformation(start);

        //todo setze den Nodestatus von allen auf Unknown
        initialize1(graph,start);


        LinkedList<N> nodequeue = new LinkedList<>();//Q= new Queue()
        nodequeue.add(start);//Q.Enqueue(s)
        while (!nodequeue.isEmpty()){ //todo while notQ.Empty()do   //while !nodeque.isEmpty

            //mit peek und dann entfernen //poll bei Dijkstra
            N currentNode = nodequeue.poll(); //u=Q.Dequeue()
            if(type.stopSearch(currentNode)){//aktueller Knoten // hier das ! weg
                stop(); //todo des hier ganz unten
                return searchResult;
            }
            if(isStopped()){  //5 mal random in Methode verteilt
                return searchResult;
            }
            Collection<Edge<N,A>> nachbarn= graph.getNeighbours(currentNode);
            for(Edge<N,A> ed : nachbarn){
                if(searchResult.getNodeStatus(currentNode) == NodeStatus.OPEN){ //ifv.color==whitethen


                    // searchResult.infoMap da hier die Distance keine Rolle spielt lasse ich sie weg
                    //v.d=u.d+ 1 und v.π=u //todo hier wo 1 steht dann die verschiedene Distance hinschreiben
                    NodeInforamtion nodeInforamtionUnKnow = new NodeInforamtion(ed, 1);
                    searchResult.open(ed.getDestination(), nodeInforamtionUnKnow);

                    nodequeue.addFirst(ed.getDestination());



                }
            }
            searchResult.setClosed(currentNode); //u.color=black
            if(type.stopSearch(currentNode)){//aktueller Knoten // hier das ! weg
                stop();
                return searchResult;
            }
            if(isStopped()){  //5 mal random in Methode verteilt
                return searchResult;
            }
            *//*NodeInforamtion<N,A> current = queue.poll();
            if(current.getNodeStatus() == NodeStatus.OPEN){
                current.setNodeStatus(NodeStatus.CLOSED);
                N zwischenspeicherNode = current.getStartNode();
                Map<N,Edge<N,A>> nachbarn = getNachbarn(start);
                for(Map.Entry<N,Edge<N,A>> entry : nachbarn.entrySet()){
                    //zum searchruslut hinzufügen
                    //wenn unknown untersuchen sonst nicht s machne
                    //list hinzufügen
                    //wenn bearbeitet schließen close aufrufen
                    //nodeinformation distance
                    //Priority queue für Dijkstra /Astar
                    //hier egal
                    //die Methode hier 20LoC
                    //keine Streams anschauen
                    queue.add(neueNodeInfo(entry.getValue(), entry.getKey()));
                }//immer dann searchresult

                start = (N) zwischenspeicherNode;
            }*//*
        }
        return searchResult;
    }*/

    @Override
    double getDistance(SearchResultImpl searchResult, Object anfangsnode, Edge edge) {
        N node = (N) anfangsnode;
        if(searchResult.getInformation(node).getPredecessor() == null){
            return 0;
        }else{
            Edge<N,A> ed = (Edge<N, A>) searchResult.getPredecessor(anfangsnode).get();
            return searchResult.getInformation(ed.getStart()).getDistance()+1; //anstatt +1 graph.getdistance (ed)

            //Astar edges zählen bis zum PUnkt wo man da ist
            //getpath to
            //für jede Edge anzahl der edges + estimated distance funkt dist berechnet
        }


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

    public void initialize1(Graph g, N n){ //Initialize(GraphG, Vertexs)
        List<N> allnodes = (List<N>) g.getNodes();
        for (int i = 0; i < allnodes.size(); i++) {//foreachu∈Vdo
            searchResult.statusMap.put(allnodes.get(i), NodeStatus.UNKOWN); //u.color=white
        }//todo bin mir nicht sicher ob man diese Zeile weglassen kann
        searchResult.setOpen(n);//s.color=gray

        NodeInforamtion nodeInforamtion1 = new NodeInforamtion();
        searchResult.infoMap.put(n, nodeInforamtion1);

        Edge start = new Edge(getStart(), getStart(),Optional.of(0));
        searchResult.getInformation(getStart()).setPredecessor(start);

        //searchResult.infoMap.put(n,new NodeInforamtion(null, 0));

        //distance brauchen wir ja hier noch nicht
    }



    /*
    public NodeInforamtion neueNodeInfo(Edge<N,A> edge, N node){
        NodeInforamtion nodeInforamtion = new NodeInforamtion(edge, 1);
        searchResult.infoMap.put( node,nodeInforamtion);
        searchResult.statusMap.put( node, NodeStatus.UNKOWN);
        return nodeInforamtion;
    }
*/


}


 /*   TwoDimGraph twoDimGraph = new TwoDimGraph();
    XYNode nuli = new XYNode("nuli",0,0);
    XYNode minusEinsY = new XYNode("minusEinsY",1,-1);
    XYNode minusEinsX = new XYNode("minusEinsX",-1,1);
    XYNode eins = new XYNode("eins", 1,1);
    XYNode zwei = new XYNode("zwei",2,0);
    XYNode Minuszwei = new XYNode("Minuszwei",-2,0);
    XYNode drei = new XYNode("drei",0,3);
    XYNode vier = new XYNode("vier",4,4);
    XYNode funf = new XYNode("funf",5,5);
        twoDimGraph.addNodes(nuli, minusEinsY,minusEinsX,eins,zwei,Minuszwei,drei );
                twoDimGraph.addEdge(nuli,eins);
                twoDimGraph.addEdge(eins,zwei);
                twoDimGraph.addEdge(zwei,minusEinsY);
                twoDimGraph.addEdge(minusEinsY, nuli);
                twoDimGraph.addEdge(nuli, minusEinsX);
                twoDimGraph.addEdge(minusEinsX,Minuszwei);
                twoDimGraph.addEdge(Minuszwei, minusEinsY);
                twoDimGraph.addEdge(minusEinsY, nuli);
                twoDimGraph.addEdge(eins,drei);
                twoDimGraph.addEdge(drei, minusEinsX);*/