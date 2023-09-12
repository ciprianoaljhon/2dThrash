package Tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Entity.Player;
import main.GamePanel;

public class TileManager {
	static public Tiles[] tile = new Tiles[6];
	static public int tilePos[][] = new int[GamePanel.WORLD_ROW][GamePanel.WORLD_COL];
	Player player;
	int tileSize = GamePanel.TILE_SIZE;

	public TileManager(Player player) {
		this.player = player;
		try {
			tile[0] = new Tiles();
			tile[0].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/grass01.png"));
			tile[1] = new Tiles();
			tile[1].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			tile[2] = new Tiles();
			tile[2].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/water01.png"));
			tile[2].collision = true;
			tile[3] = new Tiles();
			tile[3].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/earth.png"));
			tile[4] = new Tiles();
			tile[4].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			tile[5] = new Tiles();
			tile[5].image = ImageIO.read(Player.class.getResourceAsStream("/tiles/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadMap();
	}

	private void loadMap() {
		InputStream iStream = getClass().getResourceAsStream("/maps/map00.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
		String line;
		int row = 0;
		try {
			while ((line = br.readLine()) != null) {
				String[] nums = line.split(" ");
				for (int i = 0; i < nums.length; i++) {
					tilePos[row][i] = Integer.parseInt(nums[i]);
				}
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(tilePos[(0 + player.cameraY) / tileSize][(0 + player.cameraX) / tileSize]);
//		System.out.println(tilePos[275 / tileSize][1000 / tileSize]);
	}

	public void draw(Graphics2D g2) {

		for (int row = 0, col = 0; row <= GamePanel.WORLD_ROW - 1; col++) {
			BufferedImage currentTile = tile[tilePos[row][col]].image;
			int tileX = col * tileSize - (player.x - player.cameraX);
			int tileY = row * tileSize - (player.y - player.cameraY);

			int tile1 = tileX + (player.x - player.cameraX);
			int tile2 = tileY + (player.y - player.cameraY);
			if (player.x + player.cameraX > tile1 && player.y + player.cameraY > tile2
					&& player.x - player.cameraX - 48 < tile1 && player.y - player.cameraY - 48 < tile2) {
				g2.drawImage(currentTile, tileX, tileY, tileSize, tileSize, null);

			}
			if (col == GamePanel.WORLD_COL - 1) {
				col = -1;
				row++;
			}
		}
	}
}
