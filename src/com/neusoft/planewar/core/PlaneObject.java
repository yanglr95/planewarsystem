package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

/**
 * �ɻ���Ŀ����ɻ���ص�����ĸ���
 * @author yanglinru
 *������ݼ� ctrl+shift+o
 */
public class PlaneObject implements Drawable,Moveable{
     
	public int x;
	public int y;
	public Image img;
	
	public void move(){
		//����ÿ�������˶��켣��ͬ������������Ҫ��д
	} 
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
}
