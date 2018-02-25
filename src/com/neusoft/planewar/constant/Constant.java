package com.neusoft.planewar.constant;

import com.neusoft.planewar.util.GameUtil;

/**
 * 飞机大战游戏使用的常量
 * 快捷复制：ctrl+alt+↓
 * 移动所选内容：alt+↑/↓
 * @author yanglinru
 *
 */
public class Constant {
    public static final int GAME_WIDTH=Integer.parseInt(GameUtil.prop.getProperty("GAME_WIDTH"));  //窗口的宽度
    public static final int GAME_HEIGHT=Integer.parseInt(GameUtil.prop.getProperty("GAME_HEIGHT"));  //窗口的高度
    public static final int GAME_LOCATION_X=Integer.parseInt(GameUtil.prop.getProperty("GAME_LOCATION_X")); // 窗口的x定位
    public static final int GAME_LOCATION_Y=Integer.parseInt(GameUtil.prop.getProperty("GAME_LOCATION_Y")); // 窗口的y定位
    public static final String GAME_NAME="飞机大战";
    public static final String GAME_BASE_IMG_PATH="com/neusoft/planewar/img/";
   
    
    public static final int GAME_BG_OB_SPEED=Integer.parseInt(GameUtil.prop.getProperty("GAME_BG_OB_SPEED"));  //背景及障碍物背景速度
    
    public static final int LOSE_BLOOD =Integer.parseInt(GameUtil.prop.getProperty("LOSE_BLOOD"));   //每次被击中失去的生命值 
}
