package com.VanshitaG.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite{
	
	public Player(){
		w=100;
		h=200;
		x=20;
		y=550;
		image=new ImageIcon(Player.class.getResource("heroboy.gif")) ;
		
	}
	
	public void move() {
		x=x+speed;
	}
	public boolean outOfScreen() {
		return x>1400;
		
		
	}

}
