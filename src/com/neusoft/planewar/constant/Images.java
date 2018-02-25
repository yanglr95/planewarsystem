package com.neusoft.planewar.constant;



import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.neusoft.planewar.util.GameUtil;

/**
 * ר�Ŵ����Ŀ�е�ͼƬ
 * @author yanglinru
 *
 */
public class Images {
	
	public  static String[] imgsPath={
	
	"com/neusoft/planewar/img/myfly.png",//����ͼƬ
	"com/neusoft/planewar/img/littlefly.png",//�ҷ��ɻ�
	"com/neusoft/planewar/img/bullet1.png",	//�з��ɻ�1��	
	//��ըͼƬ
	"com/neusoft/planewar/img/e1.png",
	"com/neusoft/planewar/img/e2.png",
	"com/neusoft/planewar/img/e3.png",
	"com/neusoft/planewar/img/e4.png",
	"com/neusoft/planewar/img/e5.png",
	"com/neusoft/planewar/img/e6.png",
	"com/neusoft/planewar/img/bg.png",
	"com/neusoft/planewar/img/e6.png",	
	//������ͼ
	"com/neusoft/planewar/img/w1.png",
	"com/neusoft/planewar/img/w2.png",
	"com/neusoft/planewar/img/w3.png",
	"com/neusoft/planewar/img/w4.png",
	"com/neusoft/planewar/img/w5.png",
	"com/neusoft/planewar/img/w6.png",
	"com/neusoft/planewar/img/w7.png",
	"com/neusoft/planewar/img/w8.png",
	"com/neusoft/planewar/img/w9.png",
	"com/neusoft/planewar/img/w10.png",	
	//boss��ͼ
	"com/neusoft/planewar/img/b1.png",
	"com/neusoft/planewar/img/b2.png",
	"com/neusoft/planewar/img/b3.png",
	"com/neusoft/planewar/img/b4.png",
	"com/neusoft/planewar/img/b5.png",
	"com/neusoft/planewar/img/b6.png",
	"com/neusoft/planewar/img/b7.png",
	"com/neusoft/planewar/img/b8.png",
	"com/neusoft/planewar/img/b9.png",
	//boss�ӵ�
	"com/neusoft/planewar/img/bossbullet.png",
	//���˷ɻ��ӵ�ͼƬ
	"com/neusoft/planewar/img/enemybullet.png",	
	//bossͷ��
	"com/neusoft/planewar/img/bosstouxiang.png",	
	//�ɻ�����ͼƬ
	"com/neusoft/planewar/img/myflylife.png",
	//��Ѫ��
	"com/neusoft/planewar/img/blood.png",
	//��Ϸ������ͼ
	"com/neusoft/planewar/img/gameover1.png",
	"com/neusoft/planewar/img/gameover2.png",
	"com/neusoft/planewar/img/gameover3.png",
	"com/neusoft/planewar/img/gameover4.png",
	"com/neusoft/planewar/img/gameover5.png",
	"com/neusoft/planewar/img/gameover6.png",
	"com/neusoft/planewar/img/gameover7.png",
	"com/neusoft/planewar/img/gameover8.png",
	"com/neusoft/planewar/img/gameover9.png",
	"com/neusoft/planewar/img/gameover10.png",
	//��Ϸ��ʼ��ͼ
	"com/neusoft/planewar/img/begin1.png",
	"com/neusoft/planewar/img/begin2.png",
	"com/neusoft/planewar/img/begin3.png",
	"com/neusoft/planewar/img/begin4.png",
	"com/neusoft/planewar/img/begin5.png",
	"com/neusoft/planewar/img/begin6.png",
	"com/neusoft/planewar/img/begin7.png",
	"com/neusoft/planewar/img/begin8.png",
	"com/neusoft/planewar/img/begin9.png",
	"com/neusoft/planewar/img/begin10.png",
	//Ѫ��2��ը�� ������
	"com/neusoft/planewar/img/blood2.png",
	"com/neusoft/planewar/img/zhadan.png",
	"com/neusoft/planewar/img/nengliang.png",
	//ȫը
	"com/neusoft/planewar/img/daodan.png",
	//����
	"com/neusoft/planewar/img/myfly3.png",		
	};
	
	public static Map<String,Image> imgs=new HashMap<>();
	static{	
		imgs.put("myflyy",GameUtil.getImage(imgsPath[0]));
		imgs.put("enfly",GameUtil.getImage(imgsPath[1]));
		imgs.put("bullet",GameUtil.getImage(imgsPath[2]));
		//��ըͼƬ
		imgs.put("e1",GameUtil.getImage(imgsPath[3]));
		imgs.put("e2",GameUtil.getImage(imgsPath[4]));
		imgs.put("e3",GameUtil.getImage(imgsPath[5]));
		imgs.put("e4",GameUtil.getImage(imgsPath[6]));
		imgs.put("e5",GameUtil.getImage(imgsPath[7]));
		imgs.put("e6",GameUtil.getImage(imgsPath[8]));	
		imgs.put("bg",GameUtil.getImage(imgsPath[9]));
		imgs.put("e6",GameUtil.getImage(imgsPath[10]));
		//������ͼ
		imgs.put("w1",GameUtil.getImage(imgsPath[11]));
		imgs.put("w2",GameUtil.getImage(imgsPath[12]));
		imgs.put("w3",GameUtil.getImage(imgsPath[13]));
		imgs.put("w4",GameUtil.getImage(imgsPath[14]));
		imgs.put("w5",GameUtil.getImage(imgsPath[15]));
		imgs.put("w6",GameUtil.getImage(imgsPath[16]));
		imgs.put("w7",GameUtil.getImage(imgsPath[17]));
		imgs.put("w8",GameUtil.getImage(imgsPath[18]));
		imgs.put("w9",GameUtil.getImage(imgsPath[19]));
		imgs.put("w10",GameUtil.getImage(imgsPath[20]));	
		//boss��ͼ
		imgs.put("b1",GameUtil.getImage(imgsPath[21]));
		imgs.put("b2",GameUtil.getImage(imgsPath[22]));
		imgs.put("b3",GameUtil.getImage(imgsPath[23]));
		imgs.put("b4",GameUtil.getImage(imgsPath[24]));
		imgs.put("b5",GameUtil.getImage(imgsPath[25]));
		imgs.put("b6",GameUtil.getImage(imgsPath[26]));
		imgs.put("b7",GameUtil.getImage(imgsPath[27]));
		imgs.put("b8",GameUtil.getImage(imgsPath[28]));
		imgs.put("b9",GameUtil.getImage(imgsPath[29]));	
		//boss�ӵ�ͼƬ
		imgs.put("bossbullet",GameUtil.getImage(imgsPath[30]));
		//���˷ɻ��ӵ�ͼƬ
		imgs.put("enemybullet",GameUtil.getImage(imgsPath[31]));
		//bossͷ��
		imgs.put("bosstouxiang",GameUtil.getImage(imgsPath[32]));
		//�ɻ�����ͼƬ
		imgs.put("myflylife",GameUtil.getImage(imgsPath[33]));
		
		//Ѫ��
		imgs.put("blood",GameUtil.getImage(imgsPath[34]));
    	//��Ϸ������ͼ
		imgs.put("gameover1",GameUtil.getImage(imgsPath[35]));
		imgs.put("gameover2",GameUtil.getImage(imgsPath[36]));
		imgs.put("gameover3",GameUtil.getImage(imgsPath[37]));
		imgs.put("gameover4",GameUtil.getImage(imgsPath[38]));
		imgs.put("gameover5",GameUtil.getImage(imgsPath[39]));
		imgs.put("gameover6",GameUtil.getImage(imgsPath[40]));
		imgs.put("gameover7",GameUtil.getImage(imgsPath[41]));
		imgs.put("gameover8",GameUtil.getImage(imgsPath[42]));
		imgs.put("gameover9",GameUtil.getImage(imgsPath[43]));
		imgs.put("gameover10",GameUtil.getImage(imgsPath[44]));
		//��Ϸ��ʼ��ͼ
		imgs.put("begin1",GameUtil.getImage(imgsPath[45]));
		imgs.put("begin2",GameUtil.getImage(imgsPath[46]));
		imgs.put("begin3",GameUtil.getImage(imgsPath[47]));
		imgs.put("begin4",GameUtil.getImage(imgsPath[48]));
		imgs.put("begin5",GameUtil.getImage(imgsPath[49]));
		imgs.put("begin6",GameUtil.getImage(imgsPath[50]));
		imgs.put("begin7",GameUtil.getImage(imgsPath[51]));
		imgs.put("begin8",GameUtil.getImage(imgsPath[52]));
		imgs.put("begin9",GameUtil.getImage(imgsPath[53]));
		imgs.put("begin10",GameUtil.getImage(imgsPath[54]));
		//Ѫ��2��ը�� ������
		imgs.put("blood2",GameUtil.getImage(imgsPath[55]));
		imgs.put("zhadan",GameUtil.getImage(imgsPath[56]));
		imgs.put("nengliang",GameUtil.getImage(imgsPath[57]));
		//����
		imgs.put("daodan",GameUtil.getImage(imgsPath[58]));
		//����
		imgs.put("myfly3",GameUtil.getImage(imgsPath[59]));
	}
	
}
