package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public int x;
	public int y;
	public int width, height;
	public int speed;
	public boolean upCollide, downCollide, leftCollide, rightCollide;
	int spriteCounter;
	BufferedImage up, down, left, right, up2, down2, left2, right2;
	String direction;
	public HitBox hitBox;
	public boolean collision = false;
	boolean changeSprite = false;

	abstract public void draw(Graphics2D g2);

	abstract public void update();

	abstract public void move();

}
