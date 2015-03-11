import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    private int ballDegradation = 2;
    private Color color;
    private int xPosition;
    private int yPosition;
    private int size;
    private Canvas canvas; 

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int xPos, int yPos, int siz, Canvas canvas)
    {
        // initialise instance variables
        Random rand = new Random();
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.size = siz;
        this.canvas = canvas;
        drawSegment(xPosition, yPosition, size);
        drawSegment(xPosition + size, yPosition + size, size);
        drawSegment(xPosition + size*2, yPosition + size*2, size);
    }

    /**
     * Dibuja un segmento de la serpiente
     */
    private void drawSegment(int xPosition, int yPosition, int size)
    {
        canvas.setForegroundColor(color);
        canvas.drawLine(xPosition, yPosition, xPosition + size, yPosition + size);
    }
}
