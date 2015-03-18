import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class Galleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galleta
{
    // Posicion de inicio en X
    private int posX;
    // Posicion de inicio en Y
    private int posY;
    // Valor en puntos de la galleta
    private int puntos;
    // El canvas donde se va a dibujar
    private Canvas canvas;
    // Tamaño de la galleta
    private int lado;

    /**
     * Constructor for objects of class Galleta
     */
    public Galleta(int posX, int posY, int lado, Canvas canvas)
    {
       // Inicializa la posicion de la galleta y el canvas
       this.posX = posX;
       this.posY = posY;
       this.canvas = canvas;
       this.lado = lado;
       this.puntos = lado;
    }

    /**
     * Metodo que pinta la galleta
     */
    public void drawGalleta()
    {
        canvas.setForegroundColor(Color.BLACK);
        canvas.fillRectangle(posX-lado/2, posY-lado/2, lado, lado);
    }
    
    /**
     * Metodo que devuelve la posicion en X de la galleta
     * @return La posicion en X de la galleta
     */
    public int getXPos()
    {
        return posX;
    }
    
    /**
     * Metodo que devuelve la posicion en Y de la galleta
     * @return La posicion en Y de la galleta
     */
    public int getYPos()
    {
        return posY;
    }
    
    /**
     * Metodo que devuelve el lado de la galleta
     * @return El tamaño del lado de la galleta
     */
    public int getLado()
    {
        return lado;
    }
    
    /**
     * Metodo que devuelve los puntos obtenidos por comer esta gallera
     * @return Los puntos que vale esta galleta
     */
    public int getPuntos()
    {
        return puntos;
    }
    
}
