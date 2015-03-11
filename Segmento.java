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
    private boolean dirArriba;
    private boolean dirIzquierda;

    /**
     * Constructor for objects of class Segmento
     */
    public Segmento(int xPos, int yPos, int siz, Color color, Canvas canvas)
    {
        // initialise instance variables
        this.color = color;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.size = siz;
        this.canvas = canvas;
        canvas.setForegroundColor(color);
        canvas.drawLine(xPosition, yPosition, xPosition + size, yPosition);
    }
    
}
