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

	//���ֱ���
	private URL url;
	private  AudioClip ac;

	//��ʱ��
	public static String jl1;
	public static String jl2;
	public static String jl3;
	static long time =60;
	
	//����
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
			// ���÷��� ���¼���
			@Override
			public void keyPressed(KeyEvent e) {
				myplane.keyPressed(e);
			}

			// ���÷��� �ɿ�����
			@Override
			public void keyReleased(KeyEvent e) {
				myplane.keyReleased(e);
			}

		});
		/**
		 * ����9�ܵз��ɻ�
		 */
		for (int i = 0; i < 9; i++) {
			Plane p = new Plane(100 + i * 100, 20, false, this);
			enemyPlanes.add(p);
		}
		/**
		 * ����ϰ���
		 */
		obs.add(new Obstruction(0, 100, 300, 50, this));
		obs.add(new Obstruction(0, 0, 50, 300, this));
		obs.add(new Obstruction(700, 100, 50, 150, this));
	}
	
	// ���屳��
	public BackGround bg = new BackGround();

	// �����ҷ��ɻ�
	public Plane myplane = new Plane(400, 470, true, this);
	// public Plane enemyPlane=new
	// Plane(100,100,Images.imgsPath[1],false,Direction.DOWN);

	// �з��ɻ�����
	public List<Plane> enemyPlanes = new ArrayList<>();

	// �ӵ�����
	// Bullet(200,300,Constant.GAME_BASE_IMG_PATH+"bullet1.png",Direction.UP);*/
	 public List<Bullet> bullets = new ArrayList<>();

	// ������ը�������
	public List<Explode> explodes = new ArrayList<>();

	// �����ϰ��������
	public List<Obstruction> obs = new ArrayList<>();

	// ���徯��
	public Warning waring = new Warning(this);
	//������Ϸ��ʼ
	public Begin begin = new Begin(this);
	//������Ϸ����
	public Gameover gameover = new Gameover(this);	
	// ����boss
	public Boss boss1 = new Boss(this);	
	//����Ѫ������
	public List<Item> bloods = new ArrayList<>();	
	// ����һ�������ϰ���
	// public Obstruction e6=new Obstruction(this);	
	// ����
	public void paint(Graphics g) {

		bg.draw(g); // ������			
		//������Ϸ��ʼ
		begin.draw(g);
		
		if(begin.y<=-300){    //�ɻ���սͼƬ��ʼ������       
		for (int i = 0; i < obs.size(); i++) { // �����ϰ����࣬�����ϰ���
				Obstruction ob = obs.get(i);
				ob.draw(g);
		}
		// e6.draw(g); ������һ���������ϰ���

		Color c = g.getColor();
		g.setColor(Color.RED);
		Font f = new Font("����",Font.BOLD,15);
		g.setFont(f);
		//g.drawString("��ǰ�ҷ��ɻ��ķ���" + myplane.dir, 50, 50);
		// g.drawString("��ǰ�з��ɻ��ķ���"+enemyPlane.dir, 50, 70);
		//g.drawString("��ǰ�ӵ������Ĵ�С" + bullets.size(), 50, 70);
		//g.drawString("��ǰ���˷ɻ������Ĵ�С" + enemyPlanes.size(), 50, 100);
		//g.drawString("��ǰ��ը�����Ĵ�С" + explodes.size(), 50, 130);
		//g.drawString("��ǰ�ɻ�������ֵ" + myplane.getLife(), 50, 170);
		//g.drawString("��ǰ�ɻ�����һ����x:" + myplane.preX, 50, 190);
		//g.drawString("��ǰ�ɻ�����һ����y:" + myplane.preY, 50, 210);
		//g.drawString("��ǰ�ɻ����ٶ�:" + myplane.speed, 50, 230);
			
		//
		g.drawString("��ǰ��Ϸʣ��ʱ�䣺"+jl1+jl2+jl3, Constant.GAME_WIDTH-250, 50);

		//��ǰ�ɻ�������ʣ����
		g.drawString("��ǰ�ɻ�������ʣ����" + myplane.getLifeCount(), 750, 80);
		
	    //��ǰ�ҷ��ɻ���������������
		if(myplane.getLifeCount()<=3){
		g.drawString("��ǰ�ҷ��ɻ���������������" +(3- myplane.getLifeCount()), 750, 110);
		}
		//��ǰ���˷ɻ��ĸ���
		g.drawString("��ǰ���˷ɻ��ĸ���" + enemyPlanes.size(), 750, 140);
		//�Ʒֹ���
		g.drawString("��ǰ����" + (9-enemyPlanes.size()), 750, 170);
			
		g.setColor(c);
							
           // �������� �Լ�boss
		if (enemyPlanes.size() <= 0) {

			waring.draw(g); // ���л���ʣ��С�ڵ�������ʱ����������

			// ����boss ���л���ʣ��С�ڵ�������ʱ��boss����
			boss1.draw(g);
		}			
			
		//����gameover��Ϸ����
		//�������ҷ��ɻ�����Ϊ0���ߵз�boss������ʱ����Ϸ����		
		if(myplane.getLifeCount()<=0 || boss1.getLife()<=0){	
		    gameover.draw(g);	
		   
		}						
		// ���ӵ�
		// if(b!=null)b.draw(g);
		for (int i = 0; i < bullets.size(); i++) { // �����ӵ�
			Bullet b = bullets.get(i); // �����ӵ�����	
			b.draw(g); // �����ӵ�
			b.hitPlanes(enemyPlanes); // ���з��ɻ�����ӵ����б�ըЧ��   �ӵ����ез��ɻ� 
			b.hitPlane(myplane); // ���ҷ��ɻ�����ӵ����б�ըЧ��   �ӵ������ҷ��ɻ�
			b.hitObstructions(obs); //    �ӵ������ϰ���
			b.hitBoss(boss1);     //�ӵ�����boss
		}		
		// �����ҷ��ɻ�
		myplane.draw(g);
		myplane.concatWithObstructions(obs);   //�ҷ��ɻ������ϰ���
	
		// enemyPlane.draw(g);
		// ���з��ɻ�
		for (int i = 0; i < enemyPlanes.size(); i++) {  //�����з��ɻ������ɻ���ӷ���
			Plane p = enemyPlanes.get(i);
			p.draw(g);
			p.concatWithObstructions(obs);   //�з��ɻ������ϰ���
			// p.concatWithObstruction(ob1);
		}
		//��Ѫ��
		for(int i=0;i<bloods.size();i++){
			
			Item blood = bloods.get(i);	
			
			blood.draw(g);           //����Ѫ��
			//System.out.println("1111111111111");
			myplane.eatItem(blood);  //�ҷ��ɻ��Ե�Ѫ��
		}			
		// ����ը
		for (int i = 0; i < explodes.size(); i++) { // ������ը��������ը
			Explode e = explodes.get(i);
			e.draw(g);    //��ը
		}
		// e.draw(g);
		}
	}
	
	public static void  getTime() {
        // �Զ��嵹��ʱʱ��
	 long hour = 0;
     long minute = 0;
     long seconds = 0;

     while (time >= 0) {
         hour = time / 3600;
         minute = (time - hour * 3600) / 60;
         seconds = time - hour * 3600 - minute * 60;
         jl1=( hour+"ʱ");//hour +
         jl2=(minute + "��");
         jl3=(seconds + "��");
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
