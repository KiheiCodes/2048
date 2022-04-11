package com.kihei.main;

import java.awt.*;

public class HUD {
	
	public static int[][] board = new int[4][4];
	public static int score = 0;
	public static int best = 0;
	public static int ht = 0;
	public static boolean l = false; // lost
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int width, int height) {
		Color dark = new Color(192,172,164);
		Color bg = new Color(208,196,180);
		
		int size = 500;
		g.setColor(dark);
		g.fillRect((width - size) / 2, (height - size) / 2, size, size); // outline
		g.setColor(bg);
		
		size -= 20;
		int left = (width - size) / 2;
		int top = (height - size) / 2;
		g.fillRect(left, top, size, size); // actual box
		
		int currentX = left;
		int currentY = top;
		
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				if (board[r][c] != 0) {
					g.setColor(new Color(186, 178, 171));
					g.fillRect(currentX, currentY, 120, 120); // tile outline
					g.setColor(getTileColor(board[r][c])); // get tile color based on number
					g.fillRect(currentX+5, currentY+5, 110, 110); // actual tile
					
					// white text for 2 and 4, black otherwise
					if (board[r][c] == 2 || board[r][c] == 4) {
						g.setColor(new Color(120,108,100));
					} else {
						g.setColor(new Color(249, 246, 242));
					}
					
					// centers number based on rectangle dimensions
					cStr(g, Integer.toString(board[r][c]), currentX+5, currentY+5, 110, 110, new Font(g.getFont().getFontName(), Font.PLAIN, 60));
				}
				currentX += 120;
				
				if (board[r][c] > ht)
					ht = board[r][c];
			}
			currentY += 120;
			currentX = left;
		}
		
		if (l) {
			g.setColor(Color.black);
		} else {
			g.setColor(new Color(255, 252, 236));
		}
		
		cStr(g, "You lost! Press r to play again.", 0, 290, 800, 800, new Font(g.getFont().getFontName(), Font.PLAIN, 40));
		
		g.setColor(Color.black);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
		g.drawString("Score: " + score, 20, 30);
		if (score > best)
			best = score;
		g.drawString("Best: " + best, 20, 60);
		g.drawString("Highest Tile: " + ht, 20, 90);
		
		
		g.drawString("FPS: " + Game.framesDisplay, width - 120, 30);
	}
	
	// center string
	private void cStr(Graphics g, String text, int rectX, int rectY, int rectW, int rectH, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rectX + (rectW - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rectY + ((rectH - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
	
	private Color getTileColor(int num) {
		Color c = new Color(160, 50, 50);
		
		switch (num) {
		case 2:
			c = new Color(238, 228, 218);
			break;
		case 4:
			c = new Color(241, 222, 199);
			break;
		case 8:
			c = new Color(249, 179, 118);
			break;
		case 16:
			c = new Color(254, 154, 97);
			break;
		case 32:
			c = new Color(255, 133, 94);
			break;
		case 64:
			c = new Color(255, 106, 58);
			break;
		case 128:
			c = new Color(242, 203, 112);
			break;
		case 256:
			c = new Color(242, 199, 92);
			break;
		case 512:
			c = new Color(242, 192, 61);
			break;
		case 1024:
			c = new Color(242, 189, 51);
			break;
		case 2048:
			c = new Color(242, 183, 47);
			break;
		}
		
		return c;
	}
	
}
