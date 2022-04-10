package com.kihei.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4995321819197236202L;

	public static final int WIDTH = 800, HEIGHT = 800;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	
	private final Window w;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		hud = new HUD();
		
		w = new Window(WIDTH, HEIGHT, "2048", this);
		
		setup();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join(); // stopping the thread
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int framesDisplay = 0;
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.00;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				framesDisplay = frames;
				frames = 0;
			}
		}
	}
	
	private void tick() {
		hud.tick();
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(255, 252, 236));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		int width = w.frame.getContentPane().getWidth();
		int height = w.frame.getContentPane().getHeight();
		hud.render(g, width, height);
		
		g.dispose();
		bs.show();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(255, 252, 236));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		int width = w.frame.getContentPane().getWidth();
		int height = w.frame.getContentPane().getHeight();
		hud.render(g, width, height);
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static int rTile() {
		double r = Math.random();
		if (r < 0.9)
			return 2;
		else
			return 4;
	}
	
	public static void setup() {
		// (Generates 2 random squares)
		HUD.board[(int)(Math.random() * 4)][(int)(Math.random() * 4)] = rTile(); // Set the first tile's random position
		newTile();
	}
	
	public static void newTile() {
		ArrayList<Integer> avR = new ArrayList<Integer>();
		ArrayList<Integer> avC = new ArrayList<Integer>();
		
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				if (HUD.board[r][c] == 0) {
					avR.add(r);
					avC.add(c);
				}
			}
		}
		
		int r = (int)(Math.random() * avR.size());
		HUD.board[avR.get(r)][avC.get(r)] = rTile();
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
}
