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
 * 子弹类
 * @author yanglinru
 *
 */
public class Bullet	extends	PlaneObject{

	public Direction dir;     //子弹的方向
	public int speed=15;     //子弹的速度
	private boolean live=true;//
	public PlaneWarSystem pws;
	
	//区分到底是谁发出来的子弹
	private boolean good;
	
	
	public static Random r = new Random();	
	
	
	public boolean isGood() {
		return good;
	}
	//初始化
	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isLive(){
		return live;
	}
	//初始化
	public void setLive(boolean live){
		this.live=live;
	}
	
	//
	public int category;
	
	
	
	
	/**
	 * 有参数构造方法
	 * @param x
	 * @param y
	 */
	public Bullet(int x,int y/*,String imgPath*/){
		this.x=x;
		this.y=y;
		this.img=Images.imgs.get("bullet");
	}
		
	/**
	 * 有参数构造方法
	 */
public Bullet(int x,int y,Direction dir,PlaneWarSystem pws,boolean good){
		
		this(x,y,dir);  //调用本类其他构造方法
		this.pws=pws;
		this.good=good;
	}


public Bullet(int x,int y,Direction dir,PlaneWarSystem pws,boolean good,Image img){
	
	this(x,y,dir);  //调用本类其他构造方法
	this.pws=pws;
	this.img=img;
	this.good=good;
}

	
	/**
	 * 
	 * 有参数的构造函数
	 * @param x
	 * @param y
	 * @param dir
	 */
public Bullet(int x,int y,Direction dir){
	
	this(x,y);  //调用本类其他构造方法
	this.dir=dir;
}

/**
 * 
 */




	
	/**
	 * draw方法
	 */
	public void draw(Graphics g){
		if(!live){
			pws.bullets.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);   //画出子弹
		move();                        //子弹运动调用move方法
	}
	
	
	public void move(){      //子弹的move运动方向
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
		
		
		//判断子弹出界
		if(x<-100||y<-10||x>Constant.GAME_WIDTH+100||y>Constant.GAME_HEIGHT){
			this.live=false;
			this.pws.bullets.remove(this);
		}
  }
	
	
	/**
	 * 碰撞检测问题
	 * 获取当前子弹所在的矩形
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	
	
	/**
	 * 击打一架飞机的方法
	 */
	public boolean hitPlane(Plane p){
		//碰撞检测问题
		if(this.getRect().intersects(p.getRect())&&this.good!=p.isGood()){
			//System.out.println("打到了");
			
			//修改达到飞机后的业务逻辑
			if(p.isGood() && p.isLive()){
				//我方飞机
				p.setLife(p.getLife()-Constant.LOSE_BLOOD);//生命值减10
				if(p.getLife()<=0){ //生命值为0
					p.setLive(false);  //飞机死掉
					//死掉才生成爆炸
					Explode e = new Explode(p.x,p.y,pws);
					pws.explodes.add(e);
					
					//飞机死掉生命值减1
					if(p.getLifeCount()>0){
						p.setLifeCount(p.getLifeCount()-1);
					}
					
				}
				pws.bullets.remove(this);  //子弹移除
			}else{		
			//敌军
			this.live=false;
			pws.bullets.remove(this);
			p.setLive(false);		
			if(!p.isGood()){
			//生成爆炸
			Explode e = new Explode(p.x,p.y,pws);
			pws.explodes.add(e);
			
			//有关血包内容
			for(int i=0;i<pws.enemyPlanes.size();i++){
				
				Plane enemyPlane = pws.enemyPlanes.get(i);
				if(!enemyPlane.isLive()){   //当敌方飞机死掉，出现血包	
				if(r.nextInt(5)==0){category=0;}
				if(r.nextInt(5)==1){category=1;}
				if(r.nextInt(5)==2){category=2;}
				if(r.nextInt(5)==3){category=3;}
				if(r.nextInt(5)==4){category=4;}			
				if(r.nextInt(7)>1){   //随机掉落血包			
					//定义血包出现位置		
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
			pws.enemyPlanes.remove(p);    //移除
			
		  }
		}
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 击打一堆飞机的方法
	 */
	public boolean hitPlanes(List<Plane> planes){
		for(int i=0;i<planes.size();i++){
			Plane plane=planes.get(i);
			
			if(hitPlane(plane)){
				return true;
			}
		}
		//如果循环中全都没有击中
		return false;
	}
	
	
	/**
	 * 子弹打到障碍物后  死掉
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
	 * 子弹打到一堆障碍物
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
	
	
	
	
	
	
	//击打boss的方法
	public void hitBoss(Boss boss){
		if(this.live&&boss.getLife()>0&&this.getRect().intersects(boss.getRect())){
			
			if(good){   //如果是我方飞机打中boss
			boss.setLife(boss.getLife()-Constant.LOSE_BLOOD);//生命值减10
			
			    if(boss.getLife()<=0){  //生命值为0
			    	Explode e = new Explode(boss.x,boss.y,pws);
					pws.explodes.add(e);
			    }
			}
		}
	}
	
	
		
	
}
