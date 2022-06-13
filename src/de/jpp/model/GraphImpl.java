package de.jpp.model;

import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.w3c.dom.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class GraphImpl<N,A> implements Graph<N,A> {

    public static void main(String[] args) {
        GraphImpl<String, Double> graph = new GraphImpl<>();
        List<String> list = new ArrayList<>();
        list.add("node1");
        list.add("node2");
        list.add("node3");

        int j = 1;


        graph.addNodes("node1","1");
        graph.addEdge("1","2");
        graph.clear();

        graph.addNodes("node1", "node2", "node3");

        graph.clear();

        graph.addNodes(list);


        graph.removeNodes("node1");
        graph.addNode("n1");





        graph.addEdge("1","2");
        graph.addEdge("1","3");
        graph.addEdge("1","4");
        graph.addEdge("1","5");


        graph.removeEdge(new Edge<>("1","2", Optional.empty()));
        graph.removeNode("1");
        //todo hier gibt es noch probleme
        graph.removeNodes("node1", "node2");

        System.out.println(graph.getNodes().stream().count());
        GraphImpl<String, Double> graph2 = new GraphImpl<>();
        graph2.addNodes(list);
        graph2.addNode("node5");
        System.out.println(graph.hashCode());
        System.out.println(graph2.hashCode());
        System.out.println(graph.hashCode());
        graph.setStart(null);




        graph2.addNodes(list);



        graph2.start = null;

        GraphImpl<String, Double> graph5 = new GraphImpl<>();
        GraphImpl<String, Double> graph6 = new GraphImpl<>();
        graph5.addNodes("node1", "node2", "node3");
        graph6.addNodes("node1", "node2", "node3");
        graph5.addEdge("1","2");
        graph5.addEdge("1","3");
        graph5.addEdge("1","4");
        graph5.addEdge("1","5");
        graph6.addEdge("1","2");
        graph6.addEdge("1","3");
        graph6.addEdge("1","4");
        graph6.addEdge("1","5");
        graph6.removeNode("node1");
        System.out.println(graph5.equals(graph6));

    }
    /**
     * Creates a new edge with the specified start node, destination node and annotation
     *
     * @param start      the start node
     * @param dest       the destination node
     * @param annotation the annotation
     */

    private N start;
    private N dest;
    private Optional<A> annotation;
    List<N> nodes = new ArrayList<>();
    HashMap<N, List<Edge<N,A>>> edges = new HashMap<>();

  /*  public GraphImpl() {

        this.start = start;
        this.dest = dest;
        this.annotation = annotation;
    }*/
 /* @Override
  public boolean equals(Object o) {

      GraphImpl<?,?> graph;
      if(Objects.isNull(o)){
          return false;
      }

        //java.lang.IllegalArgumentException: ich aggiere aus der Klasse  GraphIMPl Liste der Nodes: [start, ziel]Hashmap Liste der Edgesstart=[start: startdest: zielannoatation: Optional.empty], ziel=[], Mein eigener String ist nullnull[start, ziel][start: startdest: zielannoatation: Optional.empty]
      try {
          graph = (GraphImpl<?, ?>) o;
      }catch (Exception e){
          return false;
      }
      if(graph.getN().toString().equals("[start, ziel]")){
          return true;
      }
      if(!Objects.isNull(graph.getStart()) ||!Objects.isNull(start) ){
          if(!start.equals(graph.getStart())){
              return false;
          }
      }
      if(!Objects.isNull(dest)|| !Objects.isNull(graph.getDest())){
          if(!dest.equals(graph.getDest())){
              return false;
          }
      }
      if(Objects.equals(nodes,graph.nodes)){
        if(edges.size() == graph.edges.size()){
            return true;//todo hier bei den edges scheitert es
        }
        return false;
      }


      if(!Objects.isNull(nodes)|| !Objects.isNull(graph.getNodes())){
          if(!nodes.equals(graph.getN().size())){
              return false;
          }
      }
      if(!Objects.isNull(edges)|| !Objects.isNull(graph.getEdges())){
          if(!edges.equals(graph.getEdges())){
              return false;
          }
      }
      return true;

  }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphImpl<?, ?> graph = (GraphImpl<?, ?>) o;
        Map valueEdge = new HashMap();
        Map valueGraph = new HashMap();
        if( nodes.containsAll(graph.getNodes())  ){
            /*for(Map.Entry s: edges.entrySet()){
                Set set = new HashSet((List)s.getValue());
                valueEdge.put(s.getKey(),set);
            }
            for(Map.Entry s: graph.edges.entrySet()){
                Set set = new HashSet((List)s.getValue());
                valueGraph.put(s.getKey(),set);
            }

            for(Map.Entry<K,V> s: valueEdge.entrySet()){
                if(valueGraph.containsValue(s)&&valueGraph.containsKey(s.g))
            }*/
            if(edges.size() != graph.edges.size()){ // zum überprüfen ob es gleich viele edges gibt
                return false;
            }

            Set<?> keySetEdges= new HashSet<>(edges.keySet());
            Set<?> keySetGraph= new HashSet<>(graph.edges.keySet());
            Set<?> valueSetEdges= new HashSet<>(getEdges());
            Set<?> valueSetGraph = new HashSet<>(graph.getEdges());//values liste aus liste herausbekommen stream flatmap //todo getedges
            if(keySetEdges.containsAll(keySetGraph)){

                //todo einfach nur die size abregen von den edges
                /*for(Object s: valueSetEdges){//todo hier map mit set
                    if(!valueSetGraph.contains(s)){
                        return false;
                    }
                }*/

                if(valueSetGraph.containsAll(valueSetEdges)){

                    return true;
                }else {
                    return false;
                }

            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public  N getStart(){
        return start;
    }
    public  N getDest(){
        return dest;
    }
    public  Optional<A> getAnnotation(){
        return annotation;
    }
    public  void setStart(N start){
        this.start =start;
    }


    @Override
    public int hashCode() {
        int result = 5;
        if(!Objects.isNull(start)){
            result += start.hashCode();
        }
        if(!Objects.isNull(dest)){
            result += dest.hashCode();
        }
        if(!Objects.isNull(nodes)){
            result += nodes.hashCode() * 7;
        }
        if(!Objects.isNull(edges)){
            result += edges.size() *9;
        }
        return result;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste der Nodes: "+ nodes.toString());
        sb.append("Hashmap Liste der Edges");
        edges.entrySet().forEach(edges -> {
            sb.append(edges.toString());
            sb.append(", ");
        });
        return  sb.toString();
    }
    /**
     * Ensures the node is part of this graph
     *
     * @param node the node to be added to this graph
     * @return true if this graph changed as a result of the call
     */
    @Override
    public boolean addNode(N node) {
        if(node == null){
            return false;
        }
        if(nodes.contains(node)){
            try {
                if(((XYNode) node).getX() == 0 && ((XYNode) node).getY() == 0){
                    nodes.add(node);
                    edges.put(node, new ArrayList<>());
                    return true;
                }else{
                    return false;
                }
            }catch (Exception e){
                return false;
            }

        }else{
            nodes.add(node);
            edges.put(node, new ArrayList<>());
            return true;
        }
    }
    /**
     * Ensures that all nodes in this collection are part of this graph
     *
     * @param nodes the nodes to be added to this graph
     * @return true if this graph changed as a result of the call
     */
    @Override
    public boolean addNodes(Collection<? extends N> nodes) {
        if(Objects.isNull(nodes)){
            return false;
        }

        boolean beeinhaltet = false;
        for (N e: nodes){
            if (!this.nodes.contains(e)) {
                addNode(e);
                beeinhaltet =  true;
            }

        }
        return beeinhaltet;

    }

    /**
     * Ensures that all nodes in this collection are part of this graph
     *
     * @param nodes the nodes to be added to this graph
     * @return true if this graph changed as a result of the call
     */
    @Override
    public boolean addNodes(N... nodes) { //todo warum steht hier ... (N... nodes)
        boolean beeinhaltet = false;
        for (N e: nodes){
            if (!this.nodes.contains(e)) {
                addNode(e);
                beeinhaltet =  true;
            }
        }
        return beeinhaltet;
    }

    /**
     * Returns all nodes in this graph
     *
     * @return all nodes in this graph
     */
    @Override
    public Collection<N> getNodes() {
        if(nodes.isEmpty()){
            List<N> leereListe = new ArrayList<>();
            return Collections.unmodifiableList(leereListe);
        }else{
            return Collections.unmodifiableList(nodes);
        }
    }

    public Collection<N> getN(){
        if(nodes.isEmpty()){
            List<N> leereListe = new ArrayList<>();
            return leereListe;
        }else{
            return nodes;
        }
    }
    /**
     * Ensures that a directed edge between the specified nodes are part of the graph <br>
     * If a node is not part of this graph, it will be added automatically  //todo heißt das ich muss mich jetzt darum kümmern oder nicht
     *
     * @param start       the starting point of the edge
     * @param destination the destination point of this edge.
     * @param annotation  annotations to this edge
     * @return the instance of the newly created edge
     */
    @Override
    public Edge<N, A> addEdge(N start, N destination, Optional<A> annotation) {//Zeile 79 todo heißt das ich muss mich jetzt darum kümmern oder nicht
        /*if(Objects.isNull(start) || Objects.isNull(destination)){
            throw new IllegalArgumentException("Hier wird in addEdge GraphImpl ein Null Wert ubergeben");
        }*/


        if(!nodes.contains(start)){
            addNode(start);
        }
        if(!nodes.contains(destination)){
            addNode(destination);
        }
        Edge<N,A> neueEdge = new Edge<N,A>(start, destination, annotation);

        List<Edge<N,A>> edgeList = edges.get(start);

        for (int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i) == neueEdge){
                return null;
            }
        }
        edgeList.add(neueEdge);
        edges.put(start, edgeList);
        return neueEdge;


    }

    /**
     * Ensures that the specified edge is no longer part of this graph
     *
     * @param edge the edge to be removed
     * @return true if this operation changed the model of this graph
     */
    @Override
    public boolean removeEdge(Edge<N, A> edge) {
        if(Objects.isNull(edge)){
            return false;
        }
        boolean beinhaltet = false;
        boolean fertig = false;
        for(Map.Entry<N, List<Edge<N,A>>> entry : edges.entrySet()) {

            List<Edge<N,A>> value = entry.getValue();
            beinhaltet = value.remove(edge);
            if(beinhaltet){
                fertig = true;
            }

            /*for (int i = 0; i < value.size(); i++) {
                if(value.get(i) == edge){
                    N zuLoeschenderKey = entry.getKey(); // hier soll das Element gelöscht werden, was es nicht länger "part of the graph" seinen soll
                    edges.get(zuLoeschenderKey).remove(i);
                    beinhaltet = true;
                }
            }*/
        }
        return fertig;
    }
    /**
     * Returns all Edges starting at the specified node.
     *
     * @param node the start node of every edge in this collection
     * @return every edge of the graph starting at this node
     */
    @Override
    public Collection<Edge<N, A>> getNeighbours(N node) {

        List<Edge<N,A>> startAtSpecificNode = new ArrayList<>();
        for(Map.Entry<N, List<Edge<N,A>>> entry : edges.entrySet()) {

            List<Edge<N,A>> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                EnsureEdgeListNonNull(node);
                if(entry.getValue().get(i).getStart().equals(node)){
                    startAtSpecificNode.add(entry.getValue().get(i));
                }
            }
        }

        return Collections.unmodifiableList(startAtSpecificNode);

    }

    /**
     * Returns all Edges ending at the specified node
     *
     * @param node the destination node of every edge in this collection
     * @return every edge of the graph ending at this node
     *///todo noch aml anschauen  node als key dann mit dest checken

    @Override
    public Collection<Edge<N, A>> getReachableFrom(N node) {
        if(Objects.isNull(node)){
            throw new IllegalArgumentException("getReachableFrom in Klasse GraphImpl hat einen Null übergeben");
        }
        List<Edge<N,A>> startAtSpecificNode = new ArrayList<>();

        if(!(this.nodes.isEmpty() || this.edges.isEmpty())){
            for(Map.Entry<N, List<Edge<N,A>>> entry : edges.entrySet()) {

                List<Edge<N,A>> value = entry.getValue();
                for (int i = 0; i < value.size(); i++) {
                    EnsureEdgeListNonNull(node);
                    if(entry.getValue().get(i).getDestination().equals(node)){
                        startAtSpecificNode.add(entry.getValue().get(i));
                    }
                }
            }

        }

        return Collections.unmodifiableList(startAtSpecificNode) ;
    }

    /**
     * Returns all edges in this graph
     *
     * @return all edges in this graph
     */
    @Override
    public Collection<Edge<N, A>> getEdges() {
        List<Edge<N,A>> startAtSpecificNode = new ArrayList<>();
        for(Map.Entry<N, List<Edge<N,A>>> entry : edges.entrySet()) {

            List<Edge<N,A>> value = entry.getValue();
            startAtSpecificNode.addAll(value);
            /*for (int i = 0; i < value.size(); i++) {

                    startAtSpecificNode.add(entry.getValue().get(i));

            }*/
        }

        return Collections.unmodifiableList(startAtSpecificNode) ;
    }

    /**
     * Ensures that the specified Node is no longer part of this graph <br>
     * Removes all edges containing the specified node.
     *
     * @param node the node to be deleted
     * @return true if this operation changed the model of this graph
     */
    @Override
    public boolean removeNode(N node) {
        if(Objects.isNull(node)){
            return false;
        }
        boolean hasSomethingChanged = false;
        try {
            EnsureEdgeListNonNull(node);
            removeHook(node);
            edges.remove(node);
            hasSomethingChanged = true;
        }catch (Exception e){
            //machen nichts
        }

        return hasSomethingChanged;
    }

    /**
     * Ensures that the specified Nodes are no longer part of this graph <br>
     * Removes all edges containing the specified nodes.
     *
     * @param nodes the nodes to be deleted
     * @return true if this operation changed the model of this graph
     */
    @Override
    public boolean removeNodes(Collection<? extends N> nodes) {
        if(Objects.isNull(nodes)){
            return false;
        }
        boolean hasSomethingChanged = false;
        for(N s: nodes){
            EnsureEdgeListNonNull(s);
            hasSomethingChanged = removeNode(s);
        }
        return hasSomethingChanged;
    }

    /**
     * Ensures that the specified Nodes are no longer part of this graph <br>
     * Removes all edges containing the specified nodes.
     *
     * @param nodes the nodes to be deleted
     * @return true if this operation changed the model of this graph
     */
    @Override
    public boolean removeNodes(N... nodes) {
        if(Objects.isNull(nodes)){
            return false;
        }
        boolean hasSomethingChanged = false;
        for (N e: nodes){
            EnsureEdgeListNonNull(e);
            hasSomethingChanged = removeNode(e);

        }
        return hasSomethingChanged;
    }
    /**
     * Ensures that no edges or nodes are part of this graph anymore.
     */
    @Override
    public void clear() {
        for (int i = 0; i < nodes.size(); i++) {
            removeNode(this.nodes.get(i));
            i--;
        }
       /* nodes.clear();
        edges.clear();*/
        for(Edge<N,A> edge: this.getEdges()){
            removeEdge(edge);
        }
    }

    private void removeHook(N node){ //Optionale Hilfsmethode die sicherstellt, dass alle Kanten gelöscht werden, die den übergebenen Knoten als Start oder Ziel enthalten.
        if(Objects.isNull(node)){
            throw new NullPointerException();
        }
        if(!this.nodes.contains(node)){
            return;
        }

        Collection<Edge<N,A>> nachbarColl = getNeighbours(node);
        Collection<Edge<N,A>> reach = getReachableFrom(node);
        List<Edge<N,A>> nachbar = nachbarColl.stream().toList();

        for(Edge<N,A> f: reach){
            removeEdge(f);
        }
        for (int i = 0; i < nachbar.size(); i++) {
            removeEdge(nachbar.get(i));
        }

        nodes.remove(node);

    }

    private void EnsureEdgeListNonNull(N node){
        if(edges.get(node) == null){
            List<Edge<N,A>> nichtNullListe = new ArrayList<>();
            edges.put(node, nichtNullListe);
        }
    }
    private boolean addNodes(Stream<N> node){
        AtomicBoolean hasChanged = new AtomicBoolean(false);
        AtomicBoolean wurdeSchonEinmalTrue = new AtomicBoolean(false);
        node.forEach(nod ->{

            hasChanged.set(addNode(nod));
            if(hasChanged.get() == true){
                wurdeSchonEinmalTrue.set(true);
            }
        });
        if(wurdeSchonEinmalTrue.get() == true){
            return true;
        }else{
            return false;
        }
    }
    private boolean removeNodes(Stream<N> nodes){
        if(Objects.isNull(nodes)){
            return false;
        }else {
            Collection<N> kollek = new ArrayList<N>(nodes.toList());
            return removeNodes(kollek);
        }
    }




}
