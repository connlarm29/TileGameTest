package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TileGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int RENDER_DISTANCE = 5;

	public int mouseTileX;
	public int mouseTileY;
	
	JFrame window = new JFrame("TileGame");
	World world = new World();
	Player PLAYER = new Player(World.SPAWN_X, World.SPAWN_Y, world.BLOCKS);
	Renderer RENDER = new Renderer(world.terrain, world.natural_objects, PLAYER.sprites);

	Inventory INV = new Inventory();
	Interface GUI = new Interface(PLAYER.sprites);
	
	Timer GAME_TIMER = new Timer (10,this);
	
	public TileGame() {

		this.setSize(TileGame.WIDTH,TileGame.HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setVisible(true);
		
		window.setSize(TileGame.WIDTH+4*RENDER.tile_SizeX,TileGame.HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setResizable(false);
		window.add(this);
		window.setVisible(true);
		
		GAME_TIMER.start();
	}
	
		@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		RENDER.Update(world.TERRAIN, world.BLOCKS, PLAYER.X, PLAYER.Y, mouseTileX, mouseTileY);
		RENDER.draw(g);
		RENDER.drawInventory(g, INV);
		RENDER.drawHealthbar(g, PLAYER.getHealth());
	}
	
	

	public static void main(String[] args) {
		new TileGame();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		PLAYER.keyTyped(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
				
		PLAYER.keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		PLAYER.keyReleased(e);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int tileSizeX = TileGame.WIDTH/ (2*TileGame.RENDER_DISTANCE);
		int tileSizeY = TileGame.HEIGHT/ (2*TileGame.RENDER_DISTANCE);

		mouseTileX = (e.getX()/tileSizeX)+(PLAYER.X-TileGame.RENDER_DISTANCE);
		mouseTileY = (e.getY()/tileSizeY)+(PLAYER.Y-TileGame.RENDER_DISTANCE);

		System.out.println("MOUSE: " + mouseTileX + ", " + mouseTileY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		if(button == MouseEvent.BUTTON3){
			if(world.BLOCKS[mouseTileX][mouseTileY] != 100) {
				INV.add(world.BLOCKS[mouseTileX][mouseTileY]);
				world.addTile(mouseTileX, mouseTileY, 100);
			}


		}else if(button == MouseEvent.BUTTON1){
			world.addTile(mouseTileX, mouseTileY, 1 ) ;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
