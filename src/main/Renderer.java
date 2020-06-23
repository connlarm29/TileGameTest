package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	private Image[] TEX;
	private BufferedImage[] TEX2;
	private BufferedImage[] PLAYER;

	private BufferedImage waterEffect;

	private int menuStart;

	//grabs the water effect because I was too lazy to add it earlier
	{
		try {
			waterEffect = ImageIO.read(this.getClass().getResource("/res/WetEffect.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Renderer(Image[] TILES, BufferedImage[] TILES2, BufferedImage[] player) {
		
		TEX = TILES;
		TEX2 = TILES2;
		PLAYER = player;
		
		RD = TileGame.RENDER_DISTANCE;
		tile_SizeX = TileGame.WIDTH/(RD*2);
		tile_SizeY = TileGame.HEIGHT/(RD*2);

		menuStart = TileGame.WIDTH+tile_SizeX;
	}
	
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
				if(!(i < 0 || i >= MAP.length-1 || j < 0 || j >= MAP.length-1) && MAP[i][j] != 100) {

					int currentX = (i - (PX - RD)) * tile_SizeX;
					int currentY = (j - (PY - RD)) * tile_SizeY;

					//Draws from the Terrain array
					g.drawImage(TEX[MAP[i][j]], currentX, currentY, tile_SizeX, tile_SizeY, null);

					//checks if there is a valid block on the terrain at that location
					if(BLOCKS[i][j] != 100) {
						//Draws the block
						g.drawImage(TEX2[BLOCKS[i][j]], currentX, currentY, tile_SizeX, tile_SizeY, null);

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
				if(MAP[PX][PY] == 3){
					g.drawImage(waterEffect, 0,0,menuStart,tile_SizeY*11,null);

				}
			}
			}
		}
	}

	public void drawGui(Graphics g, Inventory	 inv, int H) {

		//paints the background blah blah blah
		g.setColor(Color.DARK_GRAY);
		g.fillRect(menuStart, 0, 14*tile_SizeX, TileGame.HEIGHT+tile_SizeY);

		//Draws the healthbar, taken from another class
		int offset = 0;
		for(int i = 0; i < H; i++) {
			g.drawImage(PLAYER[1],menuStart+offset,0,tile_SizeX,tile_SizeY,null);
			offset += tile_SizeX/2;
		}

		//Draws the player image depending if they are "wet" ;}
		if(MAP[PX][PY] == 3){
			g.drawImage(PLAYER[4],5*tile_SizeX,5*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}else{
			g.drawImage(PLAYER[2],5*tile_SizeX,5*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}

		//Draws those weird little white lines around the inventory
		g.setColor(Color.WHITE);
		g.drawLine(menuStart, 0,menuStart,TileGame.HEIGHT+tile_SizeY);
		g.drawLine(menuStart, tile_SizeY, 14*tile_SizeX, tile_SizeY);


		//Broken inventory system, nothing to see here!
		g.drawString("inventory contains" + inv.toString(), menuStart, 2*tile_SizeY);

		//The kill button!
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("Press X to close.", 5, TileGame.HEIGHT+tile_SizeY-5);
	}
	
	
	
	
}
