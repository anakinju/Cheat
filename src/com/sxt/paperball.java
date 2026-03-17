package com.sxt;

import java.awt.*;

public class paperball {
    private Image pb;

    private static int x1 = 295;
    private static int y1 = 750;

    private static int x2 = 410;
    private static int y2 = 573;

    private Rectangle rectangle;

    public paperball(){
        pb = Toolkit.getDefaultToolkit().getImage("imgs/paperball.jpg");
        rectangle = new Rectangle();
    }

    void draw(Graphics g){
        g.drawImage(pb,x1,y1,null);
        g.drawImage(pb,x2,y2,null);
    }






}
