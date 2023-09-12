package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Tiles.CollisionChecker;
import main.Controls;
import main.GamePanel;

public class Player extends Entity {
	BufferedImage image = null;
	public final int cameraX;
	public final int cameraY;

	public Player() {
		cameraX = GamePanel.SCREEN_W / 2;
		cameraY = GamePanel.SCREEN_H / 2;
		setDefault();
		getPlayerImage();
		image = down;
	}

	public void getPlayerImage() {
		try {
			up = ImageIO.read(Player.class.getResourceAsStream("/player/boy_up_1.png"));
			down = ImageIO.read(Player.class.getResourceAsStream("/player/boy_down_1.png"));
			left = ImageIO.read(Player.class.getResourceAsStream("/player/boy_left_1.png"));
			right = ImageIO.read(Player.class.getResourceAsStream("/player/boy_right_1.png"));
			up2 = ImageIO.read(Player.class.getResourceAsStream("/player/boy_up_2.png"));
			down2 = ImageIO.read(Player.class.getResourceAsStream("/player/boy_down_2.png"));
			left2 = ImageIO.read(Player.class.getResourceAsStream("/player/boy_left_2.png"));
			right2 = ImageIO.read(Player.class.getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
		}
	}

	public void setDefault() {

		height = GamePanel.TILE_SIZE;
		width = GamePanel.TILE_SIZE;
		x = 480;
		y = 385;

		speed += 5;
	}

	@Override
	public void update() {
		hitBox = new HitBox(this);
		move();
	}

	@Override
	public void move() {
		upCollide = leftCollide = downCollide = rightCollide = false;
		CollisionChecker.checkCollision(this);
		if (Controls.up && !upCollide) {
			y -= speed;
		}
		if (Controls.down && !downCollide) {
			y += speed;
		}
		if (Controls.right && !rightCollide) {
			x += speed;
		}
		if (Controls.left && !leftCollide) {
			x -= speed;
		}
		spriteCounter++;
		if (spriteCounter > 5) {
			changeSprite = !changeSprite;
			spriteCounter = 0;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (Controls.up) {
			image = changeSprite ? up : up2;
			Controls.down = false;
		}
		if (Controls.down) {
			image = changeSprite ? down : down2;
			Controls.up = false;
		}
		if (Controls.right) {
			image = changeSprite ? right : right2;
			Controls.left = false;
		}
		if (Controls.left) {
			image = changeSprite ? left : left2;
			Controls.right = false;
		}
		g2.drawImage(image, cameraX, cameraY, width, height, null);
		g2.setColor(Color.red);
		g2.drawRect(cameraX + ((GamePanel.TILE_SIZE - hitBox.width) / 2),
				cameraY + ((GamePanel.TILE_SIZE - height) / 2), hitBox.width, hitBox.height);
	}

}
