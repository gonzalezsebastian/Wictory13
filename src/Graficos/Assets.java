
package Graficos;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
	// effects
	
	public static BufferedImage speed;
	
	// lasers
	
	public static BufferedImage blueLaser;
	
	// Meteors
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
        public static BufferedImage []explosion = new BufferedImage[2];
        public static BufferedImage [] numeros = new BufferedImage[11];
	
	public static void init()
	{
		player = Carga.ImageLoader("/folder/playerShip1_orange.png");
		
		speed = Carga.ImageLoader("/folder/fire08.png");
		
		blueLaser = Carga.ImageLoader("/folder/laserGreen02.png");
                
               for (int i = 0; i < explosion.length; i++) {
                   explosion[i] = Carga.ImageLoader("/folder/1.png");
                
            }
                
                for(int i = 0; i < bigs.length; i++)
			bigs[i] = Carga.ImageLoader("/folder/meteorBrown_big1.png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = Carga.ImageLoader("/folder/meteorBrown_med1.png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = Carga.ImageLoader("/folder/meteorBrown_small1.png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = Carga.ImageLoader("/folder/meteorBrown_tiny1.png");
               
		
	
		
		
	}
	
}

