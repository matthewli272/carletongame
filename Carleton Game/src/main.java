import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author matthewli, Jeffrey Chi
 *
 */
public class Main extends JFrame {

	public Main(double width, double height){

		super("Carleton");



		Container window = getContentPane();


	}

	public static void Main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int setWidth = (int) (width * (2.0 / 3.0));
		int setHeight = (int) (height * (5.0 / 6.0));
		main myApp = new main(setWidth,setHeight);
		myApp.setBounds((int) (setWidth * (1.0 / 3.0)) ,50,setWidth ,setHeight );

		myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myApp.setResizable(false);
		myApp.setVisible(true);
	}
}