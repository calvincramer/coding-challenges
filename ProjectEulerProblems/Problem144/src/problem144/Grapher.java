package problem144;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Grapher
    extends JFrame {
    
    private List<Shape> shapes = new ArrayList<>();
    private static final Color ELLIPSE_COLOR = Color.BLACK;
    private static final Color LINE_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    public Grapher() {
        super();

        this.setBounds(0, 0, 600, 1100);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setTitle("GRAPH");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    @Override 
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

	rh.put(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);

	g2.setRenderingHints(rh);
        
        Rectangle contentRectangle = new Rectangle();
        contentRectangle.x = this.getInsets().left;
        contentRectangle.y = this.getInsets().top;
        contentRectangle.width = this.getWidth() - this.getInsets().left - this.getInsets().right - 1;
        contentRectangle.height = this.getHeight()- this.getInsets().top - this.getInsets().bottom - 1;
        
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2.setColor(Color.CYAN);
        g2.draw(contentRectangle);
        
        //drawing math objects
        double zoom = 50.0;
        AffineTransform trans = new AffineTransform();
        trans.translate(this.getInsets().left, this.getInsets().top);
        trans.scale(zoom, -zoom);
        trans.translate( (contentRectangle.width / 2 / zoom), -contentRectangle.height / 2 / zoom);
        
        //g2.translate( (contentRectangle.x + contentRectangle.getWidth() / 2), 
        //        (contentRectangle.y + contentRectangle.getHeight() / 2));
        g2.setTransform(trans);
        g2.setStroke(new BasicStroke((float)(1.0 / zoom)));
        
        //axes
        g2.setColor(Color.GRAY);
        g2.drawLine(-100000, 0, 100000, 0);
        g2.drawLine(0, 10000, 0, -10000);
        
        //draw shapes
        for (Shape p : shapes) {
            if (p instanceof Line2D.Double)
                g2.setColor(LINE_COLOR);
            else if (p instanceof Ellipse2D.Double)
                g2.setColor(ELLIPSE_COLOR);
            
            g2.draw(p);
        }
    }
    
    
    public void addLine(Line2D.Double line) {
        if (line != null)
            shapes.add(line);
    }
    
    public void addEllipse(Ellipse2D.Double ellipse) {
        if (ellipse != null)
            shapes.add(ellipse);
    }
    
}
