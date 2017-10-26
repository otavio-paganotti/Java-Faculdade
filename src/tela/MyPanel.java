/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static java.time.Clock.system;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Rafael
 */

public class MyPanel extends JPanel implements MouseMotionListener, MouseWheelListener, MouseListener
{
    private int x1, y1;
    private boolean primeiro;
    private Color cor;
    private int raio;
    private int max, min;
    
    public MyPanel(Dimension dim)
    {
        this();
        this.setPreferredSize(dim);
    }
    public MyPanel()
    {
        this.primeiro = true;
        this.raio = 3;
        this.max = 30;
        this.min = 3;
        cor = new Color(0, 0, 0);
        this.setVisible(true);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        if(primeiro)
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            this.primeiro = false;
        }
        g.setColor(Color.red);
        g.drawLine(100, 100, 150, 150);
        g.setColor(cor);
        g.fillOval(x1, y1, raio, raio);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        //Random rand = new Random();
        //cor = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int lado = e.getWheelRotation();
        if (lado<0)
        {
            if(raio<max)
            {
                this.raio++;
            }
        }
        else
        {
            if(raio>min)
            {
                this.raio--;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON2)
        {
            Random rand = new Random();
            this.cor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            Robot robot;
            try
            {
                robot = new Robot();
                Point point = e.getLocationOnScreen();
                this.cor = robot.getPixelColor(point.x, point.y);
            }
            catch (AWTException ex)
            {
                System.err.println("Bateu a Bad");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
