package de.jpp.model.interfaces;

import java.util.Objects;
import java.util.Optional;

public class Edge<N, A> {
    private N start;
    private N dest;
    private Optional<A> annotation;
    /**
     * Creates a new edge with the specified start node, destination node and annotation
     *
     * @param start      the start node
     * @param dest       the destination node
     * @param annotation the annotation
     */
    public Edge(N start, N dest, Optional<A> annotation) {
        if(Objects.isNull(start) || Objects.isNull(dest)){
            throw new IllegalArgumentException("Falsche Paramenter beim Konstruktor von Edge übergeben");
        }
        this.start = start;
        this.dest = dest;
        if(!Objects.isNull(annotation)){ //Der Konstruktor soll zuerst den Startknoten, dann den Zielknoten, dann eine ggf. vorhandene Annotation übergeben bekommen. start und dest dürfen nicht null sein. Das private Attribut annotation darf nach Aufruf des Konstruktors nicht null, sehr wohl aber das leere Optional sein.
            this.annotation = annotation;
        }

    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("start: "+start+ "dest: "+dest+"annoatation: "+ annotation.toString());

        return  sb.toString();
    }

    /**
     * Returns the start node of this edge
     *
     * @return the start node of this edge
     */
    public N getStart() {
        return start;
    }

    /**
     * Returns the destination node of this edge
     *
     * @return the destination node of this edge
     */
    public N getDestination() {
        return dest;
    }

    /**
     * Returns the annotation of this edge
     *
     * @return the annotation of this edge
     */
    public Optional<A> getAnnotation() {
        return annotation;
    }
    // für edge equals machen
    //todo equals generieren lassen


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        boolean start = this.start.equals(edge.start);
        boolean dest = this.dest.equals(edge.dest);
        boolean anno = this.annotation.equals(edge.annotation);
        return start && dest && anno;
    }


    @Override
    public int hashCode() {
        return Objects.hash(start, dest, annotation);
    }
    /*@Override
    public int hashCode() {
        if(annotation.isPresent()){
            return Objects.hash(start, dest,annotation.get());
        }else {
            return Objects.hash(start, dest);
        }

    }*/
}