package de.jpp.factory;


import de.jpp.io.interfaces.*;
import de.jpp.model.LabelMapGraph;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;

import java.awt.image.BufferedImage;
import java.util.Map;

public class IOFactory { //todo hier wurde TwodimGraph NA ersetzt
    public static void main(String[] args) {
        System.out.println("Hello");
        IOFactory ioFactory = new IOFactory();
        ioFactory.getTwoDimGxlReader();
    }


    /**
     * Returns a new GraphReader instance which parses a TwoDimGraph from a GXL-String
     *
     * @return a new GraphReader instance which parses a TwoDimGraph from a GXL-String
     */
    public GraphReader<XYNode, Double, TwoDimGraph, String> getTwoDimGxlReader() {
        TwoDimGraphGxlReader twoDimGraphGxlReader = new TwoDimGraphGxlReader();
        return twoDimGraphGxlReader;

    }

    /**
     * Returns a new GraphReader instance which parses a TwoDimGraph from a DOT-String
     *
     * @return a new GraphReader instance which parses a TwoDimGraph from a DOT-String
     */
    public GraphReader<XYNode, Double, TwoDimGraph, String> getTwoDimDotReader() {
        return new TwoDimGraphDotIO<>();

    }

    /**
     * Returns a new GraphReader instance which parses a TwoDimGraph from a BufferedImage-Maze
     *
     * @return a new GraphReader instance which parses a TwoDimGraph from a BufferedImage-Maze
     */
    public GraphReader<XYNode, Double, TwoDimGraph, BufferedImage> getTwoDimImgReader() {

        return new TwoDimImgReader<>();
        /*throw new UnsupportedOperationException("not supported yet!");*/
    }

    /**
     * Returns a new GraphReader instance which parses a LabelMapGraph from a GXL-String
     *
     * @return a new GraphReader instance which parses a LabelMapGraph from a GXL-String
     */
    public GraphReader<String, Map<String, String>, LabelMapGraph, String> getLabelMapGraphGxlReader() {
        return new LabelMapGraphReader();
       // throw new UnsupportedOperationException("not supported yet!");
    }

    /**
     * Returns a new GraphWriter instances which outputs a TwoDimGraph as a GXL-String
     *
     * @return a new GraphWriter instances which outputs a TwoDimGraph as a GXL-String
     */
    public GraphWriter<XYNode, Double, TwoDimGraph, String> getTwoDimGxlWriter() {
        return new TwoDimGraphGxlWriter();

    }

    /**
     * Returns a new GraphWriter instance which outputs a TwoDimGraph as DOT-String
     *
     * @return a new GraphWriter instance which outputs a TwoDimGraph as DOT-String
     */
    public GraphWriter<XYNode, Double, TwoDimGraph, String> getTwoDimDotWriter() {
        return new TwoDimGraphDotIO<>();
       // throw new UnsupportedOperationException("not supported yet!");
    }

    /**
     * Returns a new GraphWriter instance which outputs a LabelMapGraph as GXL-String
     *
     * @return a new GraphWriter instance which outputs a LabelMapGraph as GXL-String
     */
    public GraphWriter<String, Map<String, String>, LabelMapGraph, String> getLabelMapGraphGxlWriter() {
        return new LabelMapGraphWriter();
        //throw new UnsupportedOperationException("not supported yet!");
    }

    //public LabelMapGraph createNewLabelMapGraph():
    //Erstellt einen neuen und leeren LabelMapGraph.
    public LabelMapGraph createNewLabelMapGraph(){
        return new LabelMapGraph();
    }





}
