import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TennisPanel extends JPanel {
    //Panel
    int panelWidth;
    int panelHeight;
    //Ball
    int ballDiameter;
    int ballX;
    int ballY;
    int xC;
    int yC;
    int ballTravDis;
    //Racquet
    int racquetX;
    int racquetY;
    int racquetWidth;
    int racquetHeight;
    int racquetTravDis;
    //Racquet Movement
    boolean up;
    boolean down;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // ball movement
        if (ballX == panelWidth - ballDiameter) {
            xC = -ballTravDis;
        }
        else if (ballX == 0) {
            xC = ballTravDis;
        }

        if (ballY == panelHeight - ballDiameter) {
            yC = -ballTravDis;
        }
        else if (ballY == 0) {
            yC = ballTravDis;
        }

        if (getRacquetBounds().intersects(getBallBounds())) {
            xC = -xC;
            //yC = -yC;
            ballX = racquetX + racquetWidth;
        }
        ballX += xC;
        ballY += yC;

        //ball painting
        g2.fillOval(ballX, ballY, ballDiameter, ballDiameter);

        // racket movement
        if (up && !(racquetY - racquetTravDis < 0)) {
            racquetY -= racquetTravDis;
        }
        else if(down && !(racquetY + racquetTravDis > panelHeight - racquetHeight)) {
            racquetY += racquetTravDis;
        }
        //racket painting
        g2.fillRect(racquetX, racquetY, racquetWidth, racquetHeight);
    }

    public void init() {
        //Note: make sure racquet travel distance is always divisible by the height of the panel
        racquetTravDis = 2;
        ballTravDis = 2;
        //panel
        panelWidth = getSize().width;
        panelHeight = getSize().height;
        //ball
        ballDiameter = 30;
        ballX = 3*panelWidth/4;// - ballDiameter/2 - ballTravDis;
        ballY = panelHeight/2;// - ballDiameter/2 - ballTravDis;
        xC = -ballTravDis;
        yC = ballTravDis;
        //racquet
        racquetHeight = 70;
        racquetWidth = 16;
        racquetX = 50;
        racquetY = panelHeight/2 - racquetHeight/2;

        //racquet movement
        KeyListener listener = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    up = true;
                }
                else if(e.getKeyCode() == KeyEvent.VK_A) {
                    down = true;
                }
            }
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    up = false;
                }
                else if(e.getKeyCode() == KeyEvent.VK_A) {
                    down = false;
                }
            }
        };

        addKeyListener(listener);
    }

    private Rectangle getRacquetBounds() {
        return new Rectangle(racquetX, racquetY, racquetWidth, racquetHeight);
    }

    private Rectangle getBallBounds() {
        return new Rectangle(ballX, ballY, ballDiameter, ballDiameter);
    }
}
