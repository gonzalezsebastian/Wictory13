
package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import Graficos.Assets;
import input.Teclado;
import main.newpackage.Juego2;
import Mat.Vector2D;
import Estados.EstadoJuego;

public class Jugador extends MovimientoObjeto{
	
	private Vector2D heading;	
	private Vector2D acceleration;

	private boolean accelerating = false;
	private Cronometro fireRate;
        /*
       Esta clase crea al jugador, le asigna su imagen y lo dibuja, le da propiedades definidas por vectores como lo son
        la aceleracion y la direccion.
        
        
        */
	
	public Jugador(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, EstadoJuego gameState) {
		super(position, velocity, maxVel, texture, gameState);
		heading = new Vector2D(0, 1);
		acceleration = new Vector2D();
		fireRate = new Cronometro();
	}
	
	@Override
	public void update() 
	{
		
		
		if(Teclado.SHOOT &&  !fireRate.isRunning())
		{		
			gameState.getMovingObjects().add(0,new Laser(
					getCenter().add(heading.scale(width)),
					heading,
					Constantes.LASER_VEL,
					angle,
					Assets.blueLaser,
					gameState
					));
			fireRate.run(Constantes.FIRERATE);
		}
		
		
		if(Teclado.RIGHT)
			angle += Constantes.DELTAANGLE;
		if(Teclado.LEFT)
			angle -= Constantes.DELTAANGLE;
		
		if(Teclado.UP)
		{
			acceleration = heading.scale(Constantes.ACC);
			accelerating = true;
		}else
		{
			if(velocity.getMagnitude() != 0)
				acceleration = (velocity.scale(-1).normalize()).scale(Constantes.ACC/2);
			accelerating = false;
		}
		
		velocity = velocity.add(acceleration);
		
		velocity = velocity.limit(maxVel);
		
		heading = heading.setDirection(angle - Math.PI/2);
		
		position = position.add(velocity);
		
		if(position.getX() > Constantes.WIDTH)
			position.setX(0);
		if(position.getY() > Constantes.HEIGHT)
			position.setY(0);
		
		if(position.getX() < 0)
			position.setX(Constantes.WIDTH);
		if(position.getY() < 0)
			position.setY(Constantes.HEIGHT);
		
		
		fireRate.update();
		colli();
	}
	
	
	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width/2 + 5,
				position.getY() + height/2 + 10);
		
		AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 5, position.getY() + height/2 + 10);
		
		at1.rotate(angle, -5, -10);
		at2.rotate(angle, width/2 -5, -10);
		
		if(accelerating)
		{
			g2d.drawImage(Assets.speed, at1, null);
			g2d.drawImage(Assets.speed, at2, null);
		}
		
		
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
		
	}
	
	
}