import java.awt.Font;
import java.io.InputStream;

public class MyFont {

	public static Font getHeadingFont(float size) {
		
		InputStream is = MyFont.class.getResourceAsStream("/font/ringbearer.ttf");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			return font.deriveFont(size);
		} catch (Exception e) {
			throw new RuntimeException("Unable to create Font");
		}
			
	}
	
}
