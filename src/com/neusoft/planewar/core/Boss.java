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
 * boss��ͼ
 * @author yanglinru
 *
 */
public class Boss extends PlaneObject {

    public PlaneWarSystem pws;
    int speed=2;     //boss�˶��ٶ�Ϊ2
    boolean right;
    
    
   public static Random r = new Random();
   
   //����boss������ֵ
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
    	 
    	 //����bossѪ����
    	 if(this.getLife()>0){    //��boss����ֵ��Ϊ0ʱ����Ѫ��
    		 bb.draw(g);
    	 }
    	 
    	 
 		if(step>imgs.length-1){    //
 			step=0;				
 		}
 		
 		
 		if(this.getLife()>0){       //boss����ֵ��Ϊ0ʱ������boss
 		g.drawImage(imgs[step], x, y,null);  //
 		}else{
 			return;         //����boss����
 		}
 		
 		step++;
 		move();  //����move()����
 	}
     
     
     @Override
     public void move(){
    	if(y>50){    //���߽��
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
    	
    	//boss������ͨ�ӵ�
    	if(r.nextInt(101)>95) fire01();
    	//boss��ŭ�����ӵ�
    	if(r.nextInt(101)>98) fire02();
    	
    	
    	
     }
	
     /**
      * boss�������ӵ�
      */
     public void fire01(){
    	 Bullet b = new Bullet(x+imgs[0].getWidth(null)/2,y+imgs[0].getHeight(null)/2,Direction.DOWN,pws,false,Images.imgs.get("bossbullet"));
    	 pws.bullets.add(b);
    	 
     }
     
    /**
     * boss��ŭ�������ӵ� 
     */
     public void fire02(){
    	 //��ö������ת��������ķ���
    	 Direction[] dirs=Direction.values();
    	 
    	 for(int i=0;i<dirs.length-1;i++){
    	 Bullet b = new Bullet(x,y,dirs[i],pws,false,Images.imgs.get("bossbullet"));
    	 pws.bullets.add(b);
    	 }
    	 
     }
     
     
     public BloodBar bb = new BloodBar();
     
 	/**
 	 * bossѪ�����ڲ���
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
	 * ��ײ�������
	 * ��ȡ��ǰ�ӵ����ڵľ���
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,imgs[0].getWidth(null),imgs[0].getHeight(null));
	}
 	
 
	
	
}
