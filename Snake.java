import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    private Color color;
    private ArrayList<Segmento> serpiente;
    private Canvas canvas;
    
    /**
     * Constructor for objects of class Snake
     */
    public Snake(int xPos, int yPos, int size, Canvas can)
    {
        // initialise instance variables
        Random rand = new Random();
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        serpiente = new ArrayList<Segmento>();
        canvas = can;
        drawSegment(xPos, yPos, size, color, canvas);
        drawSegment(xPos + size, yPos, size, color, canvas);
        drawSegment(xPos + size*2, yPos, size, color, canvas);
    }

    /**
     * Añade un nuevo segmento a la serpiente
     * @return Devuelve false si no puede dibujar el segmento
     */
    public boolean makeSnakeBigger()
    {
        boolean pintar = true;
        // Compobamos lo primero si se puede dibujar. El tamaño del canvas
        // deberia venir por parametro.
        if((xPosition*(numSegmentos +1)*size) > canvas.getSize().getWidth())
        {
            pintar = false;
        }
        else
        {
            drawSegment(xPosition + numSegmentos*size, yPosition, size);
        }
        return pintar;
    }

    /**
     * Dibuja un segmento de la serpiente
     */
    private void drawSegment(int xPos, int yPos, int siz, Color color, Canvas canvas)
    {
        segmentos.add(new Segmento(xPos, yPos, siz, color, canvas));
    }
}
