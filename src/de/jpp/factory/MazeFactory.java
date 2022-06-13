package de.jpp.factory;

import de.jpp.io.interfaces.GraphReader;
import de.jpp.maze.Maze;
import de.jpp.maze.MazeImpl;
import de.jpp.maze.MazeRandom;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class MazeFactory { //todo hier wurde TwodimGraph NA ersetzt

    public Maze mazeRandom = new MazeImpl();

    public static void main(String[] args) {
        MazeFactory mazeFactory = new MazeFactory();
        Maze maze = mazeFactory.getEmptyMaze(6,5);
        maze.setHWall(0,0, true);

       /* maze.setAllWalls(true);
        maze.setHWall(5,0,false);*/
        //maze.setVWall(3,3,false);
        mazeFactory.getMazeAsImage(maze);

        System.out.println(maze.isHWallActive(0,0));
    }


    /**
     * Creates a new empty maze with the specified width and height
     *
     * @param width  the width
     * @param height the height
     * @return a new empty maze with the specified width and height
     */
    public Maze getEmptyMaze(int width, int height) {
        MazeImpl maze = new MazeImpl();
        maze.setWidth(width);
        maze.setHeight(height);
        return maze;
    }

    /**
     * Returns a pixel representation of the specified maze
     *
     * @param maze the maze
     * @return a pixel representation of the specified maze
     */
    public BufferedImage getMazeAsImage(Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width*2+1, height*2+1, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width*2+1, height*2+1);

        // fill all the image with white
        for (int i = 1; i < height*2; i +=2) {
            for (int j = 1; j < width*2; j+=2) {
                    int x = j;
                    int y = i;
                    g2d.setColor(Color.white);
                    g2d.fillRect(x, y, 1, 1);
            }

        }

        for (int i = 2; i < height*2; i +=2) { //todo ersetzen des Hwall
            for (int j = 1; j < width*2; j+=2) {
                int x =(int) (j-1)/2;
                int y  =(int) (i-2)/2;
                if(!maze.isVWallActive(x,y)){
                    g2d.setColor(Color.white);
                    g2d.fillRect(j, i, 1, 1);
                }
            }
        }

        for (int i = 1; i < height*2; i +=2) { //todo ersetzen des Vwall
            for (int j = 2; j < width*2; j+=2) {
                int x =(int) (j-2)/2;
                int y  =(int) (i-1)/2;
                if(!maze.isHWallActive(x,y)){
                    g2d.setColor(Color.white);
                    g2d.fillRect(j, i, 1, 1);
                }
            }
        }

        /*
        for (int i = 0; i < width; i++) { //todo obere Border
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, i, 0);
        }

        for (int i = 0; i < width; i++) { //todo untere Border
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, i, height-1);
        }
        for (int i = 0; i < height; i++) { //todo links Border
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, 0, i);
        }
        for (int i = 0; i < height; i++) { //todo rechte Border
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, width-1, i);
        }

        // fill all the image with white
        for (int i = 0; i < height; i +=2) {
            for (int j = 0; j < width; j+=2) {
                if(!maze.isHWallActive(j,i) && !maze.isVWallActive(j,i)){
                    int x = j;
                    int y = i;
                    g2d.setColor(Color.white);
                    g2d.fillRect(0, 0, x, y);
                }
            }

        }

        for (int i = 1; i < height; i +=2) { //todo ersetzen des Hwall
            for (int j = 2; j < width; j+=2) {
                if(maze.isHWallActive(j,i)){
                    g2d.setColor(Color.black);
                    g2d.fillRect(0, 0, j, i);
                }else {
                    g2d.setColor(Color.white);
                    g2d.fillRect(0, 0, j, i);
                }
            }
        }

        for (int i = 2; i < height; i +=2) { //todo ersetzen des Vwall
            for (int j = 1; j < width; j+=2) {
                if(maze.isVWallActive(j,i)){
                    g2d.setColor(Color.black);
                    g2d.fillRect(0, 0, j, i);
                }else {
                    g2d.setColor(Color.white);
                    g2d.fillRect(0, 0, j, i);
                }
            }
        }*/


        /*try {
            File outputfile = new File("image.jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);
        }catch (Exception e){
            throw new IllegalArgumentException("Fehler ist bei der Erstellung eines Bilds aufgetreten");
        }*/


        return bufferedImage;
    }

    /**
     * Returns a random maze with specified width, height created by the specified algorithm specified from the instruction <br>
     * Random numbers are only taken from the specified random number generator (RNG) and only as specified in the instruction
     *
     * @param ran    the random number generator (RNG)
     * @param width  the width
     * @param height the height
     * @return a random maze with specified width and height
     *///erstelle eine instanz von maze
    public static Maze getRandomMaze(Random ran, int width, int height) { //rallen in welcher wand ich gerade stecke , , int width, int height, int pos, Maze maze
        MazeRandom maze = new MazeRandom(ran, width, height);//

        return maze;

    }

    /**
     * Returns a GraphReader which parses a TwoDimGraph from a Maze-Object
     *
     * @return a GraphReader which parses a TwoDimGraph from a Maze-Object
     */
    public GraphReader<XYNode, Double, TwoDimGraph, Maze> getMazeReader() {
        throw new UnsupportedOperationException("not supported yet!");
    }

}
