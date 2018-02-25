package com.neusoft.planewar.core;
/**
 * 血包
 * @author yanglinru
 *
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Images;

public class Item extends PlaneObject{
	
	 public PlaneWarSystem pws;
	 
	 public  int category;       //
	
	 
	 public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}


	private boolean live;   //存活生命
	 
	//初始化
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	
	
	
	
	
	public Item(int x,int y,PlaneWarSystem pws,int category){
		this.x=x;
		this.y=y;
		this.img = imgs[category];    //图片
		this.pws=pws;
		this.live=true;                    //存活
		this.category=category;  // 1:血块  2：飞机升级  3：全炸  4：生命+1
		
	}
	
	
	public Image[] imgs={
			Images.imgs.get("blood"),
			Images.imgs.get("blood2"),
			Images.imgs.get("zhadan"),
			Images.imgs.get("nengliang"),
			Images.imgs.get("myflylife")
	};
	
	
	
	
	public void draw(Graphics g){
		if(!live){     //如果不存活 
			pws.bloods.remove(this);     //移除血包
			return;
		}
		
		if(category==0){
			g.drawImage(Images.imgs.get("blood"), x, y, null);
		}
		if(category==1){
			g.drawImage(Images.imgs.get("blood2"), x, y, null);
		}
		if(category==2){
			g.drawImage(Images.imgs.get("zhadan"), x, y, null);
		}
		if(category==3){
			g.drawImage(Images.imgs.get("nengliang"), x, y, null);
		}
		if(category==4){
			g.drawImage(Images.imgs.get("myflylife"), x, y, null);
		}
		move();                    //调用move()方法
		
       //g.drawImage(img, x,y,null);        //否则画出血包
		
		
		
	}
	
	  //move()方法
	public void move(){
		y += 2;                     //血包的运动速度
	}
	
	
	/**
	 * 碰撞检测问题
	 * 获取当前飞机所在的矩形
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	

}
