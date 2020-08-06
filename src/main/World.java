package main;


import java.util.Random;

public class World {

	private static final int TREES = 75000;
	private static final int ROCKS = 35000;
	private static final int LAKE_SIZE = 25000000;
	public static final int WORLD_SIZE = 1000;
	public static final int LAKE_COUNT = 10;
	public static final int SPAWN_X = 500;
	public static final int SPAWN_Y = 500;
	public static final int SNOW_THRESHOLD = 100;

	public int TERRAIN[][] = new int[WORLD_SIZE][WORLD_SIZE];
	public int BLOCKS[][] = new int[WORLD_SIZE][WORLD_SIZE];

	//Random randomValue = new Random();
	//int T = new Random().nextInt(500)+500;

	public World() {

		System.out.println("Aaaaaaand starting to generate le world!");

		//sets the world to a blank slate
		for(int i = 0; i < WORLD_SIZE; i++){
			for(int j = 0; j < WORLD_SIZE; j++){
				BLOCKS[i][j] = 100;
				TERRAIN[i][j] = Renderer.GRASS;
			}
		}

		System.out.println("Arrays cleared!");

		for(int i = 0; i < WORLD_SIZE; i++){
			for(int j = 0; j < SNOW_THRESHOLD; j++){

				TERRAIN[i][j] = Renderer.SNOW;
			}
		}

		for(int i = 0; i < WORLD_SIZE*10; i++) {

			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(SNOW_THRESHOLD/10)+SNOW_THRESHOLD;

			int id = Renderer.SNOW;

			try {
				if (TERRAIN[x][y + 1] == id || TERRAIN[x + 1][y] == id || TERRAIN[x][y - 1] == id || TERRAIN[x - 1][y] == id) {
					TERRAIN[x][y] = id;
				}
			}catch(Exception e){}

		}

		//Seeds lakes in the world
		for(int i = 0; i < LAKE_COUNT; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = Renderer.WATER;

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

		System.out.println("Splashed some water in there!");

		//Expands said lakes
		for(int i = 0; i < LAKE_SIZE; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = Renderer.WATER;

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

		System.out.println("Due to global warming, the lakes just got larger");

		//Places sand around the lakes' edge
		for(int i = 0; i < LAKE_SIZE; i++){
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			int id = Renderer.WATER;
			try {
				if (TERRAIN[x][y + 1] == id || TERRAIN[x + 1][y] == id || TERRAIN[x][y - 1] == id || TERRAIN[x - 1][y] == id) {
					if(TERRAIN[x][y] == Renderer.GRASS){
						TERRAIN[x][y] = Renderer.SAND;
					}
				}
			}catch(Exception e){}
		}

		System.out.println("Sprinkling some sand in there for final touches");

		//Adds trees because yes
		for(int j = 0; j < TREES; j++) {
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);
			if(TERRAIN[x][y] == Renderer.GRASS) {
			BLOCKS[x][y] = Renderer.GREEN_TREE;
			}else if(TERRAIN[x][y] == Renderer.SNOW) {
				BLOCKS[x][y] = Renderer.PINE_TREE;
			}
		}

		System.out.println("Planted #Teamtrees");

		//Could this be? a duplicate method to add rocks!?
		for(int j = 0; j < ROCKS; j++) {
			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);
			if(TERRAIN[x][y] == Renderer.GRASS || TERRAIN[x][y] == Renderer.SNOW) {
				BLOCKS[x][y] = Renderer.ROCK;
				}
		}

		for(int i = 0; i < TREES/2; i++){

			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(WORLD_SIZE);

			try{

				if(BLOCKS[x][y] == Renderer.GREEN_TREE){
					BLOCKS[x][y] = 100;
					TERRAIN[x][y] = Renderer.RIPPED_GRASS;
				}

			}catch(Exception e){}







		}

		System.out.println("Added some (Dwayne) rocks (Johnson)");

		System.out.println("OkieDokie! Dont mess this one up like we did here on earth!");


		for(int i = 0; i < WORLD_SIZE; i++){
			for(int j = 0; j < SNOW_THRESHOLD; j++){

				try {
					if(TERRAIN[i][j] == Renderer.WATER && (TERRAIN[i][j-10] == Renderer.SNOW || TERRAIN[i+10][j] == Renderer.SNOW || TERRAIN[i-10][j] == Renderer.SNOW)) {
						TERRAIN[i][j] = Renderer.ICE;
					}
				} catch (Exception e) {}
			}
		}

		for(int i = 0; i < LAKE_COUNT*5; i++){

			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(SNOW_THRESHOLD);

			int id = Renderer.ICE;
			if (TERRAIN[x][y] == Renderer.WATER && y <= SNOW_THRESHOLD) {

				try {
					TERRAIN[x][y + 1] = id;
					TERRAIN[x + 1][y + 1] = id;
					TERRAIN[x + 1][y] = id;
					TERRAIN[x + 1][y - 1] = id;
					TERRAIN[x][y - 1] = id;
					TERRAIN[x - 1][y - 1] = id;
					TERRAIN[x - 1][y] = id;
					TERRAIN[x - 1][y + 1] = id;
				} catch (Exception e) {}
			}
		}

		for(int i = 0; i < WORLD_SIZE*10; i++){

			int x = new Random().nextInt(WORLD_SIZE);
			int y = new Random().nextInt(SNOW_THRESHOLD);

			int id = Renderer.ICE;

			try {
				if (TERRAIN[x][y + 1] == id || TERRAIN[x + 1][y] == id || TERRAIN[x][y - 1] == id || TERRAIN[x - 1][y] == id) {
					TERRAIN[x][y] = id;
				}
			}catch(Exception e){}

		}

	}

	//Just a method to edit the world XD
	public void addTile(int x, int y, int id){
		BLOCKS[x][y] = id;
		System.out.println("placed tile (id of: " + id + ") at position "+ x + ", " + y);
	}

}
