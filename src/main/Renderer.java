package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer {

	int RD;
	int tile_SizeX;
	int tile_SizeY;
	int PX;
	int PY;
	int MX;
	int MY;
	int MAP[][];
	int BLOCKS[][];
	private Image[] TEX = new Image[51];
	private BufferedImage tileSheet;
	ImageIcon animWater = new ImageIcon(this.getClass().getResource("/res/WATER.gif"));
	private BufferedImage waterEffect;
	private int menuStart;


	public static final int GRASS = 0;
	public static final int SAND = 1;
	public static final int SNOW = 2;
	public static final int RIPPED_GRASS = 3;
	public static final int CACTUS = 4;
	public static final int ROCK = 5;
	public static final int PINE_TREE = 6;
	public static final int GREEN_TREE = 7;
	public static final int WOOD = 8;
	public static final int SPIKES = 9;
	public static final int TABLE = 10;
	public static final int RED_BRICKS = 11;
	public static final int CANDLE = 12;
	public static final int BLUE_BRICKS = 13;
	public static final int EMPTY_HEART = 14;
	public static final int FULL_HEART = 15;
	public static final int PLAYER_ALIVE = 16;
	public static final int PLAYER_DEAD = 17;
	public static final int PLAYER_WET = 18;
	public static final int WATER = 50;
	public static final int ICE = 19;

	public static final ArrayList<Integer> solidTiles = new ArrayList<Integer>();




	//Loads all the images from a sprite sheet and puts them where they belong
	{
	try {
			//gets the main tilesheet and feeds it into an array
			tileSheet = ImageIO.read(this.getClass().getResource("/res/Tilemapv1.png"));
		for(int A = 0; A < 20; A++) {
			TEX[A] = tileSheet.getSubimage(A*16,0,16,16);
		}
		//Grabs the animated water and screen splash effect for later use
		TEX[WATER] = animWater.getImage();
		waterEffect = ImageIO.read(this.getClass().getResource("/res/WetEffect.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Renderer() {

		solidTiles.add(CACTUS);
		solidTiles.add(ROCK);
		solidTiles.add(PINE_TREE);
		solidTiles.add(GREEN_TREE);
		solidTiles.add(SPIKES);
		solidTiles.add(TABLE);
		solidTiles.add(RED_BRICKS);
		solidTiles.add(CANDLE);

		//Just getting some variables down for later :P
		RD = TileGame.RENDER_DISTANCE;
		tile_SizeX = TileGame.WIDTH/(RD*2);
		tile_SizeY = TileGame.HEIGHT/(RD*2);
		menuStart = TileGame.WIDTH+tile_SizeX;
	}

	//Updates the rendering engine with world data
	public void Update(int map[][], int blocks[][], int px, int py, int mx, int my) {
		PX = px;
		PY = py;

		MX = mx;
		MY = my;
		MAP = map;
		BLOCKS = blocks;
	}


	/*
	OH BOY
	note to future self:
	you really need to rewrite this entire class, its just too buggy and painful to look at
	 */

	public void draw(Graphics g) {
		//iterates through Rows
		for(int i = PX-RD; i < PX+RD+1; i++ ) {
			//iterates through Columns
			for(int j = (PY-RD); j < (PY+RD+1); j++ ) {
				//Checks if the tile is valid
				if(!(i < 0 || i >= MAP.length || j < 0 || j >= MAP.length) && MAP[i][j] != 100) {

					int currentX = (i - (PX - RD)) * tile_SizeX;
					int currentY = (j - (PY - RD)) * tile_SizeY;

					//Draws from the Terrain array
					g.drawImage(TEX[MAP[i][j]], currentX, currentY, tile_SizeX, tile_SizeY, null);

					//checks if there is a valid block on the terrain at that location
					if(BLOCKS[i][j] != 100) {
						//Draws the block
						g.drawImage(TEX[BLOCKS[i][j]], currentX, currentY, tile_SizeX, tile_SizeY, null);

					}

				//Draws cursor over tiles
				if(i == MX && j == MY){
					//Creates new Graphics2D object
					Graphics2D g2 = (Graphics2D) g;
					//Makes it look sexy n stuff
					g2.setColor(Color.RED);
					g2.setStroke(new BasicStroke(5));

					//Draws cursor box
					g2.drawRect(currentX, currentY, tile_SizeX, tile_SizeY);
				}

				//Draws water effects to screen
				if(MAP[PX][PY] == Renderer.WATER){
					g.drawImage(waterEffect, 0,0,menuStart,tile_SizeY*11,null);

				}
			}
			}
		}
	}

	public void drawGui(Graphics g, Inventory	 inv, int H, int selection) {

		//paints the background blah blah blah
		g.setColor(Color.DARK_GRAY);
		g.fillRect(menuStart, 0, 14*tile_SizeX, TileGame.HEIGHT+tile_SizeY);

		//Draws the healthbar, taken from another class
		int offset = 0;
		for(int i = 0; i < H; i++) {
			g.drawImage(TEX[Renderer.FULL_HEART],menuStart+offset,0,tile_SizeX,tile_SizeY,null);
			offset += tile_SizeX/2;
		}

		//Draws the player image depending if they are dead or wet
		if(MAP[PX][PY] == Renderer.WATER){
			g.drawImage(TEX[Renderer.PLAYER_WET],TileGame.RENDER_DISTANCE*tile_SizeX,TileGame.RENDER_DISTANCE*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}else if(H <= 0){
			g.drawImage(TEX[Renderer.PLAYER_DEAD],TileGame.RENDER_DISTANCE*tile_SizeX,TileGame.RENDER_DISTANCE*tile_SizeY,tile_SizeX,tile_SizeY,null);

			Graphics2D g2d = (Graphics2D) g;
			Color hurtSplash = new Color(199, 17, 17, 144);
			g2d.setColor(hurtSplash);
			g2d.fillRect(0, 0, TileGame.WIDTH+tile_SizeX, TileGame.HEIGHT+tile_SizeY);

			g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
			g.setColor(Color.WHITE);
			g.drawString("Buckaroo you just bit the dust.", 5, 55);

		}else{
			g.drawImage(TEX[Renderer.PLAYER_ALIVE],TileGame.RENDER_DISTANCE*tile_SizeX,TileGame.RENDER_DISTANCE*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}

		//Draws those weird little white lines around the inventory
		g.setColor(Color.WHITE);
		g.drawLine(menuStart, 0,menuStart,TileGame.HEIGHT+tile_SizeY);
		g.drawLine(menuStart, tile_SizeY, menuStart+(tile_SizeX*4), tile_SizeY);


		//Broken inventory system, nothing to see here!
		//g.drawString("inventory contains" + inv.toString(), menuStart, 2*tile_SizeY);

		//The kill button!
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString("Press X to close // Use Q and E to change block selection", 5, TileGame.HEIGHT+tile_SizeY-5);

		//Draws current selection
		g.drawImage(TEX[selection], menuStart+(tile_SizeX*2)+(tile_SizeX/2), TileGame.HEIGHT+(tile_SizeY/2), tile_SizeX/2, tile_SizeY/2, null);
	}
	
	
	
	
}
