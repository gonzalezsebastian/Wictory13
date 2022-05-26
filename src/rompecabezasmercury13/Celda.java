package rompecabezasmercury13;

/**
 *
 * @autor Sebastian Gonzalez
 */
public class Celda {
    private final int x,y;
    private Figura figura;
// Contiene los indices y la figura
    public Celda(int x, int y, Figura figura) {
        this.x = x;
        this.y = y;
        this.figura = figura;
    }
// Debido a que existe una casilla vacia, por lo que Figura, en ese espacio, es nula
    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        figura = null;
    }
    // Getters y setters de las casillas/celdas
    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
