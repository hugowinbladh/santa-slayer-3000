package killsanta;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import sun.audio.*;

public class Main extends JFrame{

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
    }
    
    public static void main(String[] args) {
        play("res/music.wav");
        new Main();
    }
    
    public static void play(String filename)
{
    try
    {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
        clip.start();
    }
    catch (Exception exc)
    {
        exc.printStackTrace(System.out);
    }
}
    
    
    
    
}
