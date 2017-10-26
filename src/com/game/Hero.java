package com.game;

import java.awt.image.BufferedImage;

/**
 * Created by zangyaoyi on 2017/10/20.
 */
public class Hero extends FlyingObject {
    private BufferedImage[] images = {};  //英雄机图片
    private int index = 0;                //英雄机图片切换索引

    private int doubleFire;   //双倍火力
    private int speedFire;   //双倍速度
    private int life;   //命

    /**
     * 初始化数据
     */
    public Hero() {
        life = 3;   //初始3条命
        speedFire = 30;
        doubleFire = 0;   //初始火力为0
        images = new BufferedImage[]{ShootGame.hero0, ShootGame.hero1}; //英雄机图片数组
        image = ShootGame.hero0;   //初始为hero0图片
        width = image.getWidth();
        height = image.getHeight();
        x = 150;
        y = 400;
    }

    /**
     * 获取双倍火力
     */
    public int isDoubleFire() {
        return doubleFire;
    }

    /**
     * 设置双倍火力
     */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    /**
     * 增加火力
     */
    public void addDoubleFire(int fire) {
        doubleFire = fire;
    }

    /**
     * 增加子弹射速
     */
    public void addSpeedFire(int fire) {
        speedFire = fire;
    }

    public int getSpeedFire() {
        return speedFire;
    }

    public void setSpeedFire(int speedFire) {
        this.speedFire = speedFire;
    }

    /**
     * 增命
     */
    public void addLife() {  //增命
        life++;
    }

    /**
     * 减命
     */
    public void subtractLife() {   //减命
        life--;
    }

    /**
     * 获取命
     */
    public int getLife() {
        return life;
    }

    /**
     * 当前物体移动了一下，相对距离，x,y鼠标位置
     */
    public void moveTo(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    /**
     * 越界处理
     */
    @Override
    public boolean outOfBounds() {
        return false;
    }

    /**
     * 发射子弹
     */
    public Bullet[] shoot() {
        int xStep = width / 4;      //4半
        int yStep = 20;  //步
        int fire = doubleFire;
        if (doubleFire > 0) {  //多倍火力
            Bullet[] bullets;
            if (fire == 1) {
                bullets = new Bullet[2];
                addSpeedFire(30);
                bullets[0] = new Bullet(x + xStep, y - yStep);  //y-yStep(子弹距飞机的位置)
                bullets[1] = new Bullet(x + 2 * xStep, y - yStep);
            } else if (fire == 2) {
                addSpeedFire(30);
                bullets = new Bullet[3];
                bullets[0] = new Bullet(x + xStep, y - yStep);  //y-yStep(子弹距飞机的位置)
                bullets[1] = new Bullet(x + 3 * xStep, y - yStep);
                bullets[2] = new Bullet(x + 2 * xStep, y - yStep);
            } else if (fire == 3) {
                bullets = new Bullet[2];
                bullets[0] = new Bullet(x + xStep, y - yStep);  //y-yStep(子弹距飞机的位置)
                addSpeedFire(10);
                bullets[1] = new Bullet(x + 3 * xStep, y - yStep);
            } else if (fire == 4) {
                bullets = new Bullet[3];
                bullets[0] = new Bullet(x + xStep, y - yStep);  //y-yStep(子弹距飞机的位置)
                addSpeedFire(10);
                bullets[1] = new Bullet(x + 3 * xStep, y - yStep);
                bullets[2] = new Bullet(x + 2 * xStep, y - yStep);
            } else if (fire == 5) {
                bullets = new Bullet[1];
                bullets[0] = new Bullet(x + 2 * xStep, y - yStep);
                addSpeedFire(10);
            } else {
                bullets = new Bullet[1];
                bullets[0] = new Bullet(x + 2 * xStep, y - yStep);
                addSpeedFire(60);
            }
            return bullets;
        } else {      //单倍火力
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x + 2 * xStep, y - yStep);
            return bullets;
        }
    }

    /**
     * 移动
     */
    @Override
    public void step() {
        if (images.length > 0) {
            image = images[index++ / 10 % images.length];  //切换图片hero0，hero1
        }
    }

    /**
     * 碰撞敌人算法
     */
    public boolean hitHero(FlyingObject other) {

        int x1 = other.x - this.width / 2;                 //x坐标最小距离
        int x2 = other.x + this.width / 2 + other.width;   //x坐标最大距离
        int y1 = other.y - this.height / 2;                //y坐标最小距离
        int y2 = other.y + this.height / 2 + other.height; //y坐标最大距离

        int herox = this.x + this.width / 2;               //英雄机x坐标中心点距离
        int heroy = this.y + this.height / 2;              //英雄机y坐标中心点距离

        return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;   //区间范围内为撞上了
    }
    /**
     * 敌人逃脱算法
     */
    public boolean hitWall(FlyingObject other) {

        int y2 = other.y + this.height / 2 + other.height; //y坐标最大距离

        return 654 <= y2;   //区间范围内为撞上了
    }
}
