package com.flygema;

import java.awt.*;

public class Explode {
    double x,y ;
    static Image[] images =new Image[1];

    static {

            images[1] =GameUtil.getImage("src/com/images/pause.png");

    }
    boolean live =true ;

    public void draw(Graphics g){
        if(!live) {
            return;
        }
        g.drawImage(images[1],(int) x,(int) y,null) ;
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
