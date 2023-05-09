package move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame
{
    Ball ball=null;
    public static void main(String[] args)
    {
        BallMove move=new BallMove();
    }
    public BallMove()
    {
        ball=new Ball();
        this.add(ball);
        this.addKeyListener(ball);
        this.setSize(500,500);
        this.setVisible(true);
    }
}
class Ball extends JPanel implements KeyListener
{
    int x=100;
    int y=100;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            y++;
        } else if(e.getKeyCode()==KeyEvent.VK_UP) {
            y--;
        } else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            x--;
        } else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            x++;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
}