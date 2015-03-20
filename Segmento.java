import java.awt.*;
import java.awt.geom.*;

/**
 * Representa los segmentos que componen las serpientes
 * 
 * @author Julia Zuara
 * @version 1.0
 */
public class Segmento
{
    // Color del segmento
    private Color color;
    // Posicion inicial en x
    private int xPosition;
    // Posicion iniciar en y
    private int yPosition;
    // Tamaño del segmento
    private int size;
    // Canvas para dibujarse
    private Canvas canvas;
    // En este guardaremos la direccion en la que va el segmento, 
    // 0 izquierda, 1 derecha, 2 abajo y 3 arriba
    private int direccion;

    public final static int IZQUIERDA = 0;
    public final static int DERECHA = 1;
    public final static int ABAJO = 2;
    public final static int ARRIBA = 3;


    /**
     * Constructor for objects of class Segmento
     * @param xPos Posicion inicial en x del segmento
     * @param yPos Posicion inicial en y del segmento
     * @param siz Tamaño del segmento
     * @param color Color del segmento
     * @param canvas Canvas donde va a dibujarse el segmento
     * @param dir Direccion en la que va a dibujarse el segmento
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
     *  Metodo para dibujar el segmento
     */
    public void draw()
    {
        canvas.setForegroundColor(color);
        // Dependiendo de la direccion, pintamos el segmento
        switch(direccion)
        {
            case IZQUIERDA:
            canvas.drawLine(xPosition, yPosition, xPosition - size, yPosition);
            //canvas.fillRectangle(xPosition - size, yPosition, size, 5);
            break;
            case DERECHA:
            canvas.drawLine(xPosition, yPosition, xPosition + size, yPosition);
            //canvas.fillRectangle(xPosition, yPosition, size, 5);
            break;
            case ABAJO:
            canvas.drawLine(xPosition, yPosition, xPosition, yPosition + size);
            //canvas.fillRectangle(xPosition, yPosition, 5, size);
            break;
            case ARRIBA:
            canvas.drawLine(xPosition, yPosition, xPosition, yPosition - size);
            //canvas.fillRectangle(xPosition, yPosition-size, 5, size);
        }

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
        if(direccion == IZQUIERDA)
        {
            xPosFinal -= size;
        }
        else if(direccion == DERECHA)
        {
            xPosFinal += size;
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
        if(direccion == ARRIBA)
        {
            yPosFinal -= size;
        }
        else if(direccion == ABAJO)
        {
            yPosFinal += size;
        }
        return yPosFinal;
    }
}
