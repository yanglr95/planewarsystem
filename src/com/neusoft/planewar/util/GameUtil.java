package com.neusoft.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;



/**
 * 
 * @author yanglinru
 *
 */
public class GameUtil {
	
	
	/**
	 * 加载配置文件
	 */
	public static Properties prop =new  Properties();
	static{
		
		try {
			prop.load(GameUtil.class.getClassLoader().getResourceAsStream("planewar.properties"));
		} catch (IOException e) {
					e.printStackTrace();
		}
	}
	
	
  /**
   * 加载图片的方法
   */
	
	public static Image getImage(String imgPath){
    	URL u=GameUtil.class.getClassLoader().getResource(imgPath);
    	BufferedImage img=null;
    	try{
   		 img=ImageIO.read(u);
   		 
   	   }catch(IOException e){
   		   e.printStackTrace();
   	   }
   	   return img;
    }
}
