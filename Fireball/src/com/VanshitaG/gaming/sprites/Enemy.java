package com.VanshitaG.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Sprite {
	
	public Enemy(int x,int speed) {
		//loacal var
		
		this.speed=speed;
		y=20;
		this.x=x; //parent se
		w=200;
		h=200;
		image=new ImageIcon(Enemy.class.getResource("gif2.gif"));
		
	}
	
	public void move() {
		
		if(y>950) {
			y=0;
		}
		y=y+speed;
	}
	

}
