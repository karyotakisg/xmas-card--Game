package gr.aueb.xmascard;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 * Pacman class is a sub-class of Drawable the is used to
 * draw and move the main charachter
 */
public class Pacman extends Drawable implements KeyListener {
    private int x=0;
    private int y=0;
    private int dx=0;
    private int dy=0;
    Thread t = new Thread();
    JPanel p;
    int c=0; //tracks the last move of the user
    SnowFlake sf;
    /**
     * places the charachter is the starting position and creates a 
     * drawable item
     * @param p the draw panel that the game is designed
     */
    public Pacman(JPanel p) {
      
    	super(p);
    	this.p = p;
    	x=200;
    	y=200;
    	SnowFlake.pacmanCoords(x, y);
    }
    
    public int getX() {
    	
    	return x;
    }
    
    public int getY() {
    	
    	return y;
    }
    /** 
    * creates the yellow ball and draw eyes according to its moves \
    */
    public void draw(Graphics g){
        if (dx==4) {
    	    g.drawArc(x, y, 50, 50,30,300);
            g.setColor(Color.yellow);
            g.fillArc(x, y, 50, 50,30,300);
            g.setColor(Color.BLACK);
            g.fillOval(x+15,y+10,10,10);
        }
        if (dx==-4) {
            g.drawArc(x, y, 50, 50,210,300);
            g.setColor(Color.yellow);
            g.fillArc(x, y, 50, 50,210,300);
            g.setColor(Color.BLACK);
            g.fillOval(x+15,y+10,10,10);
        }
        if (dy==-4) {
            g.setColor(Color.yellow);
            g.fillArc(x, y, 50, 50,-70,40);
            g.setColor(Color.BLACK);
            g.fillOval(x+15,y+10,10,10);
        } 
        if (dy==4) {
            g.setColor(Color.yellow);
            g.fillArc(x, y, 50, 50,180,-250);
            g.setColor(Color.BLACK);
            g.fillOval(x+15,y+10,10,10);
        }
        if(dx==0 || dy==0){ // draws the pacman when the user doesnt't press something
            if(c==0 || c==2) {
                g.setColor(Color.yellow);
                g.fillArc(x, y, 50, 50,30,300);
                g.setColor(Color.BLACK);
                g.fillOval(x+15,y+10,10,10);
            }
            if (c==1) {
                g.setColor(Color.yellow);
                g.fillArc(x, y, 50, 50,210,300);
                g.setColor(Color.BLACK);
                g.fillOval(x+15,y+10,10,10);  
            }
            if(c==3) {
                g.setColor(Color.yellow);
                g.fillArc(x, y, 50, 50,100,270);
                g.setColor(Color.BLACK);
                g.fillOval(x+15,y+10,10,10); 
            }
            if(c==4) {
                g.setColor(Color.yellow);
                g.fillArc(x, y, 50, 50,180,-250);
                g.setColor(Color.BLACK);
                g.fillOval(x+15,y+10,10,10);
            }
        }
    }
    /**
     *moves the ball according to the key presses (depends on dx,dy from keyListener)
     *also, if the user tries to escape the frame it doesn't allow him/her.
     */
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
       SnowFlake.pacmanCoords(x,  y); // Set the new coordinates in the SnowFlake class
    }
    
   //controls    
        @Override
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    
                	dx=-4;
                    moveBall();
                    p.repaint();
                    c=1;
                } else if (key == KeyEvent.VK_RIGHT) {
                   
                	dx = 4;
                    moveBall();
                    p.repaint();
                    c=2;
                } else if (key == KeyEvent.VK_UP) {     
                   
                	dy = -4;
                    moveBall();
                    p.repaint();
                    c=3;
                } else if (key == KeyEvent.VK_DOWN) {
                    
                	dy = 4;
                    moveBall();
                    p.repaint();
                    c=4;
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