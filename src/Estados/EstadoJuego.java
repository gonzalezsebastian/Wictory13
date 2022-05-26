
package Estados;

import Graficos.Animacion;
import java.awt.Graphics;
import java.util.ArrayList;

import ObjetosJuego.MovimientoObjeto;
import ObjetosJuego.Jugador;
import Graficos.Assets;
import Mat.Vector2D;
import ObjetosJuego.Constantes;
import ObjetosJuego.Meteoro;
import ObjetosJuego.Size;
import java.awt.Color;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class EstadoJuego {
	private Jugador player;
	private ArrayList<MovimientoObjeto> movingObjects = new ArrayList<MovimientoObjeto>();
        private ArrayList<Animacion>explosiones = new ArrayList<Animacion>();
        private int puntaje=0;
        private int meteors;
        private BufferStrategy bs;
	private Graphics g;
	
	public EstadoJuego()
	{
		player = new Jugador(new Vector2D(Constantes.WIDTH/2 - Assets.player.getWidth()/2,
				Constantes.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(), Constantes.PLAYER_MAX_VEL, Assets.player, this);
		movingObjects.add(player);
		meteors=1;
                startWave();
	}
        public void addPuntaje (int valor){
          puntaje += valor;  
        }
        public void divideMeteor(Meteoro meteor){
		
		Size size = meteor.getSize();
		
		BufferedImage[] textures = size.textures;
		
		Size newSize = null;
		
		switch(size){
		case BIG:
			newSize =  Size.MED;
			break;
		case MED:
			newSize = Size.SMALL;
			break;
		case SMALL:
			newSize = Size.TINY;
			break;
		default:
			return;
		}
		
		for(int i = 0; i < size.quantity; i++){
			movingObjects.add(new Meteoro(
					meteor.getPosition(),
					new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
					Constantes.METEOR_VEL*Math.random() + 1,
					textures[(int)(Math.random()*textures.length)],
					this,
					newSize
					));
		}	
	}
	
        private void startWave(){
            double x, y;
            for (int i = 0; i < meteors; i++) {
                x = i % 2 == 0? Math.random()*Constantes.WIDTH:0;
                y = i % 2 == 0?0: Math.random()*Constantes.HEIGHT;
                BufferedImage texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];
                movingObjects.add(new Meteoro(
                new Vector2D(x,y), new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                        Constantes.METEOR_VEL*Math.random()+1, texture,
                         this, Size.BIG
                
                ));

                
            }
            meteors++;
        }
	public void playExplosion(Vector2D position){
       explosiones.add(new Animacion(
				Assets.explosion,
				50,
				position.sub(new Vector2D(Assets.explosion[0].getWidth()/2, Assets.explosion[0].getHeight()/2))
				));
	}
	public void update()
	{
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).update();
                for (int i = 0; i < explosiones.size(); i++) {
                Animacion anim = explosiones.get(i);
                anim.update();
                if(!anim.isRunning()){
                    explosiones.remove(i);
                }
                }
                for (int i = 0; i < movingObjects.size(); i++) 
                if (movingObjects.get(i)instanceof Meteoro)
                    return;
                
                startWave();
            
                
	}
	
	public void draw(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).draw(g);
                for (int i=0;i<explosiones.size();i++){
                    Animacion anim = explosiones.get(i);
                    g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(), null);
                    
                }
                drawPuntaje(g);
	}
        private void drawPuntaje(Graphics g){
           Vector2D position = new Vector2D(850,25);
           String PuntajeToString = Integer.toString(puntaje);
            g.setColor(Color.WHITE);
		
		g.drawString(""+PuntajeToString, (int)position.getX(), (int)position.getY());
		position.setX(position.getX()+20);
		//---------------------
		
		g.dispose();
        
           
        }
        
       
	public ArrayList<MovimientoObjeto> getMovingObjects() {
		return movingObjects;
	}
}