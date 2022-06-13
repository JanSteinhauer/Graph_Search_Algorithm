package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.NodeStatus;
import de.jpp.algorithm.interfaces.ObservableSearchResult;
import de.jpp.algorithm.interfaces.SearchResult;
import de.jpp.model.interfaces.Edge;
import org.w3c.dom.Node;

import java.util.*;
import java.util.function.BiConsumer;

public class SearchResultImpl<N,A> implements ObservableSearchResult<N,A> {
    public Map<N, NodeStatus> statusMap = new HashMap<>();
    public Map<N, NodeInforamtion> infoMap = new HashMap<>();
    private List<BiConsumer<N, SearchResult<N, A>>> onOpen = new ArrayList<>();    //todo soll das der Biconsumer so behandelt werden wie die Consumer in Observable Graph?
    private List<BiConsumer<N, SearchResult<N, A>>> onClose = new ArrayList<>();


    public void close(N node,NodeInforamtion nodeInforamtion){
        nodeInforamtion.setDestNode(node);
        setClosed(node);
        infoMap.put(node,nodeInforamtion);
        nodeInforamtion.setNodeStatus(NodeStatus.CLOSED);
    }

    public void open(N node, NodeInforamtion nodeInforamtion){
        nodeInforamtion.setStartNode(node);
        setOpen(node);
        infoMap.put(node,nodeInforamtion);
        nodeInforamtion.setNodeStatus(NodeStatus.OPEN);
    }

    public Map<N, NodeStatus> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<N, NodeStatus> statusMap) {
        this.statusMap = statusMap;
    }

    public Map<N, NodeInforamtion> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<N, NodeInforamtion> infoMap) {
        this.infoMap = infoMap;
    }

    public NodeInforamtion getInformation(N node){
        if(this.infoMap.get(node) == null){
            return null;
        }else {
            return infoMap.get(node);
        }

    }


    @Override
    public void addNodeOpenedListener(BiConsumer<N, SearchResult<N, A>> onOpen) {
        this.onOpen.add(onOpen);

    }

    @Override
    public void removeNodeOpenedListener(BiConsumer<N, SearchResult<N, A>> onOpen) {
        this.onOpen.remove(onOpen);
    }

    @Override
    public void addNodeClosedListener(BiConsumer<N, SearchResult<N, A>> onClose) {
        this.onClose.add(onClose);

    }

    @Override
    public void removeNodeClosedListener(BiConsumer onClose) {
        this.onClose.remove(onClose);
    }

    @Override
    public NodeStatus getNodeStatus(Object node) {
        try {
            return statusMap.get(node);
        }catch (Exception e){
            throw new IllegalArgumentException("SearchRestultImpl getNodeStatus es gab keine passenden NodeSTatus für die Node");
        }
    }

    @Override
    public Optional<Edge<N, A>> getPredecessor(N node) {
        NodeInforamtion nodeInforamtion = getInformation(node);
        return nodeInforamtion.getPredecessorOptional();
    }

    @Override
    public Collection getAllKnownNodes() {
        List<N> allKnownNodes = new ArrayList<>();
        for(Map.Entry<N, NodeStatus> e: statusMap.entrySet()){
            allKnownNodes.add((N) e.getKey());
        }
        return allKnownNodes;
    }

    @Override
    public Collection getAllOpenNodes() {
        List<N> allOpenNodes = new ArrayList<>();
        for(Map.Entry<N, NodeStatus> e: statusMap.entrySet()){
            if(e.getValue() == NodeStatus.OPEN){
                allOpenNodes.add(e.getKey());
            }
        }
        return allOpenNodes;
    }

    @Override
    public void setClosed(N node) { //forschleife wie in Observable Graph
        statusMap.replace( node, NodeStatus.CLOSED);
        for(BiConsumer<N, SearchResult<N, A>> e : onClose){
            e.accept((N) node,this);
        }

    }

    @Override
    public void setOpen(N node) {
        statusMap.replace(node,NodeStatus.OPEN);
        for(BiConsumer<N, SearchResult<N, A>> e: onOpen){
            e.accept((N) node, this );
        }
    }

    @Override
    public void clear() {
        statusMap.clear();
        infoMap.clear();
        /*onOpen.clear();
        onClose.clear();*/
    }

    @Override
    public Optional<List<Edge<N,A>>> getPathTo(Object dest) { // todo es wird davon ausgegangen, dass die erste Node der InfoMap der start Knoten ist
        List<Edge<N,A>> edgeList = new ArrayList<>();
        if(!infoMap.containsKey(dest)){
            return Optional.empty();
        }
        Stack<Edge<N,A>> destStack = new Stack<>();
        N node= (N) dest;
        while (this.getPredecessor(node).isPresent()){
            destStack.push(getPredecessor(node).get());
            node = getPredecessor(node).get().getStart();

        }

        if(destStack.isEmpty()){
            return Optional.empty();
        }

        int s= destStack.size();
        for (int d = 0; d < s; d++) {
            edgeList.add(destStack.pop());
        }

        /*for(Edge<N,A > entry : destStack){
            edgeList.add(entry);
        }*/
        return Optional.of(edgeList);
        /*int index = -1;
        int indexstart =-1;
        for (Map.Entry<N,NodeInforamtion> e: infoMap.entrySet()){
            index += 1;
            if(e.getKey().equals(dest)){
                indexstart = index;
            }
        }
        if(indexstart == 0){
            return Optional.empty();
        }
        NodeInforamtion nodeInforamtion = infoMap.get(dest);
        try {
            Edge edge = nodeInforamtion.getPredecessor();
            edgeList.add(edge);
        }catch (Exception e){

        }



        return Optional.empty();*/
    }
}
