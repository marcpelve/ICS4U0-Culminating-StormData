package initialpkg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimelineCircle extends JPanel {

    private Shape cirlce = new Ellipse2D.Double(260, 100, 100, 100);
    private Dimension dim = new Dimension(450, 300);
    private final ArrayList<Shape> shapes;

    public TimelineCircle() {
        shapes = new ArrayList<>();
        shapes.add(cirlce);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                for (Shape s : shapes) {

                    if (s.contains(me.getPoint())) {//check if mouse is clicked within shape
                        if (s instanceof Ellipse2D) {
                            System.out.println();
                        }

                    }
                }
            }
        });
    }
    
    public static void initComponents(JFrame frame){
    	frame.add(new TimelineCircle());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape s : shapes) {
            g2d.draw(s);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }
	
}
