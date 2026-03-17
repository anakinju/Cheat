package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWin extends JFrame implements KeyListener {

    private Image bg;
    private Image pb;

    private Player student;

    private paperball cheatsheet;
    private GamePanel gamePanel1;

    private GamePanel gamePanel2;
    private Teacher teacher;

    private static int x1 = 295;
    private static int y1 = 750;

    private static int x2 = 410;
    private static int y2 = 550;

    private static int wincount = 0;
    private static int losecount = 0;

    private boolean reachDesk = false;
    public static boolean gameFinished = false;

    private static final String CLASSROOM_BG = "imgs/classroom.png";
    private static final String NEW_BG = "imgs/new_classroom.jpg";

    public static final String Desk = "imgs/desk.png";

    private static final String paperball = "imgs/paperball.png";
    private boolean changeBackground;

    private boolean desk;
    private Timer loseTimer;
    private Timer teachertimer;


    public GameWin() {
        bg = Toolkit.getDefaultToolkit().getImage(CLASSROOM_BG);
        student = new Player();
        teacher = new Teacher();
        cheatsheet = new paperball();

        gamePanel1 = new GamePanel();
        gamePanel1.addKeyListener(this);
        gamePanel1.setFocusable(true);
        add(gamePanel1);
        gamePanel1.requestFocusInWindow();
        changeBackground = false;
        desk = false;

        gamePanel2 = new GamePanel();
        add(gamePanel2);


        startTeacherAnimationLoop();
    }

    void launch() {
        this.setVisible(true);
        this.setSize(1024, 1024);
        this.setLocationRelativeTo(null);
        this.setTitle("Cheat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bg = Toolkit.getDefaultToolkit().getImage(NEW_BG);
            pb = Toolkit.getDefaultToolkit().getImage(paperball);
            changeBackground = true;
            gamePanel2.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (student.getx() >= 454 && student.getx() <= 484 && student.gety() >= 680 && student.gety() <= 725) {
                bg = Toolkit.getDefaultToolkit().getImage(Desk);
                desk = true;
                reachDesk = true;
            }
        }
        if (desk && e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bg = Toolkit.getDefaultToolkit().getImage(NEW_BG);
            desk = false;
        }

        student.keyPressed(e);
        gamePanel1.repaint();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isStudentUnderBrightenedArea() {

        int studentWidth = student.getWidth();
        int studentHeight = student.getHeight();
        int studentX = student.getx()+studentWidth/2;
        int studentY = student.gety()+studentHeight/2;


        int brightenedAreaX;
        int brightenedAreaY;
        int brightenedAreaWidth;
        int brightenedAreaHeight;
        brightenedAreaWidth = teacher.BRIGHTNESS_WIDTH;
        brightenedAreaHeight = teacher.BRIGHTNESS_HEIGHT;

        if (teacher.isFaceUp()) {
            brightenedAreaX = teacher.getX() + 28;
            brightenedAreaY = teacher.getY() - teacher.BRIGHTNESS_HEIGHT / 2;

        } else if (teacher.isFaceDown()) {
            brightenedAreaX = teacher.getX() + 29;
            brightenedAreaY = teacher.getY() + 30 + teacher.BRIGHTNESS_HEIGHT / 2;

        } else if (teacher.isFaceLeft()) {
            brightenedAreaX = teacher.getX() - teacher.BRIGHTNESS_HEIGHT / 2;
            brightenedAreaY = teacher.getY() + 25;

        } else {
            brightenedAreaX = teacher.getX() + 57 + teacher.BRIGHTNESS_HEIGHT / 2;
            brightenedAreaY = teacher.getY() + 28;

        }

        Rectangle a = new Rectangle(studentX,studentY,studentWidth,studentHeight);
        Rectangle b = new Rectangle(brightenedAreaX,brightenedAreaY,brightenedAreaWidth,brightenedAreaHeight);
        return a.intersects(b);


    }
    public boolean checkCollision(Player student, paperball p, int paperballX, int paperballY) {
        Rectangle studentBounds = new Rectangle(student.getx(), student.gety(), student.getWidth(), student.getHeight());
        Rectangle paperballBounds = new Rectangle(paperballX, paperballY, 23, 22);

        return studentBounds.intersects(paperballBounds);
    }


    private class GamePanel extends JPanel {
        private String displayText = "";

        private Timer timer;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (changeBackground) {
                g.drawImage(bg, 0, 0, null);
                if (!desk) {
                    student.draw(g);
                    teacher.draw(g);
                    g.drawImage(pb, x1, y1, null);
                    g.drawImage(pb, x2, y2, null);
                    teacher.brightenArea(g);

                    if (!displayText.isEmpty()) {
                        g.setColor(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 24);
                        g.setFont(font);
                        g.drawString(displayText, 183, 130);
                    }
                }
            } else {
                g.drawImage(bg, 0, 0, null);
            }
        }

        private void showText(String text, int duration) {
            displayText = text;
            repaint();

            if (timer != null) {
                timer.cancel();
            }

            timer = new Timer();
            TimerTask hideTask = new TimerTask() {
                @Override
                public void run() {
                    displayText = "";
                    repaint();
                }
            };

            timer.schedule(hideTask, duration);
        }



        private void checkIfSeen() {
            if (isStudentUnderBrightenedArea()) {

                SwingUtilities.invokeLater(() -> {
                    if (losecount == 0) {
                        showText("What are you doing? Go back to your seat!", 5000);
                    } else if (losecount == 1 && !gameFinished) {
                        showText("You are Cheating! You are expelled!", 5000);


                    }

                });

                if (!gameFinished) {
                    if (loseTimer != null) {
                        loseTimer.cancel();
                    }

                    reachDesk = false;

                    loseTimer = new Timer();
                    loseTimer.schedule( new TimerTask() {
                        @Override
                        public void run() {

                            SwingUtilities.invokeLater(() -> {
                                System.out.println(losecount);

                                if (losecount >= 1 && !gameFinished) {
                                    teachertimer.cancel();
                                    gameFinished = true;
                                    showGameOverPopup(2);

                                }

                                else if (!reachDesk && !gameFinished) {
                                    teachertimer.cancel();
                                    gameFinished = true;
                                    showGameOverPopup(1);

                                } else if (reachDesk) {
                                    losecount += 1;
                                }


                            });
                        }
                    }, 2000);
                }
            }


        }
        private void checkpaper(){
            if(checkCollision(student,cheatsheet,x1,y1)){
                wincount++;
                x1-=1000;
                y1-=1000;
                System.out.print(wincount);
                reachDesk = false;

            }
            if(checkCollision(student,cheatsheet,x2,y2)){
                wincount++;
                x2-=1000;
                y2-=1000;
                System.out.print(wincount);
                reachDesk = false;

            }

        }
        private boolean Checkwin(){
            return wincount == 2 && reachDesk;
        }


    }

    private void showGameOverPopup(int state) {

        String message="";
        if(state == 1) {
            message= "Game Over! You didn't return to your desk in time.";
        }
        else if(state ==2) {message = "Game Over! You are expelled.";}
        else if (state ==3){message = "You Win! You get an A+ in this MidTerm!";}

        String[] options = {"Restart", "Quit"};
        int restart = JOptionPane.showOptionDialog(
                this,
                message,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]
        );


        if (restart == JOptionPane.YES_OPTION) {
             restartGame();
        } else {
            System.exit(0);
        }

    }
    private void restartGame(){
        losecount = 0;
        wincount =0;

       x1 = 295;
        y1 = 750;

        x2 = 410;
        y2 = 550;

        reachDesk = false;
        gameFinished = false;
        changeBackground = false;
        bg = Toolkit.getDefaultToolkit().getImage(CLASSROOM_BG);
        student.resetState();



        gamePanel1.repaint();
        gamePanel2.repaint();


        startTeacherAnimationLoop();

        addKeyListener(this);
        gamePanel1.requestFocusInWindow();
    }


    private void startTeacherAnimationLoop() {
        teachertimer = new Timer();

        TimerTask teacherAnimationTask = new TimerTask() {
            @Override
            public void run() {
                gamePanel2.repaint();
                gamePanel2.checkIfSeen();
                gamePanel2.checkpaper();
                if(gamePanel2.Checkwin()){
                    teachertimer.cancel();
                    gameFinished = true;
                    showGameOverPopup(3);
                }


            }
        };

        int delay = 100;
        int period = 100;

        teachertimer.scheduleAtFixedRate(teacherAnimationTask, delay, period);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();



    }
}