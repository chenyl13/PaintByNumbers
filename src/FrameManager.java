import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FrameManager extends JFrame{
	private static final long serialVersionUID = 1L;
	BufferedImage origin, target;
	Graphics2D graphics;
	int width, height;
	int currentColor;
	int lastX, lastY;

	FrameManager(String windowName, String filePath) throws FileNotFoundException, IOException{
		super(windowName);
		origin = resize(ImageIO.read(new FileInputStream(filePath)), 800, 600);
		width = origin.getWidth();
		height = origin.getHeight();
		target = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		//getContentPane().setBackground(Color.WHITE);
		this.addMouseListener(new MouseListener());
		this.addMouseMotionListener(new MouseMotionListener());
		this.setVisible(true);
	}
	
	private static BufferedImage resize(BufferedImage origin, int targetW, int targetH) {
		BufferedImage target = null;
		double originW = origin.getWidth();
		double originH = origin.getHeight();
		int type = origin.getType();
		double scaleW = (double) targetW / originW;
	    double scaleH = (double) targetH / originH;
	    if (scaleW >= 1 && scaleH >=1)
	    	return origin;
	    if (scaleW > scaleH)
	        scaleW = scaleH;
	    target = new BufferedImage((int)(originW * scaleW), (int)(originH * scaleW), type);
	    Graphics2D g = target.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	               RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    g.drawRenderedImage(origin, AffineTransform.getScaleInstance(scaleW, scaleW));
	    g.dispose();
	    return target;
	}
	
	public void press(int x, int y){
		currentColor = origin.getRGB(x, y);
		lastX = x;
		lastY = y;
	}
	
	public void drag(int x, int y){
		//graphics.drawOval(p.x, p.y, 10, 10);
		//graphics.fillOval(x, y, 10, 10);
		drawLine(lastX, lastY, x, y);
		lastX = x;
		lastY = y;
	}
	
	public void release(int x, int y){
		//graphics.drawOval(p.x, p.y, 10, 10);
		//graphics.fillOval(x, y, 10, 10);
//		drawLine(lastX, lastY, x, y);
//		lastX = x;
//		lastY = y;
		
		
		graphics = target.createGraphics();
		graphics.setStroke(new BasicStroke(8.0f));
		//graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OVER, 1.0f));
		for (int i = 0; i < width; i+=4){
			for (int j = 0; j < height; j+=4){
				graphics.setColor(new Color(origin.getRGB(i, j)));
				graphics.drawLine(i-2+(int)(1+Math.random()*(4)), j-2+(int)(1+Math.random()*(4)),
						i-5+(int)(1+Math.random()*(10)), j-5+(int)(1+Math.random()*(10)));
			}
		}
		graphics.dispose();
	}
	
	public void drawLine(int x1, int y1, int x2, int y2){
		graphics = target.createGraphics();
		graphics.setColor(new Color(currentColor));
		graphics.setStroke(new BasicStroke(8.0f));  
		graphics.drawLine(x1, y1, x2, y2);
		graphics.dispose();
		repaint();
	}

	public void paint(Graphics g){
		g.drawImage(target, 0, 0, this);
	}
	
}
