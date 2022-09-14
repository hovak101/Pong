import javax.swing.*;
import java.awt.*;

public class Runner {
    /* Abstract:
     *Ball:
     Create a new window.
     Ball starts in third quarter. Paint circle at ((3/4)*window window, (1/2)*window length)
     ball has 4 move states: top right, bottom right, top left, bottom left.
     ball starts in top right move state. It changes state when it hits the window border.
     Delete everything in window and repaint, this time with updated x and y coordinates for the ball.
     *
     *Racket:
     Draw a rectangle at (50, 1/2panelWidth)
     * x value never changes. Only y value changes. y value increases by 1 when d is pressed and decrease by 1 when a is pressed.
     * delete everything and repaint.
     */
    public static void main(String[] args) throws InterruptedException {
        long startTime;
        long endTime;
        long targetMSBF = 17; //stands for "target milliseconds between frame"; approximately 60 fps.
        //creates the window and a canvas to draw on.
        JFrame frame = new JFrame();
        frame.setTitle("Tennis");
        TennisPanel panel = new TennisPanel();
        panel.setPreferredSize(new Dimension(1200, 600));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.setFocusable(true);

        // initialize panel's fields.
        panel.init();

        // paints ball in starting position
        while (true) {
            startTime = System.currentTimeMillis();
            panel.repaint();
            endTime = System.currentTimeMillis();
            Thread.sleep(targetMSBF - (endTime - startTime));
        }
    }
}
