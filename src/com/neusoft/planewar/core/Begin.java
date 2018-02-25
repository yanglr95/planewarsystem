package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * 
 * @author yanglinru
 *
 */
public class Begin extends PlaneObject {

	
	
	public int speed=2;
	 public PlaneWarSystem pws;
		
		public Image[] imgs = {
				Images.imgs.get("begin1"),
				Images.imgs.get("begin2"),
				Images.imgs.get("begin3"),
				Images.imgs.get("begin4"),
				Images.imgs.get("begin5"),
				Images.imgs.get("begin6"),
				Images.imgs.get("begin7"),
				Images.imgs.get("begin8"),
				Images.imgs.get("begin9"),
				Images.imgs.get("begin10")
						
			};
		
		 
		  int step=0;
		  
		  public Begin(){
			  
		  }
		  
		  public Begin(PlaneWarSystem pws){
				this.x=200;
				this.y=100;
				this.pws=pws;
				
			}
		  
		
		  public void draw(Graphics g){
				
		    	   	
				if(step>imgs.length-1){   //step大于数组中图片的长度时，令step=0
					step=0;
					
				}
				g.drawImage(imgs[step], x, y,null);
				
				step++;
				
				move();
			}
		
		  
		  
		  public void move(){
			  y -=speed;
		  }
	
	
	
}
