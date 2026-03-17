package com.sxt;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Teacher {
    private Image up;
    private Image down;
    private Image left;
    private Image right;



    private static final String PEOPLE_UP = "imgs/teacherup.png";
    private static final String PEOPLE_DOWN = "imgs/teacherdown.png";
    private static final String PEOPLE_LEFT = "imgs/teacherleft.png";
    private static final String PEOPLE_RIGHT = "imgs/teacherright.png";



    private static int x = 501;
    private static int y = 350;
    private static boolean faceup;
    private static boolean facedown;
    private static boolean faceleft;
    private static boolean faceright;

    private static boolean seen;

    public  final int BRIGHTNESS_HEIGHT = 200;
    public  final int BRIGHTNESS_WIDTH = 60;

    public Teacher() {
        up = Toolkit.getDefaultToolkit().getImage(PEOPLE_UP);
        down = Toolkit.getDefaultToolkit().getImage(PEOPLE_DOWN);
        left = Toolkit.getDefaultToolkit().getImage(PEOPLE_LEFT);
        right = Toolkit.getDefaultToolkit().getImage(PEOPLE_RIGHT);

        faceup = false;
        facedown = false;
        faceleft = true;
        faceright = false;



        seen = false;
        refresh();
    }

    public void refresh() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeFacingDirection();
            }
        }, 2000, 2000);
    }

    private void changeFacingDirection() {
        boolean temp = faceup;
        faceup = faceright;
        faceright = faceleft;
        faceleft = facedown;
        facedown = temp;
    }

    void draw(Graphics g) {
        if (faceup) {
            g.drawImage(up, x, y, null);
        } else if (facedown) {
            g.drawImage(down, x, y, null);
        } else if (faceleft) {
            g.drawImage(left, x, y, null);
        } else if (faceright) {
            g.drawImage(right, x, y, null);
        }
        brightenArea(g);
    }

    void brightenArea(Graphics g) {
        if (faceup) {
            int posX = x+28 ;
            int posY = y-BRIGHTNESS_HEIGHT/2;
            brighten(g, posX, posY, BRIGHTNESS_WIDTH, BRIGHTNESS_HEIGHT);
        } else if (facedown) {
            int posX = x + 29  ;
            int posY = y + 30+BRIGHTNESS_HEIGHT/2;
            brighten(g, posX, posY, BRIGHTNESS_WIDTH, BRIGHTNESS_HEIGHT);
        } else if (faceleft) {
            int posX = x-BRIGHTNESS_HEIGHT/2;
            int posY = y +25 ;
            brighten(g, posX, posY, BRIGHTNESS_HEIGHT, BRIGHTNESS_WIDTH);
        } else if (faceright) {
            int posX = x + 57+BRIGHTNESS_HEIGHT/2;
            int posY = y +28;
            brighten(g, posX, posY, BRIGHTNESS_HEIGHT, BRIGHTNESS_WIDTH);
        }

    }

    private void brighten(Graphics g, int posX, int posY, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        int brightnessX = posX - width / 2;
        int brightnessY = posY - height / 2;



        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(brightnessX, brightnessY, width, height);
        g2d.dispose();
    }



    public boolean isFaceUp(){
        return faceup;
    }
    public boolean isFaceDown(){
        return facedown;
    }
    public boolean isFaceLeft(){
        return faceleft;
    }
    public boolean isFaceRight(){
        return faceright;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int BRIGHTNESS_WIDTH(){
        return BRIGHTNESS_WIDTH;
    }
    public int BRIGHTNESS_HEIGHT(){
        return BRIGHTNESS_HEIGHT;
    }
}