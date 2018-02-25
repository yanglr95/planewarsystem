package com.neusoft.planewar.constant;

import com.neusoft.planewar.util.GameUtil;

/**
 * �ɻ���ս��Ϸʹ�õĳ���
 * ��ݸ��ƣ�ctrl+alt+��
 * �ƶ���ѡ���ݣ�alt+��/��
 * @author yanglinru
 *
 */
public class Constant {
    public static final int GAME_WIDTH=Integer.parseInt(GameUtil.prop.getProperty("GAME_WIDTH"));  //���ڵĿ��
    public static final int GAME_HEIGHT=Integer.parseInt(GameUtil.prop.getProperty("GAME_HEIGHT"));  //���ڵĸ߶�
    public static final int GAME_LOCATION_X=Integer.parseInt(GameUtil.prop.getProperty("GAME_LOCATION_X")); // ���ڵ�x��λ
    public static final int GAME_LOCATION_Y=Integer.parseInt(GameUtil.prop.getProperty("GAME_LOCATION_Y")); // ���ڵ�y��λ
    public static final String GAME_NAME="�ɻ���ս";
    public static final String GAME_BASE_IMG_PATH="com/neusoft/planewar/img/";
   
    
    public static final int GAME_BG_OB_SPEED=Integer.parseInt(GameUtil.prop.getProperty("GAME_BG_OB_SPEED"));  //�������ϰ��ﱳ���ٶ�
    
    public static final int LOSE_BLOOD =Integer.parseInt(GameUtil.prop.getProperty("LOSE_BLOOD"));   //ÿ�α�����ʧȥ������ֵ 
}
