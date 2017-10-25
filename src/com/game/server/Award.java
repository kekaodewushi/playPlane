package com.game.server;

/**
 * Created by zangyaoyi on 2017/10/20.
 */
public interface Award {
    int DOUBLE_FIRE = 1;  //双倍火力
    int THREE_FIRE=2;//三倍火力
    int SPEED_DOUBLE_FIRE = 3;//双倍火力，双倍速率
    int SPEED_THREE_FIRE = 4;//三倍火力，双倍速率
    int SPEED_FOUR_FIRE = 5;//双倍速率
    int SPEED_FIVE_FIRE = 6;//缓慢速速
    int LIFE = 0;   //1条命
    /** 获得奖励类型(上面的0或1) */
    int getType();
}
