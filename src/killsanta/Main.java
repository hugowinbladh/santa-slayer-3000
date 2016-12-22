package killsanta;

import javax.swing.*;

public class Main extends JFrame{

    Panel panel;
    
    public Main(){
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
        new Main();
    }
    
}
