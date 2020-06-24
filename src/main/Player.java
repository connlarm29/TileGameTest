package main;

import java.awt.event.KeyEvent;

public class Player {

	private static final int[] GROUND_ID = {2,4,5,9};
	
	private int health = 3;
	
	public int X;
	public int Y;
	public int selection = 8;
	public boolean isHurting = false;

	private int SpawnX;
	private int SpawnY;
	private int velX = 0;
	private int velY = 0;
	
	private int MAP[][];
	
	public Player(int x, int y, int m[][]) {
		X=x;
		Y=y;
		MAP=m;
		SpawnX = X;
		SpawnY = Y;

		}
	
	
	
	public void move() {
	X += velX;
	Y += velY;
	}
	
	public void harm() {
		health--;
		isHurting = true;

		System.out.println("Abused the player!");
	}
	
	public int getHealth() {
		return health;
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();


		if(key == e.VK_Q && selection > 8){

			selection--;

		}

		if(key == e.VK_E && selection < 13){

			selection++;

		}

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

		if(MAP[X + velX][Y + velY] == Renderer.SPIKES) {
			harm();
			velX = 0;
			velY = 0;
		}

		if(X+velX >= World.WORLD_SIZE || X+velX < 0 || Y+velY >= World.WORLD_SIZE || Y+velY < 0 || Renderer.solidTiles.contains(MAP[X + velX][Y + velY])) {
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

