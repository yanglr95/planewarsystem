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
 * 飞机类，提供飞机大战项目中的飞机相关方法和属性
 * @author yanglinru
 *
 */
public class Plane	extends	PlaneObject {
	/**
	 * 子弹音效
	 */
	private URL url; 
	private  AudioClip ac;

  /**
   * 
   */
	public int x;
	public int y;
	public Image img;
	public int speed=5;  //我方飞机移动速度
	
	/**
	 * 表示飞机存亡的变量
	 * 
	 */
	private boolean live=true;
	
	public boolean isLive(){
		return live;
	}
	
	public void setLive(boolean live){
		this.live=live;
	}
	
	
	
	//表示敌方飞机开火频率的随机数生成器
	public static Random r = new Random();
	
	
	//记录飞机上一步的位置
	 int preX;
	 int preY;
	
	
	/**
	 * 代表飞机运动方向的变量
	 */
	public boolean left,right,down,up;
	
	
	/**
	 * 代表敌我飞机的变量
	 */
	public boolean good;
	
		
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

   //定义飞机生命值
	private int life=100;
	

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	//生命条数
	public int lifeCount=0;
	
	

	public int getLifeCount() {
		return lifeCount;
	}

	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}


	/**
	 * 定义在   eatItem中使用
	 */
	public boolean change=true;


	/**
	 * 定义在   eatItem中使用
	 */
	public boolean add;
	

	/**
	 * 初始化飞机的构造方法
	 */
	
	public Direction dir=Direction.STOP;
	
	
	
	/**
	 * 添加PlaneWarSystem类的引用
	 */
	public PlaneWarSystem pws;
	
	
	
	public Plane(){
		
	}
	
	
	

	
	//Plane的构造函数
public Plane(int x,int y,boolean good,PlaneWarSystem pws){
	
	this.x=x;
	this.y=y;
	
	this.preX=x;
	this.preY=y;
	
	this.good=good;
	if(good){
		this.img=Images.imgs.get("myflyy");   //画我方飞机
		this.lifeCount=3;                    //初始生命有三条
	}else{
		
		this.img=Images.imgs.get("enfly");    //画敌方飞机
		this.dir=Direction.DOWN;              //敌机运动方向   向下
	}
	this.pws=pws;
}	
	
	
	
	@Override
	public void draw(Graphics g){
		
		for(int i=0;i<lifeCount;i++){
			//表示飞机剩余生命的图片
			g.drawImage(Images.imgs.get("myflylife"), 40+65*i, Constant.GAME_HEIGHT-100,null);
		}
		
		
		if(!live){     //当不存活时，消失
			return;
		}
		
		g.drawImage(img, x, y, null);
		
		if(good){
			//画血条
			bb.draw(g);
			move();      //我方飞机运动
		}else{		
			enemyMove();   //敌机运动
			if(r.nextInt(101)>98) fire();  //随机发射子弹
			this.speed=1;   //设置敌机的速度
		}
	}

	
	
	
	
	/**
	 * 主角飞机移动的方法
	 */
	@Override
	public void move(){
		//每次移动前，记录一下当前飞机的位置
		this.preX=x;
		this.preY=y;
		
		//移动
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
	
	
	//敌方运动方法
	public void enemyMove(){
		//记录敌方飞机上一步位置
		this.preX=x;
		this.preY=y;
		
		//移动
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
	 * 判断主角飞机飞出边界问题
	 */
	private void outOfBound(){
		//判断边界问题
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
	 * 添加使用键盘控制飞机移动
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
			   //添加飞机发出子弹的按键
		   case KeyEvent.VK_J:   
			   //发子弹的操作
			   if(this.live) fire();
			   //子弹音效
			   
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
			   rebirth();      //重生的操作
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
	 * 定义当前飞机的方向
	 */
	public void confirmDirection(){
		
	if(left && !up && !right && !down){//左
			dir=Direction.LEFT;
	}else if(left && up && !right && !down){//左上
		    dir=Direction.LEFT_UP;
	}else if(!left && up && !right && !down){//上
	        dir=Direction.UP;
    }else if(!left && up && right && !down){//右上
            dir=Direction.RIGHT_UP;
    }else if(!left && !up && right && !down){//右
            dir=Direction.RIGHT;
    }else if(!left && !up && right && down){//右下
            dir=Direction.RIGHT_DOWN;
    }else if(!left && !up && !right && down){//下
	        dir=Direction.DOWN;
    }else if(left && !up && !right && down){//左下
    	    dir=Direction.LEFT_DOWN;
    }else{
    	   dir=Direction.STOP;
    }
		
		
  }
	
	
	/**
	 * 飞机按键发出子弹的方法
	 */
	public void fire(){
		if(good){  //我方飞机子弹
			
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-4,y,Direction.UP,pws,good);
		pws.bullets.add(b);	
		   if(add){
		    Bullet b2=new Bullet(x+5,y,Direction.UP,pws,good);
			pws.bullets.add(b2);
			Bullet b3=new Bullet(x+80,y,Direction.UP,pws,good);
			pws.bullets.add(b3);
	        }
		}else{  
			//敌方飞机子弹
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-4,y,Direction.DOWN,pws,good,Images.imgs.get("enemybullet"));
		b.speed=15;
		pws.bullets.add(b);
		}
		 
		
		//Bullet b2=new Bullet(x+4,y,Direction.UP,pws,good);
		//Bullet b3=new Bullet(x+12,y,Direction.UP,pws,good);
		//Bullet b4=new Bullet(x+30,y,Direction.UP,pws,good);
		//Bullet b5=new Bullet(x+40,y,Direction.UP,pws,good);
		//每new一个子弹，往装子弹的容器中添加
		//pws.bullets.add(b);
		//pws.bullets.add(b2);
		//pws.bullets.add(b3);
		//pws.bullets.add(b4);
		//pws.bullets.add(b5);
		//return b;
	}
	
	/**
	 * 碰撞检测问题
	 * 获取当前飞机所在的矩形
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	
	
	/**
	 * 飞机与障碍物接触的方法
	 */
	public boolean concatWithObstruction(Obstruction ob){
		if(this.getRect().intersects(ob.getRect())){
			//碰撞逻辑
			//this.dir=Direction.STOP;
			this.rollBacktoPrePosition();
			//当前飞机要随着障碍物的移动速度而被强行移动
     		this.y += ob.speed;
     		
     		//坠毁效果,当飞机被障碍物压着到边界时，飞机死亡并爆炸。
			if(y>(Constant.GAME_HEIGHT-img.getHeight(null))){
					
				if(this.isLive()){
					this.setLive(false);  //这架飞机死掉
					
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
	
	
	//飞机与一堆障碍物接触
	public boolean concatWithObstructions(List<Obstruction> obs){
		for(int i=0;i<obs.size();i++){
			Obstruction ob=obs.get(i);
			if(this.concatWithObstruction(ob)){
				return true;
			}
		}
		return false;
	}
	
	
	
	//回到上一步方法
	private void rollBacktoPrePosition(){
		this.x = this.preX;
		this.y = this.preY;
	}
	
	
	public BloodBar bb = new BloodBar();
	/**
	 * 飞机血量的内部类
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
	
	
	
	//定义重生方法
	private void rebirth(){
		if(!live && lifeCount>0){
			this.setLive(true);
			this.setLife(100);
			this.x=400;
			this.y=600;
		}
		
	}
	
	/**
	 * 飞机吃血包加血
	 */
	
	public boolean eatItem(Item item){
		if(item.isLive() && good && live &&lifeCount>0 && this.getRect().intersects(item.getRect())){
			if(this.getLife()<100){       //小与100加血	
			   if(item.category==0){	 //category==0血包1 加血10
			   this.setLife(this.getLife()+10);
			    }
			   if(item.category==1){        //category==1血包2  加血20
			   this.setLife(this.getLife()+20); 
			   }	   
			   
			}
			if(this.getLife()>=100){      //大于100时，
				this.life=100;
			}
			
			
			if(pws.myplane.live){      //我方飞机活着吃炸弹
				if(item.category==2){   //category==2吃炸弹  全炸
			    for(int i=0;i<pws.enemyPlanes.size();i++){
				Bullet b = new Bullet(0,0,dir.DOWN,pws,good,Images.imgs.get("daodan"));
				b.speed=20;
				pws.bullets.add(b);
			     }	    
				}
			}
			
			if(item.category==3){  //category==3   实现变身
				if(change){
					this.img=Images.imgs.get("myfly3");
					change=false;
					item.setLive(false);		
				}
			     add=true;
			}
			
			if(item.category==4){  //category==4   加生命
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
	 * 飞机吃一堆血包加血
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
