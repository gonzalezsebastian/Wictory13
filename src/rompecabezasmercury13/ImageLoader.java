package rompecabezasmercury13;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @autor Sebastian Gonzalez
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
        return null;
    }
}
