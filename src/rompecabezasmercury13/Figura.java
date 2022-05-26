package rompecabezasmercury13;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @autor Sebastian Gonzalez
 */
public class Figura extends JButton implements ActionListener{

    private final int solPosx,solPosy;
    private int posX,posY; // Posiciones actuales en el rompecabezas
    private int dimension;
    
    public Figura(int solPosx, int solPosy, ImageIcon subImagen,int dimension) {
       
        this.solPosx = solPosx;
        this.solPosy = solPosy;
        this.dimension = dimension;
        
        // Se les asigna el valor de la posición de la solución, y luego se revuelve el tablero
        posX = solPosx;
        posY = solPosy;
        
        this.setIcon(subImagen);
        this.setPreferredSize(new Dimension(subImagen.getIconWidth(),subImagen.getIconHeight()));
        this.addActionListener(this);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSolPosx() {
        return solPosx;
    }

    public int getSolPosy() {
        return solPosy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Mover();
    }
    
    /*
        Se controla el movimiento de las piezas, por cada fila y por cada columna
        verificando hacia donde debe hacer el movimiento. Si hacia arriba, abajo,
        izquierda o derecha. Cada vez que realiza el movimiento, también se verifica
        si el rompecabezas fue solucionado.
    */
    
    private void Mover() {
        Celda[][]tablero = Tablero.tablero;
        try{
        if (tablero[posX][posY - 1].getFigura() == null) { //Arriba
            Tablero.tablero[posX][posY - 1].setFigura(this);
            Tablero.tablero[posX][posY].setFigura(null);
            posY--;
            RompecabezasMercury13.tablero.Remover();
            checkResuelto();
            return;
        }
        }catch(ArrayIndexOutOfBoundsException e){

        }
        try{
        if (tablero[posX][posY + 1].getFigura() == null) {
            Tablero.tablero[posX][posY + 1].setFigura(this);
            Tablero.tablero[posX][posY].setFigura(null);
            posY++;
            RompecabezasMercury13.tablero.Remover();
            checkResuelto();
            return;
        }
        }catch(ArrayIndexOutOfBoundsException e){

        }
        try{
        if (tablero[posX - 1][posY].getFigura() == null) {//Izquierda
            Tablero.tablero[posX - 1][posY].setFigura(this);
            Tablero.tablero[posX][posY].setFigura(null);
            posX--;
            RompecabezasMercury13.tablero.Remover();
            checkResuelto();
            return;
        }
        }catch(ArrayIndexOutOfBoundsException e){
            
        }
        try{
        if (tablero[posX + 1][posY].getFigura() == null) {
            Tablero.tablero[posX + 1][posY].setFigura(this);
            Tablero.tablero[posX][posY].setFigura(null);
            posX++;
            RompecabezasMercury13.tablero.Remover();
            checkResuelto();
            return;
        }
        }catch(ArrayIndexOutOfBoundsException e){

        }
    }
    /*
        Se verifica si el rompecabezas se completó exitosamente
        Se mueve por cada fila y columna verificando que la posición actual de las piezas
        sea la de la solución.
        Si ese es el caso, saldrá un mensaje diciendole al usuario que lo ha completado.
    */
    private void checkResuelto() {
        Figura figura = null;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                figura = Tablero.tablero[i][j].getFigura();
                if (figura == null) {
                    continue;
                }
                if (figura.getPosX() != figura.getSolPosx() || figura.getPosY() != figura.getSolPosy()){
                    return;
                }
            }
        }
        // Tablero Completado
        JOptionPane.showMessageDialog(new JPanel(), "Rompecabezas completado!","Wictory 13",JOptionPane.INFORMATION_MESSAGE);
    }
    
}
