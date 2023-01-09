package gr.aueb.xmascard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//public class Pacman extends JPanel implements KeyListener {
public class Pacman extends Drawable implements KeyListener {
    private int x=0;
    private int y=0;
    private int dx=0;
    private int dy=0;
    Thread t = new Thread();
    JPanel p;
    
    SnowFlake sf;
    
    public Pacman(JPanel p) {
      
    	super(p);
    	this.p = p;
    	x=200;
    	y=200;
    	SnowFlake.a(x, y);
    }
    
    public int getX() {
    	
    	return x;
    }
    
    public int getY() {
    	
    	return y;
    }
    
    public void draw(Graphics g){
        
    	g.drawOval(x, y, 50, 50);
        g.setColor(Color.yellow);
        g.fillOval(x,y,50,50);
        
    }
    
    public void moveBall(){
        x=x+dx;
        y=y+dy;
        if(x>980){
            dx=-4;
            moveBall();
        }
        if(y>720){
            dy=-4;
            moveBall();
        }
        if(x<20){
            dx=4;
            moveBall();
        }
        if(y<20){
            dy=4;
            moveBall();
        }
       SnowFlake.a(x,  y); 
    }
    
   //controls    
        @Override
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    
                	dx=-4;
                    moveBall();
                    p.repaint();
                } else if (key == KeyEvent.VK_RIGHT) {
                   
                	dx = 4;
                    moveBall();
                    p.repaint();
                } else if (key == KeyEvent.VK_UP) {     
                   
                	dy = -4;
                    moveBall();
                    p.repaint();
                } else if (key == KeyEvent.VK_DOWN) {
                    
                	dy = 4;
                    moveBall();
                    p.repaint();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {  
            dx=0;
            dy=0;

        }
        
}