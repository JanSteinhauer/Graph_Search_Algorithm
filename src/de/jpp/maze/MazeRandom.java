package de.jpp.maze;

import de.jpp.factory.MazeFactory;

import java.awt.image.BufferedImage;
import java.util.Random;

public class MazeRandom extends MazeImpl {
    private static Maze hallo;//todo Xpos steigt immer stedy und Ypost steigt immer um so 10 - 20 checken irgendwann nicht wann es genug ist
    //todo kommt auch des öfteren zu der Abbruchbedinung
    //wie schaffe ich es  X und Y kleinzuhalten

    Random random;
    int width;
    int height;
    Maze maze = new MazeImpl(); //todo alles auf V spielen von H
    int orginalwidht;
    int orginatlheight;
    Random ran;

    public static void main(String[] args) {
        Random ran = new Random();
        ran.setSeed(27051994L);
        MazeRandom mazeRandom = new MazeRandom(ran, 25, 12);
        System.out.println(mazeRandom.maze.isVWallActive(0, 4));
        System.out.println(mazeRandom.maze.isVWallActive(1, 6));
        MazeFactory mazeFactory = new MazeFactory();
        mazeFactory.mazeRandom = mazeRandom.maze;
        BufferedImage buf = mazeFactory.getMazeAsImage(mazeRandom.maze);


        System.out.println("lol");

    }

    public MazeRandom(Random ran, int width, int height) {
        this.ran = ran;
        this.width = width;
        this.height = height;
        this.orginalwidht = width;
        this.orginatlheight = height;

        this.width = orginalwidht;
        this.height = orginatlheight;

        maze = splitLaby1(0,0,getHeight(),getWidth(),this);

    }

    public Maze splitLaby1( int x, int y, int height, int width,Maze maze){
        if (height <= 1 || width <= 1) { //Wenn das Labyrinth nur aus einen Gang besteht:
            System.out.println("ich breche ab");                     //Breche den Algorithmus ab
            return maze;


        }
        boolean goForHeight = false;
        boolean goForWidth = false;


        if (width == height) { //Ansonsten: Wähle einen zufälligen Wahrheitswert Ist dieser wahr, unterteile das Labyrinth horizontal, sonst vertikal
            boolean randomAuswahl = ran.nextBoolean();
            if (randomAuswahl) {
                goForWidth = true;
            } else {
                goForHeight = true;
            }
        }
        if (width > height || goForWidth) {
            return splitLabyHorizontally(x,y,height,width, maze);
            //return unterteileHorizontal(maze, Xpos, Ypos, height,width);
        } else {
            //return divideVertically(maze,height,width,Xpos,Ypos);
            return splitLabyVertically(x,y,height,width,maze);
        }
    }

    private Maze splitLabyHorizontally(int xPosition ,int yPosition, int height, int width,Maze maze){
        int rnd1 = ran.nextInt(width-1);
        System.out.println(rnd1);

        int rnd2 = ran.nextInt(height-1);

        System.out.println(rnd2);

        if(rnd1 == 0 || rnd2 == 0){
            System.out.println("Es gibt ein Problem mit splitLabyHorizontally");
        }

        for(int i = yPosition; i<yPosition+height; i++){
            if(i == rnd2+yPosition){
            }else {
                maze.setHWall(xPosition+rnd1,i,true);
            }
        }

        if(width == 2){
            splitLaby1(xPosition,yPosition,height,1,maze);
            splitLaby1(xPosition+rnd1+1,yPosition,height,1,maze);
        }
        splitLaby1(xPosition,yPosition,height,rnd1+1,maze);
        System.out.println("SplitVertically mit posX"+xPosition+"posY"+yPosition);
        splitLaby1(xPosition+rnd1+1,yPosition, height,(width -rnd1-1),maze );

        return maze;
    }

    public Maze splitLabyVertically(int xPosition , int yPosition, int height, int width, Maze maze){
        int rnd1 = ran.nextInt(height-1);
        System.out.println(rnd1);

        int rnd2 = ran.nextInt(width-1);
        System.out.println(rnd2);

        if(rnd1 == 0 || rnd2 == 0){
            System.out.println("Es gibt ein Problem mit splitLabyVertically");
        }
        for(int i= xPosition; i< xPosition+width; i++){
            if(i == xPosition +rnd2){

            }else{
                maze.setVWall(i,yPosition+rnd1, true);
            }
        }
        if(height == 2){
            splitLaby1(xPosition,yPosition,1,width,maze);
            splitLaby1(xPosition,yPosition+rnd1+1,1,width,maze);
        }
        splitLaby1( xPosition,yPosition, rnd1+1,width,maze);
        splitLaby1(xPosition,yPosition+rnd1+1,(height-rnd1-1),width,maze);

        return maze;

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public boolean equals(Object anObject) {
        return true;
    }



}