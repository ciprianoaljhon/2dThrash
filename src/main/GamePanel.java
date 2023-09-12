package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Entity.Player;
import Tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {

	public static final int ORIG_TILE_SIZE = 16;
	public static int scale = 3;
	public static final int TILE_SIZE = ORIG_TILE_SIZE * scale;
	public static final int SCREEN_COL = 16;
	public static final int SCREEN_ROW = 12;
	public static final int SCREEN_H = TILE_SIZE * SCREEN_ROW;
	public static final int SCREEN_W = TILE_SIZE * SCREEN_COL;
	// World Settings
	public static final int WORLD_COL = 50;
	public static final int WORLD_ROW = 50;
	public static final int WORLD_H = TILE_SIZE * WORLD_ROW;
	public static final int WORLD_W = TILE_SIZE * WORLD_COL;

	private final double FPS = 60;
	private Thread mThread;
	private Player player;
	TileManager tiles;
	JLabel label;
//	SuperObject sObj = new SuperObject();

	public GamePanel() {
		player = new Player();
		label = new JLabel("X: " + player.x + "\nY: " + player.y);
		label.setForeground(Color.white);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tiles = new TileManager(player);
		setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
		setFocusable(true);
		add(label);
		addKeyListener(new Controls());
		mThread = new Thread(this);
		mThread.start();
	}

	public void update() {
		label.setText(
				"X: " + player.x + "\nY: " + player.y + "\n C: " + player.x / TILE_SIZE + "R: " + player.y / TILE_SIZE);
		player.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < SCREEN_COL; i++) {
			g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, SCREEN_H);
		}
		for (int i = 0; i < SCREEN_ROW; i++) {
			g.drawLine(0, i * TILE_SIZE, SCREEN_W, i * TILE_SIZE);
		}
		Graphics2D g2 = (Graphics2D) g;
		tiles.draw(g2);
		player.draw(g2);
	}

	@Override
	public void run() {
		double drawInterval = 1e9 / FPS;
		double deltaTime = 0;
		double prev = System.nanoTime();
		double timer = 0;
		int loop = 0;
		double now;
		while (mThread != null) {

			now = System.nanoTime();
			deltaTime += (now - prev) / drawInterval;
			timer += now - prev;
			prev = now;
			if (deltaTime >= 1) {
				update();
				repaint();
				deltaTime--;
				loop++;
			}
			if (timer >= 1e9) {
				loop = 0;
				timer -= 1e9;
			}
		}

	}

}
