import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 * The Util class provides helpful static methods for various parts of the game.
 * This class cannot be instantiated.
 */
public class Util {
	
	/**
	 * Takes a given class as a class object and returns a new instance of that
	 * class.
	 * Only works for classes with no argument constructors.
	 * @param className		The class to be instantiated as a class object.
	 * @return				A new instance of the given class.
	 * 						Returned Object needs to be cast to the appropriate type.
	 */
	public static Object instantiate(Class<?> className) {
		Object newInstance = null;
			try {
				newInstance = className.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

		return newInstance;
	}
	
	/**
	 * Takes a ButtonGroup of JRadioButtons and returns the selected button.
	 * @param buttonGroup	The relevant ButtonGroup.
	 * @return				The currently selected button in the ButtonGroup.
	 */
	public static JRadioButton selectedButton(ButtonGroup buttonGroup) {
		JRadioButton selectedButton = null;
		for (Enumeration<AbstractButton> e = buttonGroup.getElements(); e.hasMoreElements();) {
			JRadioButton button = (JRadioButton) e.nextElement();
			if (button.isSelected()) {
				selectedButton = button;
			}
		}
		return selectedButton;
	}
	
	/**
	 * Registers all imported fonts used in the game.
	 * This allows fonts to be consistently displayed across
	 * Operating Systems.
	 */
	public static void registerAllFonts() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		InputStream is = Util.class.getResourceAsStream("/font/ringbearer.ttf");
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			is = Util.class.getResourceAsStream("/font/rock.ttf");
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			is = Util.class.getResourceAsStream("/font/tahoma.ttf");
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
