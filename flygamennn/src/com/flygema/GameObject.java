package com.flygema;

import java.awt.*;

//游戏物体的跟类
public class GameObject {
    Image img ;//对应的图片
    int x,y ;//坐标
    int speed ;//物体移动的速度
    int winth,height ;//物体的宽度高度

    public  void drawMySelf (Graphics g ) {
        g.drawImage(img,x,y,94, 127, null);
    }
    public  Rectangle getRec() {
        return  new Rectangle(x,y,winth ,height) ;
    }
    public  GameObject (){}

    public GameObject(Image img, int x, int y, int speed, int winth, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.winth = winth;
        this.height = height;
    }

    public GameObject(Image img, int x, int y, int speed) {
       this (img,x,y) ;

        this.speed = speed;
        if(img!=null){
            this.winth =img.getWidth(null);
            this.height =img.getHeight(null) ;

        }
    }

    public GameObject(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img) {
        this.img = img;
        if(img!=null){
            this.winth =img.getWidth(null);
            this.height =img.getHeight(null) ;

        }
    }
}
