
package ObjetosJuego;

import Estados.EstadoJuego;
import Graficos.Assets;
import Mat.Vector2D;
import static ObjetosJuego.Constantes.DELTAANGLE;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Meteoro extends MovimientoObjeto{
    private Size size;
	
	public Meteoro(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, EstadoJuego gameState, Size size) {
		super(position, velocity, maxVel, texture, gameState);
		this.size = size;
		this.velocity = velocity.scale(maxVel);
		
	}

	@Override
	public void update() {
		position = position.add(velocity);
		
		if(position.getX() > Constantes.WIDTH)
			position.setX(-width);
		if(position.getY() > Constantes.HEIGHT)
			position.setY(-height);
		
		if(position.getX() < -width)
			position.setX(Constantes.WIDTH);
		if(position.getY() < -height)
			position.setY(Constantes.HEIGHT);
		
		angle += Constantes.DELTAANGLE/2;
		
	}
	
	@Override
	public void Destruir(){
		gameState.divideMeteor(this);
                gameState.addPuntaje(Constantes.METEORO_PUNT);
		super.Destruir();
	}
	
	
	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
	}

	public Size getSize(){
		return size;
	}
	
	
}