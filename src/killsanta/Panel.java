package killsanta;

import java.awt.*;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    Timer timer;
    BufferedImage background, santa, gun;
    int  santaY, speed, gunY, bulletX, bulletY, count, amountOfSantas, score;
    ArrayList<Integer> santaYs, santaXs;
    Point mouse;
    PointerInfo pInfo;
    boolean shootin, gameover;

    public Panel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setPreferredSize(new Dimension(800, 600));
        try {
            background = ImageIO.read(new File("res/background.jpg"));
            santa = ImageIO.read(new File("res/santa.png"));
            gun = ImageIO.read(new File("res/gun.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        startGame();
    }

    public void startGame(){
        timer = new Timer(20, this);
        speed = 2;
        amountOfSantas = 1;
        //santaX = 650;
        santaY = 370;
        gunY = 275;
        bulletX = 50;
        score = 0;
        bulletY = gunY;
        count = 0;
        santaYs = new ArrayList<Integer>();
        santaXs = new ArrayList<Integer>();
        shootin = false;
        gameover = false;
        santaYs.add((int) Math.ceil(Math.random() * 370));
        santaXs.add((int) Math.ceil(Math.random() * 100) + 800);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.drawImage(background, 0, 0, this);
        g.fillRect(bulletX, bulletY + 5, 20, 7);
        int tmp = 0;
        for (int i : santaYs) {
            g.drawImage(santa, santaXs.get(tmp), i, this);
            tmp++;
        }
        g.drawImage(gun, 0, gunY, this);
        if (gameover) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Game Over", 250, 300);
        }
        g.setFont(new Font("arial", 0, 30));
        g.setColor(Color.RED);
        g.drawString("score: " + score, 630, 30);
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < santaXs.size(); i++){
            santaXs.set(i, santaXs.get(i)-speed);
        }
        count++;

        if (bulletX > 800) {
            shootin = false;
        }
        if (shootin) {
            bulletX += 20;
        } else {
            bulletY = gunY;
        }
        
        int tmp = 0;
        for (int i : santaYs) {
            if (Math.hypot((santaXs.get(tmp) + 70) - (bulletX + 25), (i + 25) - (bulletY - 50)) < 70) {
                bulletX = 50;
                santaYs.set(tmp, (int) Math.ceil(Math.random() * 370));
                santaXs.set(tmp, (int) Math.ceil(Math.random() * 100) + 800);
                score++;
                shootin = false;
            }
            tmp++;
        }
        
        if (count % 200 == 0) {
            speed++;
        }
        if (count % 500 == 0) {
            santaYs.add((int) Math.ceil(Math.random() * 370));
            santaXs.add((int) Math.ceil(Math.random() * 100) + 800);
            amountOfSantas++;
        }
        if (gameover) {
            timer.stop();
        }
        for(int i : santaXs){
            if (i < -150) {
                gameover = true;
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (!shootin) {
            bulletX = 50;
            shootin = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == VK_SPACE && gameover){
            startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        gunY = me.getY();
    }

}
