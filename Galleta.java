
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
    public final static int PUNTOS = 5;
    // El canvas donde se va a dibujar
    private Canvas canvas;
    // Tamaño de la galleta
    public final static int LADO = 5;

    /**
     * Constructor for objects of class Galleta
     */
    public Galleta(int posX, int posY, Canvas canvas)
    {
       // Inicializa la posicion de la galleta y el canvas
       this.posX = posX;
       this.posY = posY;
       this.canvas = canvas;
    }

    /**
     * Metodo que pinta la galleta
     */
    public void drawGalleta()
    {
        canvas.fillRectangle(posX-LADO/2, posY-LADO/2, LADO, LADO);
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
    
}
