import java.awt.*;
import java.awt.geom.*;

/**
 * Write a description of class Segmento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Segmento
{
    private Color color;
    private int xPosition;
    private int yPosition;
    private int size;
    private Canvas canvas;
    // En este guardaremos la direccion en la que va el segmento, 
    // 0 izquierda, 1 derecha, 2 abajo y 3 arriba
    private int direccion;

    /**
     * Constructor for objects of class Segmento
     */
    public Segmento(int xPos, int yPos, int siz, Color color, Canvas canvas, int dir)
    {
        // initialise instance variables
        this.color = color;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.size = siz;
        this.canvas = canvas;
        direccion = dir;
    }

    /**
     *  Metodo para dibujar el segmento. Comprueba si toca los bordes
     *  @return True si se puede dibujar, false si no
     */
    public boolean draw()
    {
        boolean pintar = true;
        if(xPosition >= canvas.getSize().getWidth() || yPosition >= canvas.getSize().getHeight()
        || xPosition <= 0 || yPosition <= 0)
        {
            pintar = false;
        }
        else
        {
            canvas.setForegroundColor(color);
            // Dependiendo de la direccion, pintamos el segmento
            switch(direccion)
            {
                case 0:
                canvas.drawLine(xPosition, yPosition, xPosition + size, yPosition);
                break;
                case 1:
                canvas.drawLine(xPosition, yPosition, xPosition - size, yPosition);
                break;
                case 2:
                canvas.drawLine(xPosition, yPosition, xPosition, yPosition - size);
                break;
                case 3:
                canvas.drawLine(xPosition, yPosition, xPosition, yPosition + size);
            }
        }

        return pintar;
    }

    /**
     * Devuelve la posicion en x del segmento
     * @return La posicion en x del segmento
     */
    public int getXPos()
    {
        return xPosition;
    }

    /**
     * Devuelve la posicion en y del segmento
     * @return La posicion en y del segmento
     */
    public int getYPos()
    {
        return yPosition;
    }

    /**
     * Devuelve el tamaño del segmento
     * @return El tamaño del segmento
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Devuelve la direccion en la que se encuentra el segmento
     * @return La direccion del segmento
     */
    public int getDireccion()
    {
        return direccion;
    }
    
    /**
     * Devuelve la posicion final en x del segmento
     * @return La posicion final en x del segmento
     */
    public int getXPosFinal()
    {
        int xPosFinal = xPosition;
        if(direccion == 0)
        {
            xPosFinal += size;
        }
        else if(direccion == 1)
        {
            xPosFinal -= size;
        }
        return xPosFinal;
    }
    
        /**
     * Devuelve la posicion final en y del segmento
     * @return La posicion final en y del segmento
     */
    public int getYPosFinal()
    {
        int yPosFinal = yPosition;
        if(direccion == 3)
        {
            yPosFinal += size;
        }
        else if(direccion == 2)
        {
            yPosFinal -= size;
        }
        return yPosFinal;
    }
}
