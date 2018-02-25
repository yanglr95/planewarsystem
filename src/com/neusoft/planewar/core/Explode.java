package com.neusoft.planewar.core;
import java.applet.Applet;
import java.applet.AudioClip;
/**
 * ��ը��
 */
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Images;

/**
 * 
 * @author yanglinru
 *
 */
public class Explode extends	PlaneObject{
	//��ը
	private URL url; 
	private  AudioClip ac;

	
	
	int step=0;        //��ǰdraw�������ڼ���ͼƬ
	
	private boolean live=true;
	
	public PlaneWarSystem pws;
	
	
	public boolean isLive() {
		return live;
	}


	public void setLive(boolean live) {
		this.live = live;
	}


	public static Image[] imgs = {
		Images.imgs.get("e1"),
		Images.imgs.get("e2"),
		Images.imgs.get("e3"),
		Images.imgs.get("e4"),
		Images.imgs.get("e5"),
		Images.imgs.get("e6"),
		
		
	};
	
	
	
	//�չ��췽��
	public Explode(){
		
	}
	
	
	/**
	 * �в����Ĺ��췽��
	 * @param x
	 * @param y
	 */
    public Explode(int x,int y,PlaneWarSystem pws){
		this.x=x;
		this.y=y;
		this.pws=pws;
	}
	
	
	public void draw(Graphics g){
		if(!live){
			pws.explodes.remove(this);
			return;
		}
		if(step>imgs.length-1){
			step=0;
			live=false;
			return;
		}
		g.drawImage(imgs[step], x, y,null);
		
		step++;
		
		//��ը��Ч
	/*	File f1 = new File("music/bome.wav");
        try {
         url= f1.toURL();
        } catch (MalformedURLException e1) {      
          e1.printStackTrace();
        } 
        ac= Applet.newAudioClip(url);
        ac.play();*/

		
		
		
		
	}
	
}
