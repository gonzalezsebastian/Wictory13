package rompecabezasmercury13;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @autor Sebastian Gonzalez
 */
public class Tablero extends JPanel{
    /*
        Tablero estático de 2 dimensiones
        Tendra los indices y la figura del rompecabezas
        La figura tiene la posición actual, y la posición que le corresponde en la solución
        Y tiene una subImage de la imagen de solución
    */
    public static Celda[][] tablero;
    private ArrayList<Celda> tableroCompleto = new ArrayList<Celda>();
    private int dimension;
    private int x,y; // Cortar la imagen para cada casilla
    private int casillaAlto,casillaAncho;
    private JLabel espacio;
    
    public Tablero(int dimension, BufferedImage img) {
        
        this.dimension = dimension;
        tablero = new Celda[dimension][dimension];
        this.setBackground(Color.BLACK);
        
        x = 0; 
        y = 0; 
        
        casillaAncho = img.getWidth()/dimension;
        casillaAlto = img.getHeight()/dimension;
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        
        //Rellenar las imagenes de las casillas
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                //Dejar el espacio vacio
                if (i == dimension -1 && j == dimension - 1) {
                    continue; // Un break
                }
                tableroCompleto.add(new Celda(i,j,new Figura(i,j,new ImageIcon(img.getSubimage(x, y, casillaAncho, casillaAlto)),dimension)));
                x += casillaAncho;
            }
            x = 0;
            y += casillaAlto;
        }
        Desordenar();
        
        Remover();
    }
    /*
        Desordena las fichas para que el usuario deba armar el rompecabezas
    */
    private void Desordenar() {
        
        Random generador = new Random();
        ArrayList<Celda> tempCasilla = new ArrayList<Celda>(tableroCompleto);
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == dimension - 1 && j == dimension - 1) {
                    tablero[i][j] = new Celda(i,j);
                    continue;
                }
                int aleatorio = generador.nextInt(tableroCompleto.size());
                tableroCompleto.get(aleatorio).getFigura().setPosX(i);
                tableroCompleto.get(aleatorio).getFigura().setPosY(j);
                tablero[i][j] = new Celda(i,j,tableroCompleto.get(aleatorio).getFigura());
                tableroCompleto.remove(aleatorio);
            }   
        }
        // Se hace para asignarle al tablero las nuevas posiciones, despues del movimiento de las piezas
        tableroCompleto = tempCasilla;
        Remover();
    }
    /*
        Actualiza el tablero con las nuevas posiciones de las fichas
    */
    private void Actualizar(){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j].getFigura() == null) {
                    espacio = new JLabel();
                    espacio.setPreferredSize(new Dimension(casillaAncho,casillaAlto));
                    this.add(espacio);
                    continue;
                }
                this.add(tablero[i][j].getFigura());
            }
        }
        RompecabezasMercury13.contenedor.validate();
    }
    /*
        Sirve para remover el tablero anterior y lo actualiza con la información luego del movimiento
    */
    public void Remover() {
        this.removeAll();
        Actualizar();
    }
    
}
