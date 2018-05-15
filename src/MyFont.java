import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
		} catch (Exception e) {
			throw new RuntimeException("blah");
		}
		
	}
	
}