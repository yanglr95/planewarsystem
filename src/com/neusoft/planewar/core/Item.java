package com.neusoft.planewar.core;
/**
 * Ѫ��
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


	private boolean live;   //�������
	 
	//��ʼ��
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	
	
	
	
	
	public Item(int x,int y,PlaneWarSystem pws,int category){
		this.x=x;
		this.y=y;
		this.img = imgs[category];    //ͼƬ
		this.pws=pws;
		this.live=true;                    //���
		this.category=category;  // 1:Ѫ��  2���ɻ�����  3��ȫը  4������+1
		
	}
	
	
	public Image[] imgs={
			Images.imgs.get("blood"),
			Images.imgs.get("blood2"),
			Images.imgs.get("zhadan"),
			Images.imgs.get("nengliang"),
			Images.imgs.get("myflylife")
	};
	
	
	
	
	public void draw(Graphics g){
		if(!live){     //�������� 
			pws.bloods.remove(this);     //�Ƴ�Ѫ��
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
		move();                    //����move()����
		
       //g.drawImage(img, x,y,null);        //���򻭳�Ѫ��
		
		
		
	}
	
	  //move()����
	public void move(){
		y += 2;                     //Ѫ�����˶��ٶ�
	}
	
	
	/**
	 * ��ײ�������
	 * ��ȡ��ǰ�ɻ����ڵľ���
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	

}
