package de.jpp.model;

import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;

import java.util.*;
import java.util.function.Consumer;

public class ObservableGraphImpl<N,A> extends GraphImpl<N,A> implements ObservableGraph<N,A> {
    private Graph<N, A> graph;

    private List<Consumer<N>> nodeAddedListener = new ArrayList<>();
    private List<Consumer<N>> nodeRemovedListener = new ArrayList<>();
    private List<Consumer<Edge<N,A>>> edgeAddedListener = new ArrayList<>();
    private List<Consumer<Edge<N,A>>> edgeRemovedListener = new ArrayList<>();

    private List<Consumer<Collection<Edge<N, A>>>> neighboursListedListener = new ArrayList<>();
    private List<Consumer<Collection<Edge<N, A>>>> reachableListedListener = new ArrayList<>();
    private List<Consumer<Collection<Edge<N,A>>>> edgesListedListener = new ArrayList<>();
    private List<Consumer<Collection<N>>> nodesListedListener = new ArrayList<>();

    public static void main(String[] args) {
        ObservableGraph<String, String> observableGraph = new ObservableGraphImpl<>();

        observableGraph.addEdge("nod1","nod2");
        Edge<String,String> ed = new Edge("nod1","nod2",Optional.empty());


        observableGraph.removeEdge(ed);

        String n1 = "1";
        String n2 = "2";
        String n3 = "3";
        String n4 = "4";
        String n5 = "5";
        String n6 = "6";
        String n7 = "7";

        observableGraph.addNodes("node1", "node2", "node3");
        observableGraph.clear();


        //observableGraph.addNode(n1);

        observableGraph.addEdgeAddedListener(new Consumer<Edge<String, String>>() {
            @Override
            public void accept(Edge<String, String> stringStringEdge) {
                System.out.println("hello world");
            }
        });


        Edge<String, String> e1 = observableGraph.addEdge(n1,n2);
        Edge<String, String> e2 = observableGraph.addEdge(n1,n3);
        Edge<String, String> e3 = observableGraph.addEdge(n1,n4);
        Edge<String, String> e4 = observableGraph.addEdge(n1,n5);
        Edge<String, String> e5 = observableGraph.addEdge(n1,n6);

        observableGraph.removeEdge(e2);

        ObservableGraph<String, String> observableGraph2 = new ObservableGraphImpl<>();
        observableGraph2.addNodes("node1", "node2", "node3");
        Edge<String, String> e6 = observableGraph2.addEdge(n1,n2);
        Edge<String, String> e7 = observableGraph2.addEdge(n1,n3);
        Edge<String, String> e8 = observableGraph2.addEdge(n1,n4);
        Edge<String, String> e9 = observableGraph2.addEdge(n1,n5);
        Edge<String, String> e10 = observableGraph2.addEdge(n1,n6);
        //observableGraph.addNode(n1);
        observableGraph2.addNodes(n1,n2,n3,n4,n5,n6,n7);
        System.out.println(observableGraph.hashCode());
        System.out.println(observableGraph2.hashCode());




        observableGraph.getEdges();
        observableGraph.clear();

        System.out.println("Hello");




    }

    public ObservableGraphImpl() {

    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste der nodeAddedListener: "+ nodeAddedListener.toString());
        sb.append("Liste der nodeRemovedListener: "+ nodeRemovedListener.toString());
        sb.append("Liste der edgeAddedListener: "+ edgeAddedListener.toString());
        sb.append("Liste der edgeRemovedListener: "+ edgeRemovedListener.toString());
        sb.append("Liste der neighboursListedListener: "+ neighboursListedListener.toString());
        sb.append("Liste der reachableListedListener: "+ reachableListedListener.toString());
        sb.append("Liste der edgesListedListener: "+ edgesListedListener.toString());
        sb.append("Liste der nodesListedListener: "+ nodesListedListener.toString());
        return  sb.toString();
    }

    @Override
    public void addNodeAddedListener(Consumer<N> listener) {//todo ab hier kommen die Sachen vom Interface GraphWithObservableModel
        nodeAddedListener.add(listener);
    }//die eckigen noch einfügen <

    @Override
    public void addNodeRemovedListener(Consumer<N> listener) {
        nodeRemovedListener.add(listener);

    }

    @Override
    public void removeNodeAddedListener(Consumer<N> listener) {
        if(nodeAddedListener.contains(listener)){
            nodeAddedListener.remove(listener);
        }
    }

    @Override
    public void removeNodeRemovedListener(Consumer<N> listener) {
        if(nodeRemovedListener.contains(listener)){
            nodeRemovedListener.remove(listener);
        }

    }

    @Override
    public void removeEdgeRemovedListener(Consumer<Edge<N,A>> listener) {
        if(edgeRemovedListener.contains(listener)){
            edgeRemovedListener.remove(listener);
        }
    }

    @Override
    public void removeEdgeAddedListener(Consumer<Edge<N,A>> listener) {
        if(edgeAddedListener.contains(listener)){
            edgeAddedListener.remove(listener);
        }
    }

    @Override
    public void addEdgeRemovedListener(Consumer<Edge<N,A>> listener) {
        edgeRemovedListener.add(listener);
    }

    @Override
    public void addEdgeAddedListener(Consumer<Edge<N,A>> listener) {
        edgeAddedListener.add(listener);
    }

    @Override
    public void addNeighboursListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        neighboursListedListener.add(listener);
    }

    @Override
    public void addReachableListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        reachableListedListener.add(listener);
    }

    @Override
    public void addNodesListedListener(Consumer<Collection<N>> listener) {
        nodesListedListener.add(listener);
    }

    @Override
    public void addEdgesListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        edgesListedListener.add(listener);
    }

    @Override
    public void removeNeighboursListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        if(neighboursListedListener.contains(listener)){
            neighboursListedListener.remove(listener);
        }
    }

    @Override
    public void removeReachableListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        if(reachableListedListener.contains(listener)){
            reachableListedListener.remove(listener);
        }
    }

    @Override
    public void removeNodesListedListener(Consumer<Collection<N>> listener) {
        if(nodesListedListener.contains(listener)){
            nodesListedListener.remove(listener);
        }
    }

    @Override
    public void removeEdgesListedListener(Consumer<Collection<Edge<N,A>>> listener) {
        edgesListedListener.remove(listener);
    }

    @Override
    public Edge<N, A> addEdge(N start, N destination, Optional<A> annotation) {
        if(Objects.isNull(start) || Objects.isNull(destination)){
            return null;
        }
        Edge<N,A> edgeget = super.addEdge(start, destination, annotation);
        if(!Objects.isNull(edgeget)){
            for(Consumer<Edge<N,A>> e: edgeAddedListener){
                e.accept(edgeget);
            }
        }
        return edgeget;
    }

    @Override
    public boolean addNode(N node) {
        if(super.addNode(node)){
            for(Consumer<N> e: nodeAddedListener){
                e.accept(node);
            }
            return true;
        }
        return false;
    }

   /* @Override
    public boolean addNodes(Collection<? extends N> nodes) {
        if(super.addNodes(nodes)){
            for(Consumer<Collection<N>> e: nodesListedListener){
                e.accept(node);
            }
            return true;
        }
        return false;
    }*/


    //todo addnodes wurde weggelassen

    @Override
    public Collection<Edge<N, A>> getNeighbours(N node) {
        if(Objects.isNull(node)){
            return Collections.emptyList();
        }
        Collection<Edge<N,A>> nachbarList = super.getNeighbours(node);
        for (Consumer<Collection<Edge<N,A>>> f: neighboursListedListener){
            f.accept(nachbarList);
        }
        return nachbarList;
    }

    @Override
    public boolean removeNode(N node) {
        if(Objects.isNull(node)){
            return false;
        }
        if(super.removeNode(node)){
            for(Consumer<N> e: nodeRemovedListener){
                e.accept(node);
            }
            return true;
        }
        return false;
    }

    @Override
    public Collection<Edge<N, A>> getEdges() {
        Collection<Edge<N, A>> collectionedges = super.getEdges();
        for(Consumer<Collection<Edge<N, A>>> f : edgesListedListener){
            f.accept(collectionedges);
        }
        return super.getEdges();
    }

    @Override
    public void clear() {
        super.clear();
    }




    @Override
    public Collection<Edge<N, A>> getReachableFrom(N node) {

        Collection<Edge<N,A>> list = super.getReachableFrom(node);
        for(Consumer<Collection<Edge<N,A>>> f: reachableListedListener){
            f.accept(list);
        }
        return list;
    }

    @Override
    public Collection<N> getNodes() {
        //System.out.println("aktuell in getnodes");
        Collection<N> collectionnodes = super.getNodes();
        for(Consumer<Collection<N>> f : nodesListedListener){
            f.accept(super.getNodes());
        }
        return super.getNodes();
    }

    @Override
    public boolean equals(Object o) {
        if(Objects.isNull(o)){
            return false;
        }
        return super.equals(o);
    }

    @Override
    public boolean removeEdge(Edge<N, A> edge) {
        if(Objects.isNull(edge)){
            return false;
        }
        boolean removeEd = super.removeEdge(edge);
        for(Consumer<Edge<N,A>> f : edgeRemovedListener){
            f.accept(edge);
        }
        return removeEd;
    }
}

