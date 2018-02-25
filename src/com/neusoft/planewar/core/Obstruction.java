package com.neusoft.planewar.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * 障碍物类
 * 1、飞机不能飞过去，只能绕开
 * 2、飞机的子弹可以击毁障碍物（升级）
 * @author yanglinru
 *
 */
public class Obstruction extends PlaneObject{

	
	public PlaneWarSystem pws;
	int width;
	int height;
	
	public  int speed=Constant.GAME_BG_OB_SPEED;
	
	
	public Obstruction(PlaneWarSystem pws){
		this.x=100;
		this.y=-200;
		this.img=Images.imgs.get("e6");
		this.pws=pws;
		
	}
	
	
	public Obstruction(int x,int y,int width,int height,PlaneWarSystem pws){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.pws=pws;
		
	}
	
	public void draw(Graphics g){
		//g.drawImage(img, x, y,null);
		
      	move();  //调用move方法
		Color c=g.getColor();
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	
	
	public void move(){
		y += speed;
	}
	
	
	public  Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	
}
