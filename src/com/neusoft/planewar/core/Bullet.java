package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Images;

/**
 * �ӵ���
 * @author yanglinru
 *
 */
public class Bullet	extends	PlaneObject{

	public Direction dir;     //�ӵ��ķ���
	public int speed=15;     //�ӵ����ٶ�
	private boolean live=true;//
	public PlaneWarSystem pws;
	
	//���ֵ�����˭���������ӵ�
	private boolean good;
	
	
	public static Random r = new Random();	
	
	
	public boolean isGood() {
		return good;
	}
	//��ʼ��
	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isLive(){
		return live;
	}
	//��ʼ��
	public void setLive(boolean live){
		this.live=live;
	}
	
	//
	public int category;
	
	
	
	
	/**
	 * �в������췽��
	 * @param x
	 * @param y
	 */
	public Bullet(int x,int y/*,String imgPath*/){
		this.x=x;
		this.y=y;
		this.img=Images.imgs.get("bullet");
	}
		
	/**
	 * �в������췽��
	 */
public Bullet(int x,int y,Direction dir,PlaneWarSystem pws,boolean good){
		
		this(x,y,dir);  //���ñ����������췽��
		this.pws=pws;
		this.good=good;
	}


public Bullet(int x,int y,Direction dir,PlaneWarSystem pws,boolean good,Image img){
	
	this(x,y,dir);  //���ñ����������췽��
	this.pws=pws;
	this.img=img;
	this.good=good;
}

	
	/**
	 * 
	 * �в����Ĺ��캯��
	 * @param x
	 * @param y
	 * @param dir
	 */
public Bullet(int x,int y,Direction dir){
	
	this(x,y);  //���ñ����������췽��
	this.dir=dir;
}

/**
 * 
 */




	
	/**
	 * draw����
	 */
	public void draw(Graphics g){
		if(!live){
			pws.bullets.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);   //�����ӵ�
		move();                        //�ӵ��˶�����move����
	}
	
	
	public void move(){      //�ӵ���move�˶�����
		switch(dir){
	     case LEFT:
		     x -= speed;
		     break;
	     case LEFT_UP:
	    	 x -= speed/1.414;
	    	 y -= speed/1.414;
	    	 break;
	     case UP:
	    	 y -= speed;
	    	 break;
	     case RIGHT_UP:
	    	 x += speed/1.414;
	    	 y -= speed/1.414;
	    	 break;
	     case RIGHT:
	    	 x += speed;
	    	 break;
	     case RIGHT_DOWN:
	    	 x +=speed/1.414;
	    	 y += speed/1.414;
	    	 break;
	     case DOWN:
	    	 y += speed;
	    	 break;
	     case LEFT_DOWN:
	    	 x -= speed/1.414;
	    	 y += speed/1.414;
	    	 break;
	     default: 
	    		 break;
	}
		
		
		//�ж��ӵ�����
		if(x<-100||y<-10||x>Constant.GAME_WIDTH+100||y>Constant.GAME_HEIGHT){
			this.live=false;
			this.pws.bullets.remove(this);
		}
  }
	
	
	/**
	 * ��ײ�������
	 * ��ȡ��ǰ�ӵ����ڵľ���
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	
	
	/**
	 * ����һ�ܷɻ��ķ���
	 */
	public boolean hitPlane(Plane p){
		//��ײ�������
		if(this.getRect().intersects(p.getRect())&&this.good!=p.isGood()){
			//System.out.println("����");
			
			//�޸Ĵﵽ�ɻ����ҵ���߼�
			if(p.isGood() && p.isLive()){
				//�ҷ��ɻ�
				p.setLife(p.getLife()-Constant.LOSE_BLOOD);//����ֵ��10
				if(p.getLife()<=0){ //����ֵΪ0
					p.setLive(false);  //�ɻ�����
					//���������ɱ�ը
					Explode e = new Explode(p.x,p.y,pws);
					pws.explodes.add(e);
					
					//�ɻ���������ֵ��1
					if(p.getLifeCount()>0){
						p.setLifeCount(p.getLifeCount()-1);
					}
					
				}
				pws.bullets.remove(this);  //�ӵ��Ƴ�
			}else{		
			//�о�
			this.live=false;
			pws.bullets.remove(this);
			p.setLive(false);		
			if(!p.isGood()){
			//���ɱ�ը
			Explode e = new Explode(p.x,p.y,pws);
			pws.explodes.add(e);
			
			//�й�Ѫ������
			for(int i=0;i<pws.enemyPlanes.size();i++){
				
				Plane enemyPlane = pws.enemyPlanes.get(i);
				if(!enemyPlane.isLive()){   //���з��ɻ�����������Ѫ��	
				if(r.nextInt(5)==0){category=0;}
				if(r.nextInt(5)==1){category=1;}
				if(r.nextInt(5)==2){category=2;}
				if(r.nextInt(5)==3){category=3;}
				if(r.nextInt(5)==4){category=4;}			
				if(r.nextInt(7)>1){   //�������Ѫ��			
					//����Ѫ������λ��		
					if(category==0){
				      Item blood = new Item(enemyPlane.x + enemyPlane.img.getWidth(null)/2-25,enemyPlane.y + enemyPlane.img.getHeight(null)/2,pws,0);	
					  pws.bloods.add(blood);
				}
					if(category==1){
					   Item blood = new Item(enemyPlane.x + enemyPlane.img.getWidth(null)/2-25,enemyPlane.y + enemyPlane.img.getHeight(null)/2,pws,1);	
					   pws.bloods.add(blood);
					}
					if(category==2){
						Item blood = new Item(enemyPlane.x + enemyPlane.img.getWidth(null)/2-25,enemyPlane.y + enemyPlane.img.getHeight(null)/2,pws,2);	
						pws.bloods.add(blood);
					}
					if(category==3){
						Item blood = new Item(enemyPlane.x + enemyPlane.img.getWidth(null)/2-25,enemyPlane.y + enemyPlane.img.getHeight(null)/2,pws,3);	
						pws.bloods.add(blood);
					}
					if(category==4){
						Item blood = new Item(enemyPlane.x + enemyPlane.img.getWidth(null)/2-25,enemyPlane.y + enemyPlane.img.getHeight(null)/2,pws,4);	
						pws.bloods.add(blood);
					}
					
					}
				}
			}
			pws.enemyPlanes.remove(p);    //�Ƴ�
			
		  }
		}
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * ����һ�ѷɻ��ķ���
	 */
	public boolean hitPlanes(List<Plane> planes){
		for(int i=0;i<planes.size();i++){
			Plane plane=planes.get(i);
			
			if(hitPlane(plane)){
				return true;
			}
		}
		//���ѭ����ȫ��û�л���
		return false;
	}
	
	
	/**
	 * �ӵ����ϰ����  ����
	 */
	
	public boolean hitObstruction(Obstruction ob){
		if(this.getRect().intersects(ob.getRect())){
			this.live=false;
			
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * �ӵ���һ���ϰ���
	 * @param obs
	 * @return
	 */
	public boolean hitObstructions(List<Obstruction> obs){
		for(int i=0;i<obs.size();i++){
			Obstruction ob=obs.get(i);
			if(this.hitObstruction(ob)){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	//����boss�ķ���
	public void hitBoss(Boss boss){
		if(this.live&&boss.getLife()>0&&this.getRect().intersects(boss.getRect())){
			
			if(good){   //������ҷ��ɻ�����boss
			boss.setLife(boss.getLife()-Constant.LOSE_BLOOD);//����ֵ��10
			
			    if(boss.getLife()<=0){  //����ֵΪ0
			    	Explode e = new Explode(boss.x,boss.y,pws);
					pws.explodes.add(e);
			    }
			}
		}
	}
	
	
		
	
}
