package rompecabezasmercury13;

import Menú.Menú;
import Menú.menuR;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @autor Sebastian Gonzalez
 */
public class RompecabezasMercury13 extends JFrame{
    
    public static Container contenedor;
    public static Tablero tablero;
    private BufferedImage imgF;
    private BufferedImage imgI;
    private BufferedImage imgD;
    /*
        El contenedor en static, permite que se acceda a el en las diferentes clases.
        Esto para permitir que se repinten las imagenes que utilizamos
        
        En el try{}catch(){} se leen las rutas de donde estan guardadas las imágenes, y si existe
        algún tipo de error se debe a que no se encontraron  las imágenes
    */
    public RompecabezasMercury13() {
    
        this.setTitle("Wictory 13");
        this.setSize(415,440);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        contenedor = this.getContentPane();
        
        try {
            imgF = ImageIO.read(ImageLoader.class.getResource("/rompecabezas/rompecabezaFacil.png"));
            imgI = ImageIO.read(ImageLoader.class.getResource("/rompecabezas/rompecabezaIntermedio.png"));
            imgD = ImageIO.read(ImageLoader.class.getResource("/rompecabezas/rompecabezaDificil.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Crear tablero de acuerdo con la dificultad
        ejecutar(menuR.i);
    }
    public void ejecutar(int nivel){
        if (nivel == 1) {
            tablero = new Tablero(3,imgF);
        }else{
            if (nivel == 2) {
                tablero = new Tablero(4,imgI);
            }else{
                if (nivel == 3) {
                    tablero = new Tablero(5,imgD);
                }
            }
        }
        contenedor.add(tablero);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menú().setVisible(true);
            }
        });
    }

}
