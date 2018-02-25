package com.neusoft.planewar.core;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * �ɻ��࣬�ṩ�ɻ���ս��Ŀ�еķɻ���ط���������
 * @author yanglinru
 *
 */
public class Plane	extends	PlaneObject {
	/**
	 * �ӵ���Ч
	 */
	private URL url; 
	private  AudioClip ac;

  /**
   * 
   */
	public int x;
	public int y;
	public Image img;
	public int speed=5;  //�ҷ��ɻ��ƶ��ٶ�
	
	/**
	 * ��ʾ�ɻ������ı���
	 * 
	 */
	private boolean live=true;
	
	public boolean isLive(){
		return live;
	}
	
	public void setLive(boolean live){
		this.live=live;
	}
	
	
	
	//��ʾ�з��ɻ�����Ƶ�ʵ������������
	public static Random r = new Random();
	
	
	//��¼�ɻ���һ����λ��
	 int preX;
	 int preY;
	
	
	/**
	 * ����ɻ��˶�����ı���
	 */
	public boolean left,right,down,up;
	
	
	/**
	 * ������ҷɻ��ı���
	 */
	public boolean good;
	
		
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

   //����ɻ�����ֵ
	private int life=100;
	

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	//��������
	public int lifeCount=0;
	
	

	public int getLifeCount() {
		return lifeCount;
	}

	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}


	/**
	 * ������   eatItem��ʹ��
	 */
	public boolean change=true;


	/**
	 * ������   eatItem��ʹ��
	 */
	public boolean add;
	

	/**
	 * ��ʼ���ɻ��Ĺ��췽��
	 */
	
	public Direction dir=Direction.STOP;
	
	
	
	/**
	 * ���PlaneWarSystem�������
	 */
	public PlaneWarSystem pws;
	
	
	
	public Plane(){
		
	}
	
	
	

	
	//Plane�Ĺ��캯��
public Plane(int x,int y,boolean good,PlaneWarSystem pws){
	
	this.x=x;
	this.y=y;
	
	this.preX=x;
	this.preY=y;
	
	this.good=good;
	if(good){
		this.img=Images.imgs.get("myflyy");   //���ҷ��ɻ�
		this.lifeCount=3;                    //��ʼ����������
	}else{
		
		this.img=Images.imgs.get("enfly");    //���з��ɻ�
		this.dir=Direction.DOWN;              //�л��˶�����   ����
	}
	this.pws=pws;
}	
	
	
	
	@Override
	public void draw(Graphics g){
		
		for(int i=0;i<lifeCount;i++){
			//��ʾ�ɻ�ʣ��������ͼƬ
			g.drawImage(Images.imgs.get("myflylife"), 40+65*i, Constant.GAME_HEIGHT-100,null);
		}
		
		
		if(!live){     //�������ʱ����ʧ
			return;
		}
		
		g.drawImage(img, x, y, null);
		
		if(good){
			//��Ѫ��
			bb.draw(g);
			move();      //�ҷ��ɻ��˶�
		}else{		
			enemyMove();   //�л��˶�
			if(r.nextInt(101)>98) fire();  //��������ӵ�
			this.speed=1;   //���õл����ٶ�
		}
	}

	
	
	
	
	/**
	 * ���Ƿɻ��ƶ��ķ���
	 */
	@Override
	public void move(){
		//ÿ���ƶ�ǰ����¼һ�µ�ǰ�ɻ���λ��
		this.preX=x;
		this.preY=y;
		
		//�ƶ�
		if(down)
			y += speed;
		if(up)
			y -= speed;
		if(right)
			x += speed;
		if(left)
			x -= speed;
		
		outOfBound();
	}
	
	
	//�з��˶�����
	public void enemyMove(){
		//��¼�з��ɻ���һ��λ��
		this.preX=x;
		this.preY=y;
		
		//�ƶ�
		switch(dir){
		     case LEFT:
			     x -= speed;
			     break;
		     case LEFT_UP:
		    	 x -= speed;
		    	 y -= speed;
		    	 break;
		     case UP:
		    	 y -= speed;
		    	 break;
		     case RIGHT_UP:
		    	 x += speed;
		    	 y -= speed;
		    	 break;
		     case RIGHT:
		    	 x += speed;
		    	 break;
		     case RIGHT_DOWN:
		    	 x +=speed;
		    	 y += speed;
		    	 break;
		     case DOWN:
		    	 y += speed;
		    	 break;
		     case LEFT_DOWN:
		    	 x -= speed;
		    	 y += speed;
		    	 break;
		     default: 
		    		 break;
		}
		
		//outOfBound();
		
	}
	
	
	
	
	/**
	 * �ж����Ƿɻ��ɳ��߽�����
	 */
	private void outOfBound(){
		//�жϱ߽�����
		if(x<0){
			x=0;
		}
		if(x>Constant.GAME_WIDTH-img.getWidth(null)){
			x=Constant.GAME_WIDTH-img.getWidth(null);
		}
		if(y<30){
			y=30;
		}
		if(y>Constant.GAME_HEIGHT-img.getHeight(null)){
			y=Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	
	
	
	/**
	 * ���ʹ�ü��̿��Ʒɻ��ƶ�
	 */
	public void keyPressed(KeyEvent e){
		 switch(e.getKeyCode()){
		   case KeyEvent.VK_DOWN:
			   down=true;
			   break;
		   case KeyEvent.VK_UP:
			   up=true;
			   break;
		   case KeyEvent.VK_LEFT:
			   left=true;
			   break;
		   case KeyEvent.VK_RIGHT:
			   right=true;
			   break;
			   //��ӷɻ������ӵ��İ���
		   case KeyEvent.VK_J:   
			   //���ӵ��Ĳ���
			   if(this.live) fire();
			   //�ӵ���Ч
			   
			   /*File f1 = new File("music/bullet.wav");
	              try {
	               url= f1.toURL();
	              } catch (MalformedURLException e1) {      
	                e1.printStackTrace();
	              } 
	              ac= Applet.newAudioClip(url);
	              ac.play();*/

			   break;  
		   case KeyEvent.VK_K:
			   rebirth();      //�����Ĳ���
			   break;        
		   default:
			   break;
		   }
		 move();
		 confirmDirection();
	   }
	
	/**
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e){
		 switch(e.getKeyCode()){
		   case KeyEvent.VK_DOWN:
			   down=false;
			   break;
		   case KeyEvent.VK_UP:
			   up=false;
			   break;
		   case KeyEvent.VK_LEFT:
			   left=false;
			   break;
		   case KeyEvent.VK_RIGHT:
			   right=false;
			   break;
		   default:
			   break;
		   }
		 confirmDirection();
	   }
	
	
	/**
	 * ���嵱ǰ�ɻ��ķ���
	 */
	public void confirmDirection(){
		
	if(left && !up && !right && !down){//��
			dir=Direction.LEFT;
	}else if(left && up && !right && !down){//����
		    dir=Direction.LEFT_UP;
	}else if(!left && up && !right && !down){//��
	        dir=Direction.UP;
    }else if(!left && up && right && !down){//����
            dir=Direction.RIGHT_UP;
    }else if(!left && !up && right && !down){//��
            dir=Direction.RIGHT;
    }else if(!left && !up && right && down){//����
            dir=Direction.RIGHT_DOWN;
    }else if(!left && !up && !right && down){//��
	        dir=Direction.DOWN;
    }else if(left && !up && !right && down){//����
    	    dir=Direction.LEFT_DOWN;
    }else{
    	   dir=Direction.STOP;
    }
		
		
  }
	
	
	/**
	 * �ɻ����������ӵ��ķ���
	 */
	public void fire(){
		if(good){  //�ҷ��ɻ��ӵ�
			
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-4,y,Direction.UP,pws,good);
		pws.bullets.add(b);	
		   if(add){
		    Bullet b2=new Bullet(x+5,y,Direction.UP,pws,good);
			pws.bullets.add(b2);
			Bullet b3=new Bullet(x+80,y,Direction.UP,pws,good);
			pws.bullets.add(b3);
	        }
		}else{  
			//�з��ɻ��ӵ�
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-4,y,Direction.DOWN,pws,good,Images.imgs.get("enemybullet"));
		b.speed=15;
		pws.bullets.add(b);
		}
		 
		
		//Bullet b2=new Bullet(x+4,y,Direction.UP,pws,good);
		//Bullet b3=new Bullet(x+12,y,Direction.UP,pws,good);
		//Bullet b4=new Bullet(x+30,y,Direction.UP,pws,good);
		//Bullet b5=new Bullet(x+40,y,Direction.UP,pws,good);
		//ÿnewһ���ӵ�����װ�ӵ������������
		//pws.bullets.add(b);
		//pws.bullets.add(b2);
		//pws.bullets.add(b3);
		//pws.bullets.add(b4);
		//pws.bullets.add(b5);
		//return b;
	}
	
	/**
	 * ��ײ�������
	 * ��ȡ��ǰ�ɻ����ڵľ���
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	
	
	/**
	 * �ɻ����ϰ���Ӵ��ķ���
	 */
	public boolean concatWithObstruction(Obstruction ob){
		if(this.getRect().intersects(ob.getRect())){
			//��ײ�߼�
			//this.dir=Direction.STOP;
			this.rollBacktoPrePosition();
			//��ǰ�ɻ�Ҫ�����ϰ�����ƶ��ٶȶ���ǿ���ƶ�
     		this.y += ob.speed;
     		
     		//׹��Ч��,���ɻ����ϰ���ѹ�ŵ��߽�ʱ���ɻ���������ը��
			if(y>(Constant.GAME_HEIGHT-img.getHeight(null))){
					
				if(this.isLive()){
					this.setLive(false);  //��ܷɻ�����
					
					Explode e = new Explode(this.x,this.y-60,pws);
					pws.explodes.add(e);
					
					if(this.getLifeCount()>0){
						this.setLifeCount(this.getLifeCount()-1);
					}									
				}			 					  				
			}
			
			return true;
		}else{
			return false;
		}
		
	
	}
	
	
	//�ɻ���һ���ϰ���Ӵ�
	public boolean concatWithObstructions(List<Obstruction> obs){
		for(int i=0;i<obs.size();i++){
			Obstruction ob=obs.get(i);
			if(this.concatWithObstruction(ob)){
				return true;
			}
		}
		return false;
	}
	
	
	
	//�ص���һ������
	private void rollBacktoPrePosition(){
		this.x = this.preX;
		this.y = this.preY;
	}
	
	
	public BloodBar bb = new BloodBar();
	/**
	 * �ɻ�Ѫ�����ڲ���
	 */
	class BloodBar{	
		public void draw(Graphics g){	
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-10, img.getWidth(null), 7);
			if(img.getWidth(null)*life/100>=40){
				g.setColor(Color.GREEN);
				g.drawRect(x, y-10, img.getWidth(null), 7);
				g.fillRect(x, y-10,img.getWidth(null)*life/100 ,7);
			}
			if(img.getWidth(null)*life/100>=20&&img.getWidth(null)*life/100<40){
				g.setColor(Color.CYAN);
				g.drawRect(x, y-10, img.getWidth(null), 7);
				g.fillRect(x, y-10,img.getWidth(null)*life/100 ,7);
			}
			if(img.getWidth(null)*life/100<20){
				g.setColor(Color.RED);
				g.fillRect(x, y-10,img.getWidth(null)*life/100 ,7);
			}
							
			g.setColor(c);
			
			
		}
	}
	
	
	
	//������������
	private void rebirth(){
		if(!live && lifeCount>0){
			this.setLive(true);
			this.setLife(100);
			this.x=400;
			this.y=600;
		}
		
	}
	
	/**
	 * �ɻ���Ѫ����Ѫ
	 */
	
	public boolean eatItem(Item item){
		if(item.isLive() && good && live &&lifeCount>0 && this.getRect().intersects(item.getRect())){
			if(this.getLife()<100){       //С��100��Ѫ	
			   if(item.category==0){	 //category==0Ѫ��1 ��Ѫ10
			   this.setLife(this.getLife()+10);
			    }
			   if(item.category==1){        //category==1Ѫ��2  ��Ѫ20
			   this.setLife(this.getLife()+20); 
			   }	   
			   
			}
			if(this.getLife()>=100){      //����100ʱ��
				this.life=100;
			}
			
			
			if(pws.myplane.live){      //�ҷ��ɻ����ų�ը��
				if(item.category==2){   //category==2��ը��  ȫը
			    for(int i=0;i<pws.enemyPlanes.size();i++){
				Bullet b = new Bullet(0,0,dir.DOWN,pws,good,Images.imgs.get("daodan"));
				b.speed=20;
				pws.bullets.add(b);
			     }	    
				}
			}
			
			if(item.category==3){  //category==3   ʵ�ֱ���
				if(change){
					this.img=Images.imgs.get("myfly3");
					change=false;
					item.setLive(false);		
				}
			     add=true;
			}
			
			if(item.category==4){  //category==4   ������
				if(this.lifeCount<=2){
				this.lifeCount +=1;
				}
			}
			
			
			item.setLive(false);
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * �ɻ���һ��Ѫ����Ѫ
	 */
	public boolean eatItems(List<Item> items){
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			if(eatItem(item)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	
	
	
	
	
	

		
}
