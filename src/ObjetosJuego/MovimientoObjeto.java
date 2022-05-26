
package ObjetosJuego;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Mat.Vector2D;
import Estados.EstadoJuego;
import java.util.ArrayList;

public abstract class MovimientoObjeto extends ObjetoJuego{
	
	protected Vector2D velocity;
	protected AffineTransform at;
	protected double angle;
	protected double maxVel;
	protected int width;
	protected int height;
	protected EstadoJuego gameState;
	
	public MovimientoObjeto(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, EstadoJuego gameState) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.gameState = gameState;
		width = texture.getWidth();
		height = texture.getHeight();
		angle = 0;
		
	}
        protected void colli(){
            ArrayList<MovimientoObjeto>movingObjects = gameState.getMovingObjects();
            for (int i = 0; i < movingObjects.size(); i++) {
                MovimientoObjeto m = movingObjects.get(i);
                if (m.equals(this))
                continue;
               
                double distance = m.getCenter().sub(getCenter()).getMagnitude();
                if(distance<m.width/2 + width/2 && movingObjects.contains(this)){
                 objectCollision (m,this);   
                    
                }
            }
        }
        private void objectCollision(MovimientoObjeto a, MovimientoObjeto b){
            if (!(a instanceof Meteoro && b instanceof Meteoro)){
                gameState.playExplosion(getCenter());
                a.Destruir();
                b.Destruir();
            }
        }
        protected void Destruir(){
            gameState.getMovingObjects().remove(this);
        }
        protected Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}
	

}