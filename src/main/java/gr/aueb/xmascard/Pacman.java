package gr.aueb.xmascard;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
public class Pacman extends JPanel implements ActionListener {
    Image up;
    Image left;
    Image right;
    Image down;
    private boolean inGame = false;
    private boolean dying = false;
    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(g2d);
        }
    }
    private void movePacman() {
    
    }

    private void drawPacman(Graphics2D g2d) {

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
}