package com.neusoft.planewar.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;
import com.neusoft.planewar.core.Plane.BloodBar;

/**
 * boss组图
 * @author yanglinru
 *
 */
public class Boss extends PlaneObject {

    public PlaneWarSystem pws;
    int speed=2;     //boss运动速度为2
    boolean right;
    
    
   public static Random r = new Random();
   
   //定义boss的生命值
   private int life=2000;
	

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	
	


	public Image[] imgs = {
			Images.imgs.get("b1"),
			Images.imgs.get("b2"),
			Images.imgs.get("b3"),
			Images.imgs.get("b4"),
			Images.imgs.get("b5"),
			Images.imgs.get("b6"),
			Images.imgs.get("b7"),
			Images.imgs.get("b8"),
			Images.imgs.get("b9")			
		};
	
	
	
	int step=0;
	
	public Boss(){
		
	}
	
     public Boss(PlaneWarSystem pws){
    	this.x=Constant.GAME_WIDTH/2-imgs[0].getWidth(null)/2;
 		this.y=-imgs[0].getHeight(null)-100;
 		this.pws=pws;
	}
     
     
     
     public void draw(Graphics g){
    	 
    	 //画出boss血量条
    	 if(this.getLife()>0){    //当boss生命值不为0时，画血条
    		 bb.draw(g);
    	 }
    	 
    	 
 		if(step>imgs.length-1){    //
 			step=0;				
 		}
 		
 		
 		if(this.getLife()>0){       //boss生命值不为0时，画出boss
 		g.drawImage(imgs[step], x, y,null);  //
 		}else{
 			return;         //否则boss死掉
 		}
 		
 		step++;
 		move();  //调用move()方法
 	}
     
     
     @Override
     public void move(){
    	if(y>50){    //到边界后
    		y=50;
    		if(right){
    			x += speed;
    		}else{
    			x -= speed;
    		}
    		if(x<5){
    			right=true;
    		}
    		if(x>Constant.GAME_WIDTH-imgs[0].getWidth(null)-5){
    			right=false;
    		}
    	} 
    	y += speed; 
    	
    	//boss发出普通子弹
    	if(r.nextInt(101)>95) fire01();
    	//boss愤怒发出子弹
    	if(r.nextInt(101)>98) fire02();
    	
    	
    	
     }
	
     /**
      * boss发出的子弹
      */
     public void fire01(){
    	 Bullet b = new Bullet(x+imgs[0].getWidth(null)/2,y+imgs[0].getHeight(null)/2,Direction.DOWN,pws,false,Images.imgs.get("bossbullet"));
    	 pws.bullets.add(b);
    	 
     }
     
    /**
     * boss愤怒发出的子弹 
     */
     public void fire02(){
    	 //将枚举类型转换成数组的方法
    	 Direction[] dirs=Direction.values();
    	 
    	 for(int i=0;i<dirs.length-1;i++){
    	 Bullet b = new Bullet(x,y,dirs[i],pws,false,Images.imgs.get("bossbullet"));
    	 pws.bullets.add(b);
    	 }
    	 
     }
     
     
     public BloodBar bb = new BloodBar();
     
 	/**
 	 * boss血量的内部类
 	 */
 	class BloodBar{
 		
 		public void draw(Graphics g){
 			g.drawImage(Images.imgs.get("bosstouxiang"),30,40,null);
 			Color c = g.getColor();
 			g.setColor(Color.YELLOW);
 			g.drawRect(80, 45,700, 25);
 			
 			if(700*life/2000>=500){
 			  g.setColor(Color.YELLOW);
 			  g.drawRect(80, 45,700, 25);
 			  g.fillRect(80, 45,700*life/2000 ,25);  
 			}
 			if(700*life/2000>=300 && 700*life/2000<500 ){
 				g.setColor(Color.GREEN);
 	 			g.drawRect(80, 45,700, 25);
 	 			g.fillRect(80, 45,700*life/2000 ,25);
 			}
 			if(700*life/2000<300 ){
 				g.setColor(Color.RED);
 	 			g.drawRect(80, 45,700, 25);
 	 			g.fillRect(80, 45,700*life/2000 ,25);
 			}	
 			g.setColor(c);
 		}
 	}
     
     
     
 	/**
	 * 碰撞检测问题
	 * 获取当前子弹所在的矩形
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,imgs[0].getWidth(null),imgs[0].getHeight(null));
	}
 	
 
	
	
}
