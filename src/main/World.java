package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	 * 4 SPIKES
	 * 5 TABLE
	 * 6 RED BRICKS
	 * 7 CANDLE
	 * 8 BLUE BRICKS
	 */

	private static final int TREES = 75000;
	private static final int ROCKS = 35000;
	private static final int BIOME_SIZE = 2500000;


	public static final int WORLD_SIZE = 1000;
	public static final int SEED_COUNT = 500;
	public static final int SPAWN_X = 500;
	public static final int SPAWN_Y = 500;
	BufferedImage sheet1;
	Image[] terrain = new Image[4];
	BufferedImage sheet2;
	BufferedImage sheet3;
	BufferedImage[] objects = new BufferedImage[9];
	ImageIcon water = new ImageIcon(this.getClass().getResource("/res/WATER.gif"));
	public int TERRAIN[][] = new int[WORLD_SIZE][WORLD_SIZE];
	public int BLOCKS[][] = new int[WORLD_SIZE][WORLD_SIZE];

	Random randomValue = new Random();
	int T = new Random().nextInt(500)+500;

	public World() {
		
		try {
			sheet1 = ImageIO.read(this.getClass().getResource("/res/TERRAIN2.png"));
			sheet2 = ImageIO.read(this.getClass().getResource("/res/TERRAIN3.png"));
			sheet3 = ImageIO.read(this.getClass().getResource("/res/BLOCKS.png"));
			//water =  ImageIO.read(this.getClass().getResource("/res/WATER.gif"));
			}catch(Exception e) {
				e.printStackTrace();
			}
		for(int A = 0; A < 4; A++) {
			terrain[A] = sheet1.getSubimage(A*16,0,16,16);
		}
			terrain[3] = water.getImage();
		for(int A = 0; A < 4; A++) {
			objects[A] = sheet2.getSubimage(A*16,0,16,16);
		}
		for(int A = 4; A < 9; A++) {
			objects[A] = sheet3.getSubimage((A-4)*16,0,16,16);
		}



		//Seed the world
//		for(int i = 0; i <= SEED_COUNT; i++){
//			int x = new Random().nextInt(WORLD_SIZE);
//			int y = new Random().nextInt(WORLD_SIZE);
//			TERRAIN[x][y] = new Random().nextInt(4);
//		}

		for(int i = 0; i < WORLD_SIZE; i++){
			for(int j = 0; j < WORLD_SIZE; j++){
				BLOCKS[i][j] = 100;
				TERRAIN[i][j] = 0;
			}
		}


		for(int i = 0; i < SEED_COUNT; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = 3;

			try {
				TERRAIN[x][y] = id;
				TERRAIN[x][y + 1] = id;
				TERRAIN[x + 1][y + 1] = id;
				TERRAIN[x + 1][y] = id;
				TERRAIN[x + 1][y - 1] = id;
				TERRAIN[x][y - 1] = id;
				TERRAIN[x - 1][y - 1] = id;
				TERRAIN[x - 1][y] = id;
				TERRAIN[x - 1][y + 1] = id;
			}catch(Exception e){}
		}

		for(int i = 0; i < BIOME_SIZE; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = 3;

			if (TERRAIN[x][y] == id) {
				try {
					TERRAIN[x][y + 1] = id;
					TERRAIN[x + 1][y + 1] = id;
					TERRAIN[x + 1][y] = id;
					TERRAIN[x + 1][y - 1] = id;
					TERRAIN[x][y - 1] = id;
					TERRAIN[x - 1][y - 1] = id;
					TERRAIN[x - 1][y] = id;
					TERRAIN[x - 1][y + 1] = id;
				} catch (Exception e) {
				}
			}
		}


		for(int i = 0; i < BIOME_SIZE; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = 3;
			try {
				if (TERRAIN[x][y + 1] == id || TERRAIN[x + 1][y] == id || TERRAIN[x][y - 1] == id || TERRAIN[x - 1][y] == id) {
					if(TERRAIN[x][y] == 0){
						TERRAIN[x][y] = 1;
					}
				}
			}catch(Exception e){}
		}

		for(int j = 0; j < TREES; j++) {
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);
			if(TERRAIN[x][y] == 0) {
			BLOCKS[x][y] = 3;
			}
		}

		for(int j = 0; j < ROCKS; j++) {
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);
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
