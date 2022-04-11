package com.kihei.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		int[][] ob = new int[4][4];
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				ob[r][c] = HUD.board[r][c];
			}
		}
		
		
		if (key == KeyEvent.VK_D) {
			for(int r = 0; r < 4; r++) {
				ArrayList<Integer> nonZeroes = new ArrayList<Integer>();
				int[] tempRow = new int[4];
				
				// Find all non-zero values in the row
				for(int c = 0; c < 4; c++) {
					if (HUD.board[r][c] != 0) {
						nonZeroes.add(HUD.board[r][c]);
					}
				}
				
				if (nonZeroes.size() == 4 && nonZeroes.get(0) == nonZeroes.get(1) && nonZeroes.get(2) == nonZeroes.get(3)) {
					tempRow[2] = nonZeroes.get(0) * 2;
					tempRow[3] = nonZeroes.get(2) * 2;
				} else {
					// Find pair(s) and add
					int p = 0; int pA = 0; int pB = 0;
					for(int i = 1; i < nonZeroes.size(); i++) {
						if (nonZeroes.get(i).equals(nonZeroes.get(i-1))) {
							p = nonZeroes.get(i);
							pA = i-1;
							pB = i;
						}
					}
					if (p != 0) {
						nonZeroes.set(pB, p*2);
						nonZeroes.remove(pA);
						HUD.score += p*2;
					}
				
					// Move numbers to the right (and put this in tempRow)
					for(int i = nonZeroes.size() - 1; i >= 0; i--) {
						tempRow[i + (4 - nonZeroes.size())] = nonZeroes.get(i);
					}
				}
				
				// Set the actual board's row to the tempRow
				for(int i = 0; i < 4; i++) {
					HUD.board[r][i] = tempRow[i];
				}
			}
			
			nTile(ob);
			checkLoss();
		}
		
		if (key == KeyEvent.VK_A) {
			for(int r = 0; r < 4; r++) {
				ArrayList<Integer> nonZeroes = new ArrayList<Integer>();
				int[] tempRow = new int[4];
				
				// Find all non-zero values in the row
				for(int c = 0; c < 4; c++) {
					if (HUD.board[r][c] != 0) {
						nonZeroes.add(HUD.board[r][c]);
					}
				}
				
				if (nonZeroes.size() == 4 && nonZeroes.get(0) == nonZeroes.get(1) && nonZeroes.get(2) == nonZeroes.get(3)) {
					tempRow[0] = nonZeroes.get(0) * 2;
					tempRow[1] = nonZeroes.get(2) * 2;
				} else {
					// Find pair(s) and add
					int p = 0; int pA = 0; int pB = 0;
					for(int i = 1; i < nonZeroes.size(); i++) {
						if (nonZeroes.get(i).equals(nonZeroes.get(i-1))) {
							p = nonZeroes.get(i);
							pA = i-1;
							pB = i;
						}
					}
					if (p != 0) {
						nonZeroes.set(pA, p*2);
						nonZeroes.remove(pB);
						HUD.score += p*2;
					}
				
					// Move numbers to the left (and put this in tempRow)
					for(int i = 0; i < nonZeroes.size(); i++) {
						tempRow[i] = nonZeroes.get(i);
					}
				}
				
				// Set the actual board's row to the tempRow
				for(int i = 0; i < 4; i++) {
					HUD.board[r][i] = tempRow[i];
				}
			}
			
			nTile(ob);
			checkLoss();
		}
		
		if (key == KeyEvent.VK_S) {
			for(int c = 0; c < 4; c++) {
				ArrayList<Integer> nonZeroes = new ArrayList<Integer>();
				int[] tempRow = new int[4];
				
				// Find all non-zero values in the row
				for(int r = 0; r < 4; r++) {
					if (HUD.board[r][c] != 0) {
						nonZeroes.add(HUD.board[r][c]);
					}
				}
				
				if (nonZeroes.size() == 4 && nonZeroes.get(0) == nonZeroes.get(1) && nonZeroes.get(2) == nonZeroes.get(3)) {
					tempRow[2] = nonZeroes.get(0) * 2;
					tempRow[3] = nonZeroes.get(2) * 2;
				} else {
					// Find pair(s) and add
					int p = 0; int pA = 0; int pB = 0;
					for(int i = 1; i < nonZeroes.size(); i++) {
						if (nonZeroes.get(i).equals(nonZeroes.get(i-1))) {
							p = nonZeroes.get(i);
							pA = i-1;
							pB = i;
						}
					}
					if (p != 0) {
						nonZeroes.set(pB, p*2);
						nonZeroes.remove(pA);
						HUD.score += p*2;
					}
				
					// Move numbers to the right (and put this in tempRow)
					for(int i = nonZeroes.size() - 1; i >= 0; i--) {
						tempRow[i + (4 - nonZeroes.size())] = nonZeroes.get(i);
					}
				}
				
				// Set the actual board's row to the tempRow
				for(int i = 0; i < 4; i++) {
					HUD.board[i][c] = tempRow[i];
				}
			}
			
			nTile(ob);
			checkLoss();
		}
		
		if (key == KeyEvent.VK_W) {
			for(int c = 0; c < 4; c++) {
				ArrayList<Integer> nonZeroes = new ArrayList<Integer>();
				int[] tempRow = new int[4];
				
				// Find all non-zero values in the row
				for(int r = 0; r < 4; r++) {
					if (HUD.board[r][c] != 0) {
						nonZeroes.add(HUD.board[r][c]);
					}
				}
				
				if (nonZeroes.size() == 4 && nonZeroes.get(0) == nonZeroes.get(1) && nonZeroes.get(2) == nonZeroes.get(3)) {
					tempRow[0] = nonZeroes.get(0) * 2;
					tempRow[1] = nonZeroes.get(2) * 2;
				} else {
					// Find pair(s) and add
					int p = 0; int pA = 0; int pB = 0;
					for(int i = 1; i < nonZeroes.size(); i++) {
						if (nonZeroes.get(i).equals(nonZeroes.get(i-1))) {
							p = nonZeroes.get(i);
							pA = i-1;
							pB = i;
						}
					}
					if (p != 0) {
						nonZeroes.set(pA, p*2);
						nonZeroes.remove(pB);
						HUD.score += p*2;
					}
				
					// Move numbers to the left (and put this in tempRow)
					for(int i = 0; i < nonZeroes.size(); i++) {
						tempRow[i] = nonZeroes.get(i);
					}
				}
				
				// Set the actual board's row to the tempRow
				for(int i = 0; i < 4; i++) {
					HUD.board[i][c] = tempRow[i];
				}
			}
			
			nTile(ob);
			checkLoss();
		}
		
		if (key == KeyEvent.VK_R) {
			HUD.board = new int[4][4];
			Game.setup();
			HUD.score = 0;
			HUD.l = false;
		}
		// if (key == KeyEvent.VK_Q) System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getExtendedKeyCode();
	}
	
	private void nTile(int[][] oB) {
		if (!Arrays.deepEquals(oB, HUD.board)) { // if the board has changed after the key input
			try {
			    Thread.sleep(100);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			Game.newTile();
		}
	}
	
	private void checkLoss() {
		boolean nz = true; // no zeroes
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (HUD.board[r][c] == 0) {
					nz = false;
					return;
				}
			}
		}
		
		int ln = -1; // last number
		if (nz) { // if the board is full (no zeroes)
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if (HUD.board[r][c] == ln) {
						return;
					} else {
						ln = HUD.board[r][c];
					}
				}
				
				ln = -1;
			}
			
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 4; r++) {
					if (HUD.board[r][c] == ln) {
						return;
					} else {
						ln = HUD.board[r][c];
					}
				}
				
				ln = -1;
			}
			
			// at this point, pairs don't exist so it is a loss
			HUD.l = true;
		}
	}
	
}
