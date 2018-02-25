package com.neusoft.planewar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neusoft.planewar.constant.Constant;


/**
 * 
 * @author yanglinru
 *
 */
public class MyFrame extends Frame {
   /**
    * 使用构造器初始化窗口
    */
	public void launchFrame(){
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		
		this.setLocation(Constant.GAME_LOCATION_X,Constant.GAME_LOCATION_Y);
		
		this.setVisible(true);
		
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			// 重写WindowAdapter()类的方法
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);// 退出时停止该app
			}
		});
		
		this.setTitle(Constant.GAME_NAME);	
		
		// 启动线程
		/*
		 * MyThread mt = new MyThread(); mt.start();
		 */
		new MyThread().start();// 匿名对象
}
		


	private class MyThread extends Thread {
		@Override
		public void run() {
			// 死循环
			while (true) {
				// 不停的调用paint()方法
				repaint();
		
				// 为了适合人眼识别，将线程睡眠一定时间
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	// 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
	Image backImg = null;

	// 重写update()方法，在窗口的里层添加一个虚拟的图片
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// 获取到虚拟图片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// 调用虚拟图片的paint()方法，每50ms刷新一次
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}	

	
}
