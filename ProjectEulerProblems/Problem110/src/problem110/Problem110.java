package problem110;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JFrame;
import mathtools.MF;

public class Problem110 {

    /*
    In the following equation x, y, and n are positive integers.

    1/x + 1/y = 1/n
    It can be verified that when n = 1260 there are 113 distinct solutions and this is the least value of n for which the total number of distinct solutions exceeds one hundred.

What is the least value of n for which the number of distinct solutions exceeds four million?
    */
    
    private static double scale = 0.2;
    private static final boolean drawEquipotentialLines = true;
    private static final int EQUI_LINE_INCREMENT = 17;
    private static final boolean drawPolarLines = false;
    private static final long start = 0;
    private static final long stop = 1261;
    private static Graphics2D offScreenG;
    private static Image offScreenImage;
    
    public static void main(String[] args) {

        
        ArrayList<ArrayList<Pair>> allSolutions = new ArrayList<ArrayList<Pair>>();
        for (long n = start; n <= stop; n++)
            allSolutions.add(new ArrayList<>());
        
        for (long n = 2; n <= stop; n++) {
            long numSolutions = 0;
            
            long x = n+1;     //if x is less than n, dividing by (x-n) will result in a negative number which we don't care about
            long xStop = 2*n;
            for (; x <= xStop; x++) {
                //if (x - n == 0) {
                //    x++;
                //    continue;
                //}
                if ( (n*x) % (x - n) == 0) {
                    numSolutions++;
                    allSolutions.get((int)n).add(new Pair(x, (n*x) / (x-n)));
                } 
            }
            //symmetric
            for (int i = allSolutions.get((int)n).size() - 1; i >= 0; i--) {
                Pair temp = allSolutions.get((int)n).get(i);
                if (temp.x != temp.y)                           //dont duplicate (12,12)
                    allSolutions.get((int)n).add(new Pair(temp.y,temp.x));
            }
            
            System.out.println("n=" + n + "\tnum solutions: " + numSolutions);
            MF.printList(allSolutions.get((int)n));
            
        }
        
        //graph
        JFrame showingFrame = new JFrame() {
            @Override 
            public void paint(Graphics g) {
                if (offScreenG == null && offScreenImage != null)
                    offScreenG = (Graphics2D) offScreenImage.getGraphics();
                if (offScreenImage == null) {
                    offScreenImage = this.createImage(this.getWidth(), this.getHeight());
                    offScreenG = (Graphics2D) offScreenImage.getGraphics();
                }
                
                
                AffineTransform aft = new AffineTransform();
                aft.translate(0, this.getHeight());
                aft.scale(1.0, -1.0);
                aft.translate(20, 20);
                aft.scale(scale, scale);
                
                offScreenG.setColor(Color.BLACK);
                offScreenG.fillRect(0, 0, offScreenImage.getWidth(null), offScreenImage.getHeight(null));
                offScreenG.setTransform(aft);
                offScreenG.setColor(Color.WHITE);
                
                
                offScreenG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                offScreenG.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                
                //set stroke s.t. everything is at least 1 pixel
                offScreenG.setStroke(new BasicStroke((float)(1/scale)));
                
                //axes
                offScreenG.drawLine(0, 0, 0, (int) (this.getHeight() / scale));
                offScreenG.drawLine(0, 0, (int) (this.getWidth() / scale), 0);
                
                //tick marks
                int step = 10;
                for (int y = 0; y < this.getHeight() / scale; y += step)
                    offScreenG.drawLine(0, y, -5, y);
                for (int x = 0; x < this.getWidth() / scale; x += step)
                    offScreenG.drawLine(x, 0, x, -5);
                
                Color[] colorCycle = {new Color(255,0,0), new Color(255,135,5), new Color(247,255,15), new Color(159,255,15), new Color(15,255,115), new Color(15,255,211), 
                    new Color(15,163,255), new Color(97,134,255), new Color(143,117,255), new Color(231,122,255), new Color(255,122,204) };
                int colorIndex = 0;
                
                //some lines for the same solution equipotential lines
                if (drawEquipotentialLines) {
                    for (int i = 0; i < allSolutions.size(); i+=EQUI_LINE_INCREMENT) {
                        if (allSolutions.get(i) == null)
                            continue;
                        offScreenG.setColor(colorCycle[colorIndex]);
                        colorIndex = (colorIndex + 1) % colorCycle.length;
                        for (int j = 0; j < allSolutions.get(i).size() - 1; j++)
                            offScreenG.drawLine((int) allSolutions.get(i).get(j).x, (int) allSolutions.get(i).get(j).y, (int) allSolutions.get(i).get(j+1).x, (int) allSolutions.get(i).get(j+1).y);
                    }
                }
                
                //polar lines
                if (drawPolarLines) {
                    offScreenG.setColor(new Color(30,30,30,30));
                    offScreenG.setStroke(new BasicStroke((float)(0.5/scale)));
                    for (int i = 0; i < allSolutions.size(); i++) {
                        if (allSolutions.get(i) == null)
                            continue;
                        for (int j = 0; j < allSolutions.get(i).size(); j++)
                            offScreenG.drawLine(0, 0, (int) allSolutions.get(i).get(j).x, (int) allSolutions.get(i).get(j).y);
                    }
                }
                
                //points
                for (int i = 0; i < allSolutions.size(); i++) {
                    if (allSolutions.get(i) == null)
                        continue;
                    offScreenG.setColor(colorCycle[colorIndex]);
                    colorIndex = (colorIndex + 1) % colorCycle.length;
                    for (Pair p : allSolutions.get(i))
                        offScreenG.drawRect((int)p.x, (int)p.y, 0, 0);
                }
                
                g.drawImage(offScreenImage, 0, 0, this);
            }
        };
        showingFrame.setTitle("show!");
        showingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showingFrame.setBounds(100, 100, 1000, 800);
        showingFrame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() == -1)
                    scale *= 1.1;
                else
                    scale *= 0.9;
                
                offScreenImage = showingFrame.createImage(showingFrame.getWidth(), showingFrame.getHeight());
                offScreenG = (Graphics2D) offScreenImage.getGraphics();
                
                showingFrame.repaint();
            }
        });
        showingFrame.setVisible(true);
    }

    
}
