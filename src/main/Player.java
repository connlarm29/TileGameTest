package main;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Player {

	private static final int[] GROUND_ID = {2,4,5,9};
	
	private int health = 3;
	
	public int X;
	public int Y;
	private int SpawnX;
	private int SpawnY;
	private int velX = 0;
	private int velY = 0;
	
	BufferedImage sheet;
	BufferedImage WET;
	BufferedImage[] sprites = new BufferedImage[5];
	
	private int MAP[][];
	
	public Player(int x, int y, int m[][]) {
		X=x;
		Y=y;
		MAP=m;
		SpawnX = X;
		SpawnY = Y;
		
		
		try {
			sheet = ImageIO.read(this.getClass().getResource("/res/player.png"));
			WET =  ImageIO.read(this.getClass().getResource("/res/playerWET.png"));
			}catch(Exception e) {
				e.printStackTrace();
			}
		for(int A = 0; A < 4; A++) {
			sprites[A] = sheet.getSubimage(A*16,0,16,16);
		}
		sprites[4] =  WET.getSubimage(0,0,16,16);
	}
	
	
	
	public void move() {
	X += velX;
	Y += velY;
	}
	
	public void harm() {
		health--;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
			  if(key == e.VK_W) {
			velY = -1;
		}else if(key == e.VK_D) {
			velX = 1; 
		}else if(key == e.VK_A) {
			velX = -1;
		}else if(key == e.VK_S) {
			velY = 1;
		}
		
		if(X == MAP.length) {
			X = MAP.length;
		}
		if(X == 0) {
			X = 0;
		}
		if(Y == MAP.length) {
			Y = MAP.length;
		}
		if(Y == 0) {
			Y = 0;
		}
		if(MAP[X + velX][Y + velY] != 100) {
			velX = 0;
			velY = 0;
		}
		
		
		move();
		
	}
	public void keyReleased(KeyEvent e) {
	velX = 0;
	velY = 0;
	}
}

