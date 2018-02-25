package com.neusoft.planewar.client;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.BackGround;
import com.neusoft.planewar.core.Begin;
import com.neusoft.planewar.core.Boss;
import com.neusoft.planewar.core.Bullet;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.core.Gameover;
import com.neusoft.planewar.core.Item;
import com.neusoft.planewar.core.Obstruction;
import com.neusoft.planewar.core.Plane;
import com.neusoft.planewar.core.Warning;
import com.neusoft.planewar.util.MyFrame;

/**
 * 
 * @author yanglinru
 *
 */
public class  PlaneWarSystem  extends MyFrame {

	//音乐变量
	private URL url;
	private  AudioClip ac;

	//定时器
	public static String jl1;
	public static String jl2;
	public static String jl3;
	static long time =60;
	
	//音乐
	   public void launchFrame() {

		   File f1 = new File("music/s5.wav");
		     try {
		      url= f1.toURL();
		     } catch (MalformedURLException e) {      
		       e.printStackTrace();
		     } 
		     ac= Applet.newAudioClip(url);
		     ac.loop();
		
	    super.launchFrame();

		this.addKeyListener(new KeyAdapter() {
			// 调用方法 按下键盘
			@Override
			public void keyPressed(KeyEvent e) {
				myplane.keyPressed(e);
			}

			// 调用方法 松开键盘
			@Override
			public void keyReleased(KeyEvent e) {
				myplane.keyReleased(e);
			}

		});
		/**
		 * 创建9架敌方飞机
		 */
		for (int i = 0; i < 9; i++) {
			Plane p = new Plane(100 + i * 100, 20, false, this);
			enemyPlanes.add(p);
		}
		/**
		 * 添加障碍物
		 */
		obs.add(new Obstruction(0, 100, 300, 50, this));
		obs.add(new Obstruction(0, 0, 50, 300, this));
		obs.add(new Obstruction(700, 100, 50, 150, this));
	}
	
	// 定义背景
	public BackGround bg = new BackGround();

	// 定义我方飞机
	public Plane myplane = new Plane(400, 470, true, this);
	// public Plane enemyPlane=new
	// Plane(100,100,Images.imgsPath[1],false,Direction.DOWN);

	// 敌方飞机容器
	public List<Plane> enemyPlanes = new ArrayList<>();

	// 子弹容器
	// Bullet(200,300,Constant.GAME_BASE_IMG_PATH+"bullet1.png",Direction.UP);*/
	 public List<Bullet> bullets = new ArrayList<>();

	// 创建爆炸类的容器
	public List<Explode> explodes = new ArrayList<>();

	// 创建障碍物的容器
	public List<Obstruction> obs = new ArrayList<>();

	// 定义警告
	public Warning waring = new Warning(this);
	//定义游戏开始
	public Begin begin = new Begin(this);
	//定义游戏结束
	public Gameover gameover = new Gameover(this);	
	// 定义boss
	public Boss boss1 = new Boss(this);	
	//定义血包容器
	public List<Item> bloods = new ArrayList<>();	
	// 定义一个单独障碍物
	// public Obstruction e6=new Obstruction(this);	
	// 画出
	public void paint(Graphics g) {

		bg.draw(g); // 画背景			
		//画出游戏开始
		begin.draw(g);
		
		if(begin.y<=-300){    //飞机大战图片开始上升，       
		for (int i = 0; i < obs.size(); i++) { // 遍历障碍物类，画出障碍物
				Obstruction ob = obs.get(i);
				ob.draw(g);
		}
		// e6.draw(g); 画出了一个单独的障碍物

		Color c = g.getColor();
		g.setColor(Color.RED);
		Font f = new Font("宋体",Font.BOLD,15);
		g.setFont(f);
		//g.drawString("当前我方飞机的方向" + myplane.dir, 50, 50);
		// g.drawString("当前敌方飞机的方向"+enemyPlane.dir, 50, 70);
		//g.drawString("当前子弹容器的大小" + bullets.size(), 50, 70);
		//g.drawString("当前敌人飞机容器的大小" + enemyPlanes.size(), 50, 100);
		//g.drawString("当前爆炸容器的大小" + explodes.size(), 50, 130);
		//g.drawString("当前飞机的生命值" + myplane.getLife(), 50, 170);
		//g.drawString("当前飞机的上一步的x:" + myplane.preX, 50, 190);
		//g.drawString("当前飞机的上一步的y:" + myplane.preY, 50, 210);
		//g.drawString("当前飞机的速度:" + myplane.speed, 50, 230);
			
		//
		g.drawString("当前游戏剩余时间："+jl1+jl2+jl3, Constant.GAME_WIDTH-250, 50);

		//当前飞机的生命剩余数
		g.drawString("当前飞机的生命剩余数" + myplane.getLifeCount(), 750, 80);
		
	    //当前我方飞机死掉的生命条数
		if(myplane.getLifeCount()<=3){
		g.drawString("当前我方飞机死掉的生命条数" +(3- myplane.getLifeCount()), 750, 110);
		}
		//当前敌人飞机的个数
		g.drawString("当前敌人飞机的个数" + enemyPlanes.size(), 750, 140);
		//计分功能
		g.drawString("当前积分" + (9-enemyPlanes.size()), 750, 170);
			
		g.setColor(c);
							
           // 画出警告 以及boss
		if (enemyPlanes.size() <= 0) {

			waring.draw(g); // 当敌机还剩下小于等于三个时，发出警告

			// 画出boss 当敌机还剩下小于等于三个时，boss出现
			boss1.draw(g);
		}			
			
		//画出gameover游戏结束
		//当满足我方飞机生命为0或者敌方boss被打死时，游戏结束		
		if(myplane.getLifeCount()<=0 || boss1.getLife()<=0){	
		    gameover.draw(g);	
		   
		}						
		// 画子弹
		// if(b!=null)b.draw(g);
		for (int i = 0; i < bullets.size(); i++) { // 遍历子弹
			Bullet b = bullets.get(i); // 定义子弹对象	
			b.draw(g); // 画出子弹
			b.hitPlanes(enemyPlanes); // 给敌方飞机添加子弹击中爆炸效果   子弹击中敌方飞机 
			b.hitPlane(myplane); // 给我方飞机添加子弹击中爆炸效果   子弹击中我方飞机
			b.hitObstructions(obs); //    子弹击中障碍物
			b.hitBoss(boss1);     //子弹击中boss
		}		
		// 画出我方飞机
		myplane.draw(g);
		myplane.concatWithObstructions(obs);   //我方飞机遇到障碍物
	
		// enemyPlane.draw(g);
		// 画敌方飞机
		for (int i = 0; i < enemyPlanes.size(); i++) {  //遍历敌方飞机，给飞机添加方法
			Plane p = enemyPlanes.get(i);
			p.draw(g);
			p.concatWithObstructions(obs);   //敌方飞机遇到障碍物
			// p.concatWithObstruction(ob1);
		}
		//画血包
		for(int i=0;i<bloods.size();i++){
			
			Item blood = bloods.get(i);	
			
			blood.draw(g);           //画出血包
			//System.out.println("1111111111111");
			myplane.eatItem(blood);  //我方飞机吃掉血包
		}			
		// 画爆炸
		for (int i = 0; i < explodes.size(); i++) { // 遍历爆炸，画出爆炸
			Explode e = explodes.get(i);
			e.draw(g);    //爆炸
		}
		// e.draw(g);
		}
	}
	
	public static void  getTime() {
        // 自定义倒计时时间
	 long hour = 0;
     long minute = 0;
     long seconds = 0;

     while (time >= 0) {
         hour = time / 3600;
         minute = (time - hour * 3600) / 60;
         seconds = time - hour * 3600 - minute * 60;
         jl1=( hour+"时");//hour +
         jl2=(minute + "分");
         jl3=(seconds + "秒");
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         time--;
     }
   }
	
	

	
	
	
	public static void main(String[] args) {
		new PlaneWarSystem().launchFrame();
		getTime();
	}
}
