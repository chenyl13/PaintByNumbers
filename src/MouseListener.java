import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{
	public void mousePressed(MouseEvent e){
		FrameManager f = (FrameManager)e.getSource();
		f.press(e.getX(), e.getY());
//		f.repaint();
	}
	
	public void mouseReleased(MouseEvent e){
		FrameManager f = (FrameManager)e.getSource();
		f.release(e.getX(), e.getY());
		f.repaint();
	}
}
