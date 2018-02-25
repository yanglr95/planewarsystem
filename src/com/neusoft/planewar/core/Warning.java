package com.neusoft.planewar.core;
/**
 * 警告组图
 * @author yanglinru
 *
 */
import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;



public class Warning extends PlaneObject {
   
	
	public PlaneWarSystem pws;
	
	public Image[] imgs = {
			Images.imgs.get("w1"),
			Images.imgs.get("w2"),
			Images.imgs.get("w3"),
			Images.imgs.get("w4"),
			Images.imgs.get("w5"),
			Images.imgs.get("w6"),
			Images.imgs.get("w7"),
			Images.imgs.get("w8"),
			Images.imgs.get("w9"),
			Images.imgs.get("w10")
					
		};
	
	
	
	int step=0;
	
	public Warning(){
		
	}
	
	
    public Warning(PlaneWarSystem pws){
		this.x=Constant.GAME_WIDTH/2-imgs[0].getWidth(null)/2;
		this.y=Constant.GAME_HEIGHT/2-imgs[0].getHeight(null)/2-100;
		this.pws=pws;	
	}
	
    
    
    public void draw(Graphics g){
		
    	
    	if(pws.boss1.y>-150){    //判断语句，当boss运动了50时，警告消失
    	return;
    }
    	
		if(step>imgs.length-1){   //step大于数组中图片的长度时，令step=0
			step=0;
			
		}
		g.drawImage(imgs[step], x, y,null);
		
		step++;
	}
	
	
	
    
    
    
    
	
}
