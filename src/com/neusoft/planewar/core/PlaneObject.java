package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 飞机项目中与飞机相关的物体的父类
 * @author yanglinru
 *导包快捷键 ctrl+shift+o
 */
public class PlaneObject implements Drawable,Moveable{
     
	public int x;
	public int y;
	public Image img;
	
	public void move(){
		//由于每个物体运动轨迹不同，所以子类需要重写
	} 
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
}
