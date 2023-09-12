package Tiles;

import Entity.Entity;
import main.Controls;
import main.GamePanel;

public class CollisionChecker {
	static int tileSize = GamePanel.TILE_SIZE;
	static Tiles[] tile = TileManager.tile;

	public static void checkCollision(Entity entity) {
		int up = entity.hitBox.y;
		int down = entity.hitBox.y + entity.hitBox.height;
		int left = entity.hitBox.x;
		int right = entity.hitBox.x + entity.hitBox.width;

		int upCol = up / GamePanel.TILE_SIZE;
		int downCol = down / GamePanel.TILE_SIZE;
		int rightCol = right / GamePanel.TILE_SIZE;
		int leftCol = left / GamePanel.TILE_SIZE;
		if (Controls.up) {
			entity.upCollide = tile[TileManager.tilePos[(up - entity.speed) / GamePanel.TILE_SIZE][rightCol]].collision
					|| tile[TileManager.tilePos[(up - entity.speed) / GamePanel.TILE_SIZE][leftCol]].collision;
		}
		if (Controls.down) {
			entity.downCollide = tile[TileManager.tilePos[(down + entity.speed)
					/ GamePanel.TILE_SIZE][rightCol]].collision
					|| tile[TileManager.tilePos[(down + entity.speed) / GamePanel.TILE_SIZE][leftCol]].collision;
		}
		if (Controls.right) {
			entity.rightCollide = tile[TileManager.tilePos[upCol][(right + entity.speed)
					/ GamePanel.TILE_SIZE]].collision
					|| tile[TileManager.tilePos[downCol][(right + entity.speed) / GamePanel.TILE_SIZE]].collision;
		}
		if (Controls.left) {
			entity.leftCollide = tile[TileManager.tilePos[upCol][(left - entity.speed) / GamePanel.TILE_SIZE]].collision
					|| tile[TileManager.tilePos[downCol][(left - entity.speed) / GamePanel.TILE_SIZE]].collision;
		}
	}
}
