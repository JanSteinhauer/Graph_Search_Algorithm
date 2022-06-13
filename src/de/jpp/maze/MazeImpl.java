package de.jpp.maze;

import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeImpl implements Maze{

    HashMap<List<Integer>, XYNode> nodeid = new HashMap<>();
    HashMap<List<Integer>, Boolean> isActivemapH = new HashMap<>();
    HashMap<List<Integer>, Boolean> isActivemapV = new HashMap<>();
    int width;
    int height;


    public void setWidth(int width){
        this.width = width;
    }

    public  void  setHeight(int height){
        this.height = height;
    }

    public static void main(String[] args) throws IOException {
        MazeImpl maze = new MazeImpl();
        maze.setVWall(1,2, true);
        System.out.println(maze.isVWallActive(1,2));
        System.out.println(maze.isVWallActive(3,2));
        //maze.start();

    }

    public void start() throws IOException {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        //Image picture = ImageIO.read(new File("picture.png"));
        BufferedImage image = ImageIO.read(new File("maze1.png"));
        System.out.println("width ist "+image.getWidth());
        System.out.println("height ist "+image.getHeight());
        int width = image.getWidth();
        int height = image.getHeight();
        int widthMalHeight = image.getWidth()*image.getHeight();
        for (int i = 0; i < height; i++) { // todo immer mit minus eins nehmen
            for (int j = 0; j < width; j++) {
                double x = j;
                double y = i;
                int farbe = image.getRGB(j,i);
                if(farbe == -1){
                    List<Integer> nodeList = new ArrayList<>();
                    XYNode xyNode = new XYNode("",x,y);
                    twoDimGraph.addNodes(xyNode);
                    nodeList.add(j);
                    nodeList.add(i);
                    nodeid.put(nodeList, xyNode );
                    //nodeList.clear();
                    System.out.println("weiss");
                }else {
                    System.out.println("schwarz");
                }
            }
        }
        for(XYNode node: twoDimGraph.getNodes()){
            List<Integer> nodeList = new ArrayList<>();
            nodeList.add((int)node.getX()+1);
            nodeList.add((int)node.getY());
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList));
            }
            nodeList.clear();

            nodeList.add((int)node.getX()-1);
            nodeList.add((int)node.getY());
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList));
            }
            nodeList.clear();

            nodeList.add((int)node.getX());
            nodeList.add((int)node.getY()+1);
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node,nodeid.get(nodeList));
            }
            nodeList.clear();

            nodeList.add((int)node.getX());
            nodeList.add((int)node.getY()-1);
            if(nodeid.containsKey(nodeList)){
                twoDimGraph.addEdge(node, nodeid.get(nodeList));
            }
            nodeList.clear();
        }


        System.out.println("lol");
    }



    @Override
    public void setHWall(int x, int y, boolean wallActive) {//todo hier immer width -1
        List<Integer> vglList = new ArrayList<>();
        vglList.add(x);
        vglList.add(y);

        isActivemapH.put(vglList, wallActive);

    }

    @Override
    public void setVWall(int x, int y, boolean wallActive) { //todo hier immer height -1
        List<Integer> vglList = new ArrayList<>();
        vglList.add(x);
        vglList.add(y);

        isActivemapV.put(vglList, wallActive);
    }

    @Override
    public void setAllWalls(boolean wallActive) {
        for (int i = 0; i < height; i++) {//todo hier wird auch height-1 auf height erhöht
            for (int j = 0; j < width; j++) {
                setVWall(j,i,wallActive);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {// todo testweise wird width auf 1 erhöht
                setHWall(j,i,wallActive);
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isHWallActive(int x, int y) {
        List<Integer> vglList = new ArrayList<>();
        vglList.add(x);
        vglList.add(y);
        XYNode node = (XYNode) nodeid.get(vglList);
        try {
            boolean res = isActivemapH.get(vglList);
            return res;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean isVWallActive(int x, int y) {
        List<Integer> vglList = new ArrayList<>();
        vglList.add(x);
        vglList.add(y);
        XYNode node = (XYNode) nodeid.get(vglList);
        try {
            boolean res = isActivemapV.get(vglList);
            return res;
        }catch (Exception e){
            return false;
        }
    }

    public boolean equals(Object anObject) {
        return true;
    }
}

