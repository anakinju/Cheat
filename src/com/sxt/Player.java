package com.sxt;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    private Image pup;
    private Image pdown;
    private Image pleft;
    private Image pright;

    public Rectangle rectangle;

    private static final String PEOPLE_UP = "imgs/studentup.png";
    private static final String PEOPLE_DOWN = "imgs/studentdown.png";
    private static final String PEOPLE_LEFT = "imgs/studentleft.png";
    private static final String PEOPLE_RIGHT = "imgs/studentright.png";

    private static int x = 454, y = 680;
    private final int initialx = 454, initialy = 680;
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public Player() {
        pup = Toolkit.getDefaultToolkit().getImage(PEOPLE_UP);
        pdown = Toolkit.getDefaultToolkit().getImage(PEOPLE_DOWN);
        pleft = Toolkit.getDefaultToolkit().getImage(PEOPLE_LEFT);
        pright = Toolkit.getDefaultToolkit().getImage(PEOPLE_RIGHT);
        movingUp = false;
        movingDown = true;
        movingLeft = false;
        movingRight = false;
        rectangle = new Rectangle();
    }

    void draw(Graphics g) {
        if (movingUp) {
            g.drawImage(pup, x, y, null);
        } else if (movingDown) {
            g.drawImage(pdown, x, y, null);
        } else if (movingLeft) {
            g.drawImage(pleft, x, y, null);
        } else if (movingRight) {
            g.drawImage(pright, x, y, null);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                if ((x + 13 > 250 && y > 241 && y < 800) || (x + 13 > 258 && y > 242 && y < 412) || (x + 13 > 470 && y > 342 && y < 802)|| (x + 13 > 250 && y > 712 && y < 812) ) {
                    this.movingUp = false;
                    this.movingDown = false;
                    this.movingLeft = true;
                    this.movingRight = false;
                    x -= 15;
                }
                break;
            case 38:
                if ((y > 257 && x > 250 && x < 345) || (y + 173 > 440 && x > 250 && x < 500) || (y + 173 > 550 && x > 470 && x < 552) || (y + 173 > 900 && x > 250 && x < 558)) {
                    this.movingUp = true;
                    this.movingDown = false;
                    this.movingLeft = false;
                    this.movingRight = false;
                    y -= 15;
                }
                break;
            case 39:
                if ((x + 13 < 350 && y > 241 && y < 800) || (x + 13 < 500 && y > 242 && y < 412) || (x + 13 < 580 && y > 342 && y < 802) || (x + 13 < 580 && y > 712 && y < 812) ) {
                    this.movingUp = false;
                    this.movingDown = false;
                    this.movingLeft = false;
                    this.movingRight = true;
                    x += 15;
                }
                break;
            case 40:
                if ((y < 820 && x > 250 && x < 345) || (y < 432 && x > 250 && x < 500) || (y < 820 && x > 470 && x < 552) || (y < 820 && x > 250 && x < 558)) {
                    this.movingUp = false;
                    this.movingDown = true;
                    this.movingLeft = false;
                    this.movingRight = false;
                    y += 15;
                }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int getx(){
        return this.x;



    }
    public int gety(){
        return this.y;


    }
    public void resetState(){
        x = initialx;
        y = initialy;


        movingUp = false;
        movingDown = true;
        movingLeft = false;
        movingRight = false;


    }

    public int getWidth(){
        return 57;
    }
    public int getHeight(){
        return 188;
    }

}