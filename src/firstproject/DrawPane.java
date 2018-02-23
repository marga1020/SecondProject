/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstproject;

import java.awt.Color;
import static java.awt.Color.GREEN;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author marianga
 */
public class DrawPane extends JPanel { 
    private String newList, readyList, runningList, waitingList, terminatedList;
    
    public DrawPane(){
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
        setName("PA6 Thing");
        setUp();
        setBackground(new Color(150,120,100));
    }
    
    public void setUp() {
        for (Component c: getComponents())
            c.setSize(c.getPreferredSize());
        JFrame f = new JFrame(getName());
        f.setContentPane(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(false);    
    }
    
       @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(GREEN);
        g.fillRect(0, 0, 600, 600);
        
        
    } // end of paintComponent()
    
}
    