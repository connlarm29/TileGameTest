package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Interface {
	
	
	BufferedImage[] TEX;
	
	int RD;
	int tile_SizeX;
	int tile_SizeY;
	

	
	public Interface(BufferedImage[] TILES) {
		TEX = TILES;

		RD = TileGame.RENDER_DISTANCE;
		tile_SizeX = TileGame.WIDTH/(RD*2);
		tile_SizeY = TileGame.HEIGHT/(RD*2);
	}
	
	public void drawHealthbar(Graphics g, int H) {
		int offset = 0;
		for(int i = 0; i < H; i++) {
			g.drawImage(TEX[1],11*tile_SizeX+offset,0,tile_SizeX,tile_SizeY,null);
			offset += tile_SizeX/2;
		}	
		
	}

	public void drawInventory(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(11*tile_SizeX, 0, 14*tile_SizeX, TileGame.HEIGHT);
		g.drawImage(TEX[2],5*tile_SizeX,5*tile_SizeY,tile_SizeX,tile_SizeY,null);
		g.setColor(Color.WHITE);
		g.drawLine(11*tile_SizeX, 0,11*tile_SizeX,TileGame.HEIGHT);
		g.drawLine(11*tile_SizeX, tile_SizeY, 14*tile_SizeX, tile_SizeY);	
		
		
	
	}
}
