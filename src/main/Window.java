package main;

import javax.swing.JFrame;

public class Window extends JFrame {
	private GamePanel gamePanel = new GamePanel();

	public static void main(String[] args) {
		Window frame = new Window();
		frame.setVisible(true);
	}

	Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(gamePanel);
		setTitle("2D Game");
		pack();
		setLocationRelativeTo(null);
	}

}
