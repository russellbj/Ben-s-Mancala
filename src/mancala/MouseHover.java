package mancala;
	import java.awt.*;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseMotionAdapter;
	import java.awt.font.FontRenderContext;
	import java.awt.font.TextLayout;
	import java.awt.geom.Rectangle2D;
	import javax.swing.JFrame;
	import javax.swing.JPanel;

	public class MouseHover extends JPanel {

	    private static Color hiliteColor = new Color(0xFFFFC0);
	    private static Font font = new Font("SansSerif", Font.PLAIN, 12);
	    private FontRenderContext frc =
	        new FontRenderContext(null, false, false);
	    private Point pt = new Point(Short.MAX_VALUE, Short.MAX_VALUE);

	    public MouseHover() {
	    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setPreferredSize(new Dimension(screenSize));
	        this.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                pt.setLocation(e.getX(), e.getY());
	                MouseHover.this.repaint();
	            }
	        });
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2D = (Graphics2D) g;

	        g.setColor(Color.lightGray);
	        g.fillRect(0, 0, getWidth(), getHeight());
	        
	        String s = pt.x + "," + pt.y;
	        if((110 <=pt.x && pt.x< 320) && (370<=pt.y && pt.y<610)) {
	        	s = "number 1";
	        }
	        else if((360 <=pt.x && pt.x< 570) && (370<=pt.y && pt.y<610)) {
	        	s = "number 2";
	        }
	        else if((600 <=pt.x && pt.x< 820) && (370<=pt.y && pt.y<610)) {
	        	s = "number 3";
	        }
	        else if((840 <=pt.x && pt.x< 1060) && (370<=pt.y && pt.y<610)) {
	        	s = "number 4";
	        }
	        else if((1090 <=pt.x && pt.x< 1300) && (370<=pt.y && pt.y<610)) {
	        	s = "number 5";
	        }
	        else if((1340 <=pt.x && pt.x< 1550) && (370<=pt.y && pt.y<610)) {
	        	s = "number 6";
	        }
	        else if((110 <=pt.x && pt.x< 320) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 7";
	        }
	        else if((360 <=pt.x && pt.x< 570) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 8";
	        }
	        else if((600 <=pt.x && pt.x< 820) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 9";
	        }
	        else if((840 <=pt.x && pt.x< 1060) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 10";
	        }
	        else if((1090 <=pt.x && pt.x< 1300) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 11";
	        }
	        else if((1340 <=pt.x && pt.x< 1550) && (640 <=pt.y && pt.y< 870)) {
	        	s = "number 12";
	        }
	        else {
	        	s = "Take your step!";
	        }
	        Rectangle2D.Float r = (Rectangle2D.Float)
	            font.getStringBounds(s, frc);
	        r.setRect(r.x + pt.x - 3d, r.y + pt.y - 2d,
	            r.width + 6d, r.height + 4d);
	        g2D.setPaint(hiliteColor);
	        g2D.fill(r);
	        TextLayout layout = new TextLayout(s, font, frc);
	        g2D.setPaint(Color.black);
	        layout.draw(g2D, (float) pt.x, (float) pt.y);
	        g2D.setPaint(Color.blue);
	        g2D.draw(r);
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                JFrame f = new JFrame();
	                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                f.add(new MouseHover(), BorderLayout.CENTER);
	                f.pack();
	                f.setVisible(true);
	            }
	        });
	    }
	}
