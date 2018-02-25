package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

public class Gameover extends PlaneObject {

	
    public PlaneWarSystem pws;
	
	public Image[] imgs = {
			Images.imgs.get("gameover1"),
			Images.imgs.get("gameover2"),
			Images.imgs.get("gameover3"),
			Images.imgs.get("gameover4"),
			Images.imgs.get("gameover5"),
			Images.imgs.get("gameover6"),
			Images.imgs.get("gameover7"),
			Images.imgs.get("gameover8"),
			Images.imgs.get("gameover9"),
			Images.imgs.get("gameover10")
					
		};
	
	 
	  int step=0;
	  
	  public Gameover(){
		  
	  }
	  
	  public Gameover(PlaneWarSystem pws){
			this.x=Constant.GAME_WIDTH/2-imgs[0].getWidth(null)/2;
			this.y=Constant.GAME_HEIGHT/2-imgs[0].getHeight(null)/2;
			this.pws=pws;
			
		}
	  
	
	  public void draw(Graphics g){
			
	    	   	
			if(step>imgs.length-1){   //step大于数组中图片的长度时，令step=0
				step=0;
				
			}
			g.drawImage(imgs[step], x, y,null);
			
			step++;
		}
	  
	  
	  
	
	
	
}
