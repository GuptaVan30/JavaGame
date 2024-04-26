package com.VanshitaG.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.VanshitaG.gaming.sprites.Enemy;
import com.VanshitaG.gaming.sprites.Player;

public class Board extends JPanel { // j component abstarct class -use as inheritance by jpanelImge
	Timer timer;
	
	BufferedImage BackgroundImage;
	Player player;
	Enemy enemies[]=new Enemy[4];
	public Board () {  //constructor
		
		
		setSize(1400,790);
		loadBackgroundImage();
		player=new Player();
		loadEnemies();
		gameLoop();
		bindEvents();
		setFocusable(true);
		
		
	}
	
	private void gameOver(Graphics pen) {
		
		if(player.outOfScreen()) {
			
			pen.setFont(new Font("arial", Font.BOLD, 50));
			pen.setColor(Color.WHITE);
			pen.drawString("Game Win",1400/2,790/2);
			timer.stop();
			return;
			
			
		}
		for(Enemy enemy:enemies) {
			if(isCollide(enemy)) {
				pen.setFont(new Font("arial", Font.BOLD, 50));
				pen.setColor(Color.WHITE);
				pen.drawString("Game Over",1500/2,790/2);
				timer.stop();
			}
		}
	}
	
	private boolean isCollide(Enemy enemy) {
		
		int xDistance=Math.abs(player.x-enemy.x);
		int yDistance=Math.abs(player.y-enemy.y);
		int maxH=Math.max(player.h,enemy.h);
		int maxW=Math.max(player.w,enemy.w);
		
		return xDistance<=maxW-160 && yDistance<=maxH-70;
		
	}
	private void bindEvents() {
		addKeyListener(new KeyListener(){
			
			public void keyReleased(KeyEvent e) {
				
				player.speed=0;
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				
				player.speed=10;
			}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					player.speed=-10;
				}
				
				
			}
			
		});
		
	}
	
	private void loadEnemies() {
		
		int x=400;
		int gap=250;
		int speed=5;
		
		for(int i=0;i<enemies.length;i++) {
			enemies[i]=new Enemy(x,speed);
			x=x+gap;
			speed=speed+5;
		}
	}
	
	private void gameLoop() {
		timer=new Timer(50,(e)->repaint());
		timer.start();
		
	}
	
	
	//jab bhi board pehli bar call hoga internally vo khud paintcomp ko bulayega khud first time
	private void loadBackgroundImage() {
		
		try {
			BackgroundImage=ImageIO.read(Board.class.getResource("g2.jpg"));
		}catch(IOException e){
			System.out.println("BackGround Image not found...");
			System.exit(1);
			
			e.printStackTrace();
			
		}
		
	}
	
	private void printEnemies(Graphics pen) {
		for(Enemy enemy:enemies) {
			enemy.draw(pen);
			enemy.move();
		}
	}
	
	@Override
	public void paintComponent(Graphics pen) {  //pen reference variable  ,graphics class
		
		super.paintComponent(pen); //clean up
		
		//all printing logic  here
		pen.drawImage(BackgroundImage,0,0,1500,950,null);
		player.draw(pen);
		player.move();
		printEnemies(pen);
		gameOver(pen);
		
	}

}
