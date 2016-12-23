import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Canvas {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String imagePath = selectImage();
		if (imagePath != null)
			new FrameManager("Draw", imagePath);
	}
	
	private static String selectImage(){
		JFileChooser fileChooser = new JFileChooser(".");
	    fileChooser.setDialogTitle("Please select image.");
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
		int result = fileChooser.showOpenDialog(null);
		String path = null;
		if(result == JFileChooser.APPROVE_OPTION){
			path = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println("Open file: " + path);
		}
	    return path;
	}

}
