
package Graficos;

import Mat.Vector2D;
import java.awt.image.BufferedImage;


public class Animacion {
    private BufferedImage [] frames;
    private int velocidad;
    private boolean running;
    private int indice;
    private Vector2D position;
    private long tiempo, ult;

    public Animacion(BufferedImage[] frames, int velocidad, Vector2D position) {
        this.frames = frames;
        this.velocidad = velocidad;
        this.position = position;
        indice = 0;
        running = true;
        tiempo = 0;
        ult = System.currentTimeMillis();
    }
    public void update(){
        tiempo += System.currentTimeMillis()-ult;
        ult=System.currentTimeMillis();
        if(tiempo>velocidad){
            tiempo=0;
            indice++;
            if(indice>=frames.length){
                running = false;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Vector2D getPosition() {
        return position;
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[indice];
    }
}
