package de.jpp.model;

import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.WeightedGraph;

import java.util.Collection;
import java.util.Optional;

/**
 * A Two Dimensional graph. <br>
 * The abstract-tag is only set because the tests will not compile otherwise. You should remove it!
 */
public class TwoDimGraph extends ObservableGraphImpl<XYNode,Double> implements WeightedGraph<XYNode, Double> {

//    public static void main(String[] args) {
//        XYNode xyNode = new XYNode("Hello", 0, 0);
//        XYNode xyNode1 = new XYNode("What UP", 0.0,1.0);
//        Optional<Double> db = Optional.empty();
//
//        Edge<XYNode, Double> edge1 = new Edge(xyNode, xyNode1,db.of(5.0));
//        TwoDimGraph<, A> twoDimGraph = new TwoDimGraph<N, A>();
//
//        twoDimGraph.getDistance(edge1);
//        twoDimGraph.addEuclidianEdge(xyNode, xyNode1);
//        twoDimGraph.getNodes();
//    }


    /**
     * Adds an edge to the graph which is automatically initialised with the euclidian distance of the nodes <br>
     * Returns the newly created edge
     *
     * @param start the start node of the edge
     * @param dest  the destination node of the edge
     * @return the newly created edge
     */
    public Edge<XYNode, Double> addEuclidianEdge(XYNode start, XYNode dest) {
        double weight = start.euclidianDistTo(dest);
        Optional<Double> opt = Optional.of(weight);
        Edge<XYNode, Double> edge = new Edge<>(start,dest, opt);
        addEdge(start,dest,opt);

        return edge;
    }

    @Override
    public Collection<XYNode> getNodes() {

        return super.getNodes();

    }

    @Override
    public Edge<XYNode, Double> addEdge(XYNode start, XYNode destination, Optional<Double> annotation) {
        return super.addEdge(start, destination, annotation);
    }

    @Override
    public double getDistance(Edge<XYNode, Double> edge) {
        double weight = edge.getStart().euclidianDistTo(edge.getDestination());
        if(edge.getAnnotation().isPresent()){
           /* if(edge.getAnnotation().get() == )
            weight = weight * edge.getAnnotation().get();*/
            return edge.getAnnotation().get();
        }
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}


