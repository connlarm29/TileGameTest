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

	private BufferedImage WaterEffect;

	{
		try {
			WaterEffect = ImageIO.read(this.getClass().getResource("/res/WetEffect.png"));
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
	}
	
	public void Update(int map[][], int blocks[][], int px, int py, int mx, int my) {
		PX = px;
		PY = py;

		MX = mx;
		MY = my;
		MAP = map;
		BLOCKS = blocks;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, TileGame.WIDTH+4*tile_SizeX, TileGame.HEIGHT);
		
		for(int i = PX-RD; i < PX+RD+1; i++ ) {
			for(int j = (PY-RD); j < (PY+RD); j++ ) {
				
			if(i < 0 || i >= MAP.length-1 || j < 0 || j >= MAP.length-1) {
				g.fillRect((i-(PX-RD))*tile_SizeX, (j-(PY-RD))*tile_SizeY, tile_SizeX, tile_SizeY);
				}else{
					g.drawImage(TEX[MAP[i][j]], (i - (PX - RD)) * tile_SizeX, (j - (PY - RD)) * tile_SizeY, tile_SizeX, tile_SizeY, null);
					if(BLOCKS[i][j] != 100) {
						g.drawImage(TEX2[BLOCKS[i][j]], (i - (PX - RD)) * tile_SizeX, (j - (PY - RD)) * tile_SizeY, tile_SizeX, tile_SizeY, null);
					}

				if(i == MX && j == MY){

					Graphics2D g2 = (Graphics2D) g;
					g2.setColor(Color.RED);
					g2.setStroke(new BasicStroke(5));
					g2.drawRect((i-(PX-RD))*tile_SizeX, (j-(PY-RD))*tile_SizeY, tile_SizeX, tile_SizeY);
				}

				if(MAP[PX][PY] == 3){
					g.drawImage(WaterEffect, 0,0,tile_SizeX*11,tile_SizeY*10,null);

				}
			}
				
			}
		}


		
	}
	public void drawHealthbar(Graphics g, int H) {
		int offset = 0;
		for(int i = 0; i < H; i++) {
			g.drawImage(PLAYER[1],11*tile_SizeX+offset,0,tile_SizeX,tile_SizeY,null);
			offset += tile_SizeX/2;
		}

	}

	public void drawInventory(Graphics g, Inventory inv) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(11*tile_SizeX, 0, 14*tile_SizeX, TileGame.HEIGHT);

		if(MAP[PX][PY] == 3){
			g.drawImage(PLAYER[4],5*tile_SizeX,5*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}else{
			g.drawImage(PLAYER[2],5*tile_SizeX,5*tile_SizeY,tile_SizeX,tile_SizeY,null);
		}
		g.setColor(Color.WHITE);
		g.drawLine(11*tile_SizeX, 0,11*tile_SizeX,TileGame.HEIGHT);
		g.drawLine(11*tile_SizeX, tile_SizeY, 14*tile_SizeX, tile_SizeY);



		g.drawString("inventory contains" + inv.toString(),11*tile_SizeX, 2*tile_SizeY);



	}
	
	
	
	
}
