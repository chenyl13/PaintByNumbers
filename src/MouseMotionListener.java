import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionListener extends MouseMotionAdapter{
	public void mouseDragged(MouseEvent e){
		FrameManager f = (FrameManager)e.getSource();
		f.drag(e.getX(), e.getY());
		f.repaint();
	}
}
