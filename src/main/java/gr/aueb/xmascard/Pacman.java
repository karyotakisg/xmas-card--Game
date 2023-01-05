package gr.aueb.xmascard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Pacman extends JPanel implements ActionListener {
    Image up;
    Image left;
    Image right;
    Image down;
    private boolean inGame = false;
    private boolean dying = false;
    private int x,y;
    private int dx,dy;
    private int ax,ay; //controlled by the buttons of the keyboard
    private Timer timer;
    private final int PACMAN_SPEED = 4;
    private int score;
    public Pacman(){
        addKeyListener(new TAdapter());
        loadImages();
        
    }
    private void playGame(Graphics2D g2d) {
        movePacman();
        drawPacman(g2d);
        
    }
    private void movePacman() {
        x=100; // initialize the starting x coordination of pacman
        y=100; // initialize the starting y coordination of pacman
        if (ax != 0 || ay != 0) {
            if ((ay == -1 && ax == 0)
                    || (ax == 1 && ay == 0)
                    || (ax == 0 && ay == -1 )
                    || (ax == 0 && ay == 1 )) {
                x = ax;
                y = ay;
                }
            }

            // Check for standstill
            if ((dx == -1 && dy == 0  )
                    || (dx == 1 && dy == 0)
                    || (dx == 0 && dy == -1 )
                    || (dx == 0 && dy == 1 )) {
                dx = 0;
                dy = 0;
            }
         
        x = x + PACMAN_SPEED * dx;
        y = y + PACMAN_SPEED * dy;
    }
    private void initGame(){
        score =0;
    }

    private void loadImages() {
    	down = new ImageIcon("/src/images/down.gif").getImage();
    	up = new ImageIcon("/src/images/up.gif").getImage();
    	left = new ImageIcon("/src/images/left.gif").getImage();
    	right = new ImageIcon("/src/images/right.gif").getImage();
       
    }

    private void death(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    private void drawPacman(Graphics2D g2d) {

        if (ax == -1) {
        	g2d.drawImage(left, x + 1, y + 1, this);
        } else if (ay == 1) {
        	g2d.drawImage(right, x + 1, y + 1, this);
        } else if (ay == -1) {
        	g2d.drawImage(up, x + 1, y + 1, this);
        } else {
        	g2d.drawImage(down, x + 1, y + 1, this);
        }
    }
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    ax = -1;
                    ay = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    ax = 1;
                    ay = 0;
                } else if (key == KeyEvent.VK_UP) {
                    ax = 0;
                    ay = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    ax = 0;
                    ay = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } 
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    initGame();
                }
            }
        }
    }
}