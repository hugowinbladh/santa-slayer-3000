package killsanta;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Main extends JFrame {

    Panel panel;
    
    public Main(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("sounds/samp.wav");
        panel = new Panel();
        addKeyListener(panel);
        setBounds(0,0,800,600);
        setTitle("Kill Santa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(panel);
        setVisible(true);
        playMusic("res/music.wav");
    }
    
    public static void main(String[] args) {
        
        new Main();
    }
    
    public static void playMusic(String filename) {
        while(true){
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filename)));
                clip.start();
                Thread.sleep(325000);
            }
            catch (Exception exc){
                System.out.println("exc");
            }
            
        }
    }
}
