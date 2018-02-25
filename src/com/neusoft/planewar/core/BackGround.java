package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * 滚动的背景图片
 * @author yanglinru
 *
 */
public class BackGround extends PlaneObject {

	
	public PlaneWarSystem pws;
	public int speed=Constant.GAME_BG_OB_SPEED;
	
	int y1=0;    //y1的起始位置
	int x=0;    //
	int y2=-1200; //y2的起始位置
	
	
	public static Image[] bgs={   //定义两张背景图片
		Images.imgs.get("bg"),	
		Images.imgs.get("bg")
	};
	
	
	public BackGround(){  //空构造方法
		
	}
	
	
	public void draw(Graphics g){    //画出两张背景
		g.drawImage(bgs[0], x, y1, null);
		g.drawImage(bgs[1], x, y2, null);
		move();                      //调用move方法
	}
	
	public void move(){            //定义背景图片的move方法
		if(y1>Constant.GAME_HEIGHT){
			y1=(-(bgs[0].getHeight(null)-Constant.GAME_HEIGHT)-bgs[0].getHeight(null))/30*29;
		}
		if(y2>Constant.GAME_HEIGHT){
			y2=(-(bgs[0].getHeight(null)-Constant.GAME_HEIGHT)-bgs[1].getHeight(null))/30*29;
		}
		y1 += speed;   //y1的位置变化
		y2 += speed;   //y2的位置变化
		
	}
	
	
	
}
