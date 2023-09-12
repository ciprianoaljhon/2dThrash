package Entity;

import main.GamePanel;

public class HitBox {
	public int width, height;
	public int x;
	public int y;
	int cameraX;
	int cameraY;
	int reduceVal = 30;

	public HitBox(Entity entity) {
		this.width = 32;
		this.height = 32;
		this.x = entity.x + ((GamePanel.TILE_SIZE - width) / 2);
		this.y = entity.y + ((GamePanel.TILE_SIZE - height) / 2);
	}
}
