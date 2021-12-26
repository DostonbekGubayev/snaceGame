package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {
    Image image;
    static final int SCREEN_WIDH=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;
    static final int GAME_UNITS=(SCREEN_WIDH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=100;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    int bodyParts=6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction='R';
    boolean running =true;
    Timer timer;
    Random random;
    JButton tugma;
gamePanel(){

random=new Random();
    this.setPreferredSize(new Dimension(SCREEN_HEIGHT,SCREEN_WIDH));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
  //  image=new ImageIcon(getClass().getResource("b.png.png")).getImage();
        tugma=new JButton("Boshlash");
}
public void startGame()   {
    newApple();
    running=true;
    timer=new Timer(DELAY,this);
    timer.start();
}
public void paintComponent(Graphics g){
    super.paintComponent( g);
    Graphics2D g2D=(Graphics2D)g;
    //g2D.drawImage(image,0,0,null);
    draw(g);
    isOptimizedDrawingEnabled();
}
public void draw(Graphics g){
    if (running) {

        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDH, i * UNIT_SIZE);

        }
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {

            if (i == 0) {
                g.setColor(Color.green);
                g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else {
              //  g.setColor(new Color(45, 180, 0));
                g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }
    else {
        gameOver(g);
    }

}
public  void newApple(){
    appleX=random.nextInt((int)(SCREEN_WIDH/UNIT_SIZE))*UNIT_SIZE;
    appleY=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;


    }

public void move(){
    for (int i=bodyParts;i>0;i--){
        x[i]=x[i-1];
        y[i]=y[i-1];

    }
    switch (direction){
        case 'U':
            y[0]=y[0]-UNIT_SIZE;
            break;
        case 'D':
            y[0]=y[0]+UNIT_SIZE;
            break;
        case 'L':
            x[0]=x[0]-UNIT_SIZE;
            break;
        case 'R':
                x[0]=x[0]+UNIT_SIZE;
            break;

    }

}
            public void checkApple(){
    if ((x[0]==appleX)&&(y[0]==appleY)){
        bodyParts++;
        applesEaten++;
        newApple();
    }

            }
   public void checkCollisions(){
       for (int i = bodyParts; i >0 ; i--) {
           if (x[0]==x[i]&&(y[0]==y[i])){
               running=false;
           }

       }
       if (x[0]<0){
           running=false;
       }
       if (x[0]>SCREEN_WIDH){
           running=false;
       }
       if (y[0]<0){
           running=false;
       }
       if (y[0]>SCREEN_HEIGHT){
           running=false;
       }
       if (!running){
           timer.stop();
       }

   }
   public void gameOver(Graphics g){
    // hisob texti
       g.setColor(Color.red);
       g.setFont( new Font("Ink Free",Font.BOLD,40));
       FontMetrics metrics1=getFontMetrics(g.getFont());
       g.drawString("Score: "+applesEaten,
               (SCREEN_WIDH-metrics1.stringWidth("Game over: "+applesEaten))/2,g.getFont().getSize());
       //O'yin tugashi texti
       g.setColor(Color.red);
       g.setFont( new Font("Italic",Font.BOLD,40));
       FontMetrics metrics2=getFontMetrics(g.getFont());
       g.drawString("Game Over", (SCREEN_WIDH+metrics2.stringWidth("Score: "+applesEaten))/2,
               g.getFont().getSize());

   }
    @Override
    public void actionPerformed(ActionEvent hodisa) {
    if (hodisa.getSource()==tugma){
        timer.restart();
    }
        if (running) {
            move();
            checkApple();
            checkCollisions();

            if (requestFocusInWindow(true)) ;


    }
    repaint();

    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
           switch (e.getKeyCode()){
               case KeyEvent.VK_LEFT:
                   if (direction!='R'){
                       direction='L';
                   }
                   break;
                   case KeyEvent.VK_RIGHT:
                       if (direction!='L'){
                           direction='R';
                       }
                       break;
               case KeyEvent.VK_UP:
                   if (direction!='D'){
                       direction='U';
                   }
                   break;
               case KeyEvent.VK_DOWN:
                   if (direction!='U'){
                       direction='D';
                   }
                   break;

           }

        }



        }
    }

