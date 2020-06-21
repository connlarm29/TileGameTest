/*
    blocks
    0   spikes
    1   table
    2   reb_bricks
    3   candle
    4   blue_bricks
######################
    terrain
    0   grass
    1   sand
    2   snow
    3   water
######################
    natural_objects
    0   cactus
    1   rock
    2   snow_tree
    3   green_tree
######################
 */

package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    Image[] terrain = new Image[4];
    BufferedImage sheet1;
    BufferedImage sheet2;
    BufferedImage sheet3;
    BufferedImage[] natural_objects = new BufferedImage[4];
    BufferedImage[] blocks = new BufferedImage[5];
    ImageIcon water = new ImageIcon(this.getClass().getResource("/res/WATER.gif"));

    public Tile(int catigory, int ID){

        //loads images
        try {
            sheet1 = ImageIO.read(this.getClass().getResource("/res/TERRAIN2.png"));
            sheet2 = ImageIO.read(this.getClass().getResource("/res/TERRAIN3.png"));
            sheet3 = ImageIO.read(this.getClass().getResource("/res/BLOCKS.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        for(int A = 0; A < 4; A++) {
            terrain[A] = sheet1.getSubimage(A*16,0,16,16);
        }
        terrain[3] = water.getImage();
        for(int A = 0; A < 4; A++) {
            natural_objects[A] = sheet2.getSubimage(A*16,0,16,16);
        }
        for(int A = 0; A < 5; A++) {
            blocks[A] = sheet3.getSubimage(A*16,0,16,16);
        }
    }



}
