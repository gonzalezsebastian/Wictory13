
package ObjetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Mat.Vector2D;

public abstract class ObjetoJuego {
protected BufferedImage texture;
	protected Vector2D position;
	
	public ObjetoJuego(Vector2D position, BufferedImage texture)
	{
		this.position = position;
		this.texture = texture;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
}