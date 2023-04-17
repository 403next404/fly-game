package com.flygema;

import java.awt.*;

public class shell extends  GameObject {
    double degree ;

    @Override
    public void drawMySelf(Graphics g) {
        Color c = g.getColor() ;
        g.setColor(Color.yellow);
        g.fillOval(x,y,winth,height);


        //炮弹飞行

        x+= speed*Math.cos(degree);
        y+= speed*Math.sin(degree);

        //实现边界碰撞回弹

        if (x>GameUtil.FRAME_WIDTH|| y<30 ){
            degree= -degree ;
        }
        if (y>GameUtil.FRAME_HIGHt|| x<30){
            degree= Math.PI-degree ;

        }

        g.setColor(c);
    }

    public shell() {
        degree = Math.random() *Math.PI*2 ;
        x =500 ;
        y = 900 ;
        winth = 5  ;
        height =5 ;
        speed =3 ;
    }

}
