package com.flygema;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLOutput;

import static com.flygema.GameUtil.* ;
public class MyGameFrame extends Frame {
    Image bg = GameUtil.getImage("com/images/background.png") ;
    Image planeImg = GameUtil.getImage("com/images/hero1.png") ;
    shell shell =new shell();

    Plane plane = new Plane(planeImg,94,127,3) ;
    int x=200,y =300 ;
    shell[] shells =new shell[1000] ;
    Explode explode ;//声明炮弹


    //窗口
    public  void launchFrame() {
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(FRAME_WIDTH,FRAME_HIGHt);
        this.setLocation(300,0);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //启动窗口绘制线程
        new paintThread().start();
        //启动键盘监听
        this.addKeyListener(new KeyMonitor());
        //初始化50发炮弹
        for (int i=0 ; i<shells.length; i++){
            shells[i]= new shell();
        }
    }

    //键盘监听类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }

    //画笔工具
    @Override
    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0,FRAME_WIDTH, FRAME_HIGHt, null);
        plane.drawMySelf(g);
        for(int i=0;i<shells.length; i++){
            shells[i].drawMySelf(g);
            boolean peng = shells[i].getRec().intersects(plane.getRec()) ;
            if (peng){
                System.out.println("飞机挂了");
                plane.live =false ;
                if(explode==null){
                    explode = new Explode(plane.x,plane.y);
                }
                explode.draw(g);
            }
        }
        shell.drawMySelf(g);

    }

    //重画线程
    class paintThread extends  Thread {
        @Override
        public void run() {
            super.run();
            while (true){
                repaint();
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        MyGameFrame frame = new MyGameFrame();
        frame.launchFrame();
    }   private Image offScreenImage =null ;
    public void update(Graphics g ){

        if (offScreenImage  == null)
             offScreenImage  = this.createImage(FRAME_WIDTH,FRAME_HIGHt);
        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null) ;
    }
}
