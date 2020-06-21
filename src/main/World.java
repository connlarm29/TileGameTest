package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class World {
	
	/*
	 * TERRAIN2:
	 * 0 GRASS
	 * 1 SAND
	 * 2 SNOW
	 * 3 WATER
	 * 
	 *
	 * TERRAIN3:
	 * 0 CACTUS
	 * 1 ROCK
	 * 2 SNOW TREE
	 * 3 TREE
	 */
	private static int b1 = 1;
	private static int b2 = 2;
	private static int b3 = 3;

	private static final int TREES = 20000;
	private static final int ROCKS = 10000;
	private static final int BIOME_SIZE = 256000;
	
	
	public static final int SIZE = 1000;
	public static final int SPAWN_X = 500;
	public static final int SPAWN_Y = 500;
	BufferedImage sheet1;
	Image[] terrain = new Image[4];
	BufferedImage sheet2;
	BufferedImage[] natural_objects = new BufferedImage[4];
	ImageIcon water = new ImageIcon(this.getClass().getResource("/res/WATER.gif"));
	public int TERRAIN[][] = new int[SIZE][SIZE];
	public int BLOCKS[][] = new int[SIZE][SIZE];
	
	Random randomValue = new Random();
	int T = new Random().nextInt(500)+500;
	
	public World() {
		
		try {
			sheet1 = ImageIO.read(this.getClass().getResource("/res/TERRAIN2.png"));
			sheet2 = ImageIO.read(this.getClass().getResource("/res/TERRAIN3.png"));
			//water =  ImageIO.read(this.getClass().getResource("/res/WATER.gif"));
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

		
		//GRASS
		for(int i = 0; i < SIZE; i++) {


			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			TERRAIN[x][y] = b1;
		}

		//SAND
		for(int i = 0; i < SIZE; i++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			TERRAIN[x][y] = b2;
		}
		//SNOW
		for(int i = 0; i < SIZE; i++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			TERRAIN[x][y] = b3;
		}
		
		for(int i = 0; i < BIOME_SIZE; i++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			
			
			if(x > 0 && x < SIZE-1 && y > 0 && y < SIZE-1) {
			if(TERRAIN[x][y] == b1) {
			TERRAIN[x+1][y+1] = b1;
			TERRAIN[x-1][y-1] = b1;
			TERRAIN[x+1][y-1] = b1;
			TERRAIN[x-1][y+1] = b1;
			TERRAIN[x+1][y] = b1;
			TERRAIN[x-1][y] = b1;
			TERRAIN[x][y+1] = b1;
			TERRAIN[x][y-1] = b1;
			}else if(TERRAIN[x][y] == b2) {
			TERRAIN[x+1][y+1] = b2;
			TERRAIN[x-1][y-1] = b2;
			TERRAIN[x+1][y-1] = b2;
			TERRAIN[x-1][y+1] = b2;
			TERRAIN[x+1][y] = b2;
			TERRAIN[x-1][y] = b2;
			TERRAIN[x][y+1] = b2;
			TERRAIN[x][y-1] = b2;
			}else if(TERRAIN[x][y] == b3) {
			TERRAIN[x+1][y+1] = b3;
			TERRAIN[x-1][y-1] = b3;
			TERRAIN[x+1][y-1] = b3;
			TERRAIN[x-1][y+1] = b3;
			TERRAIN[x+1][y] = b3;
			TERRAIN[x-1][y] = b3;
			TERRAIN[x][y+1] = b3;
			TERRAIN[x][y-1] = b3;
			}
			}
		}


		for(int x = 0; x < SIZE-1; x++) {
			for(int y = 0; y < SIZE-1; y++) {
				BLOCKS[x][y] = 100;
			}
		}

		
		for(int j = 0; j < TREES; j++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			if(TERRAIN[x][y] == 0) {
			BLOCKS[x][y] = 3;
			}
		}

		for(int j = 0; j < TREES; j++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			if (TERRAIN[x][y] == 1) {
				BLOCKS[x][y] = 0;
			}

		}
		for(int j = 0; j < TREES; j++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			if (TERRAIN[x][y] == 2) {
				BLOCKS[x][y] = 2;
			}
		}



		for(int j = 0; j < ROCKS; j++) {
			int x = new Random().nextInt(SIZE);
			int y = new Random().nextInt(SIZE);
			if(TERRAIN[x][y] == 0) {
				BLOCKS[x][y] = 1;
				}
		}
		
	}

	public void addTile(int x, int y, int id){
		BLOCKS[x][y] = id;
		System.out.println("placed tile (id of: " + id + ") at position "+ x + ", " + y);
	}
	
	
	
	
}
