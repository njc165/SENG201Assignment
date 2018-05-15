import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class MyFont {
	
	public static void registerAll() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		InputStream is = MyFont.class.getResourceAsStream("/font/ringbearer.ttf");
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			is = MyFont.class.getResourceAsStream("/font/rock.ttf");
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			is = MyFont.class.getResourceAsStream("/font/tahoma.ttf");
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}