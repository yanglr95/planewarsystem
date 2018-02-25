package com.neusoft.planewar.core;
import java.applet.Applet;
import java.applet.AudioClip;
/**
 * 爆炸类
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
	//爆炸
	private URL url; 
	private  AudioClip ac;

	
	
	int step=0;        //当前draw方法画第几张图片
	
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
	
	
	
	//空构造方法
	public Explode(){
		
	}
	
	
	/**
	 * 有参数的构造方法
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
		
		//爆炸音效
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
