package de.jpp.io.interfaces;

import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TwoDimImgReader<N, A, G extends Graph<N, A>, F> implements GraphReader<N, A, G , F> {

    HashMap<List<Integer>, XYNode> nodeid = new HashMap<>();
    int width;
    int height;

    public static void main(String[] args) throws ParseException, IOException {
        TwoDimImgReader twoDimImgReader = new TwoDimImgReader();
        BufferedImage image = ImageIO.read(new File("mazeSmall.png"));
        TwoDimGraph twoDimGraph = (TwoDimGraph) twoDimImgReader.read(image);
        TwoDimGraphGxlWriter twoDimGraphGxlWriter = new TwoDimGraphGxlWriter();
        String res = (String) twoDimGraphGxlWriter.write(twoDimGraph);
        TwoDimGraphGxlReader twoDimGraphGxlReader = new TwoDimGraphGxlReader();
        TwoDimGraph twoDimGraph1 = (TwoDimGraph) twoDimGraphGxlReader.read(res);
        TwoDimGraphDotIO twoDimGraphDotIO = new TwoDimGraphDotIO();
        String res2 = (String) twoDimGraphDotIO.write(twoDimGraph1);
        TwoDimGraph finalGraph = (TwoDimGraph) twoDimGraphDotIO.read(res2);

        System.out.println("lol");

    }


    @Override
    public G read(F input) throws ParseException {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        //Image picture = ImageIO.read(new File("picture.png"));
        BufferedImage image;
        try {
            //image = ImageIO.read(new File("maze1.png"));
            //image = ImageIO.read(new File("Maze.png"));
            image = (BufferedImage) input;
        }catch (Exception e){
            throw new ParseException();
        }

        System.out.println("width ist "+image.getWidth());
        System.out.println("height ist "+image.getHeight());
        int width = image.getWidth();
        int height = image.getHeight();
        int widthMalHeight = image.getWidth()*image.getHeight();
        for (int i = 0; i < height; i++) { // todo immer mit minus eins nehmen
            for (int j = 0; j < width; j++) {
                double x = j;
                double y = i;
                int farbe = image.getRGB(j,i); //todo hier ist das Farbmodell falsch
                int red =   (farbe & 0x00ff0000) >> 16;
                int green = (farbe & 0x0000ff00) >> 8;
                int blue =   farbe & 0x000000ff;
                float[] hsb = Color.RGBtoHSB(red, green, blue, null);
                float brightness = hsb[2];
                //if(farbe == -1){ // todo hier stand vorher -1
                //if(red == 255 && green == 255 && blue == 255){ // todo hier stand vorher -1
                if(brightness >= 0.5){ // todo hier stand vorher -1
                    List<Integer> nodeList = new ArrayList<>();
                    XYNode xyNode = new XYNode("dist=1.0",x,y); //todo das war vorher XYNode xyNode = new XYNode("",x,y);
                    twoDimGraph.addNodes(xyNode);
                    nodeList.add(j);
                    nodeList.add(i);
                    nodeid.put(nodeList, xyNode );
                    //nodeList.clear();
                    System.out.println("weiss");
                }else {
                    //System.out.println("schwarz");
                }
            }
        }
        for(XYNode node: twoDimGraph.getNodes()){
            List<Integer> nodeList = new ArrayList<>();
            nodeList.add((int)node.getX()+1);
            nodeList.add((int)node.getY());
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList), "dist=1.0");
            }
            nodeList.clear();

            nodeList.add((int)node.getX()-1);
            nodeList.add((int)node.getY());
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList),"dist=1.0");
            }
            nodeList.clear();

            nodeList.add((int)node.getX());
            nodeList.add((int)node.getY()+1);
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList),"dist=1.0");
            }
            nodeList.clear();

            nodeList.add((int)node.getX());
            nodeList.add((int)node.getY()-1);
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node, nodeid.get(nodeList),"dist=1.0");
            }
            nodeList.clear();
        }


        System.out.println("lol");
        return (G) twoDimGraph;
    }
}

