package de.jpp.model;

import java.util.Objects;

public class XYNode {
    String label;
    double x;
    double y;

    public static void main(String[] args) {
        XYNode xyNode = new XYNode("Hello", -2.0, -1.0);
        XYNode xyNode1 = new XYNode("What UP", 0.0,1.0);
        double x = xyNode.euclidianDistTo(xyNode1);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("x: "+x+ "y: "+y);

        return  sb.toString();
    }
    /**
     * Creates a new XYNode with the specified label and coordinate
     *
     * @param label the label
     * @param x     the x value of the coordinate
     * @param y     the y value of the coordinate
     */

    public XYNode(String label, double x, double y) {
        if(Objects.isNull(label)){
            throw new IllegalArgumentException("XYNode Konstruktor label darf nicht null sein");
        }
        this.label = label;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the label of this node
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the x coordinate of this node
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this node
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Calculates the euclidian distance to the specified XYNode
     *
     * @param other the node to calculate the distance to
     * @return the euclidian distance to the specified XYNode
     */
    public double euclidianDistTo(XYNode other) {
        double gleichung1 = Math.pow(x- other.getX(),2);
        double gleichung2 = Math.pow(y- other.getY(),2);
        double ergebniss = Math.pow(gleichung1+gleichung2,0.5);
        return ergebniss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XYNode xyNode = (XYNode) o;
        return Double.compare(xyNode.x, x) == 0 && Double.compare(xyNode.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
