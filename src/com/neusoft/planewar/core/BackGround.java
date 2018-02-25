package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * �����ı���ͼƬ
 * @author yanglinru
 *
 */
public class BackGround extends PlaneObject {

	
	public PlaneWarSystem pws;
	public int speed=Constant.GAME_BG_OB_SPEED;
	
	int y1=0;    //y1����ʼλ��
	int x=0;    //
	int y2=-1200; //y2����ʼλ��
	
	
	public static Image[] bgs={   //�������ű���ͼƬ
		Images.imgs.get("bg"),	
		Images.imgs.get("bg")
	};
	
	
	public BackGround(){  //�չ��췽��
		
	}
	
	
	public void draw(Graphics g){    //�������ű���
		g.drawImage(bgs[0], x, y1, null);
		g.drawImage(bgs[1], x, y2, null);
		move();                      //����move����
	}
	
	public void move(){            //���屳��ͼƬ��move����
		if(y1>Constant.GAME_HEIGHT){
			y1=(-(bgs[0].getHeight(null)-Constant.GAME_HEIGHT)-bgs[0].getHeight(null))/30*29;
		}
		if(y2>Constant.GAME_HEIGHT){
			y2=(-(bgs[0].getHeight(null)-Constant.GAME_HEIGHT)-bgs[1].getHeight(null))/30*29;
		}
		y1 += speed;   //y1��λ�ñ仯
		y2 += speed;   //y2��λ�ñ仯
		
	}
	
	
	
}
