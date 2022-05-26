
package Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Carga {
	
	public static BufferedImage ImageLoader(String path)
	{
		try {
			return ImageIO.read(Carga.class.getResource(path));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
