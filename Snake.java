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
    private Random rand;
    private int size;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int xPos, int yPos, int size, Canvas can)
    {
        // initialise instance variables
        rand = new Random();
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        serpiente = new ArrayList<Segmento>();
        canvas = can;
        this.size = size;
        drawSegment(xPos, yPos, 0);
        drawSegment(xPos - size, yPos, 0);
        drawSegment(xPos - size*2, yPos, 0);
    }

    /**
     * Pinta los segmentos de la serpiente
     */
    public void draw()
    {
        for(int i = 0; i < serpiente.size(); i++)
        {
            serpiente.get(i).draw();
        }
    }

    /**
     * Añade un nuevo segmento a la serpiente
     * @return Devuelve false si no puede dibujar el segmento
     */
    public boolean makeSnakeBigger()
    {
        boolean pintar = true;
        // Tomamos las coordenadas del ultimo segmento y su direccion
        int xPos = serpiente.get(serpiente.size()-1).getXPosFinal();
        int yPos = serpiente.get(serpiente.size()-1).getYPosFinal();
        int dir = serpiente.get(serpiente.size()-1).getDireccion();
        // Generamos la direccion del nuevo segmento
        int nuevaDir = generaDireccion(dir, xPos, yPos);
        // Comprobamos si existe colision
        if(comprobarPosiciones(xPos,yPos, nuevaDir))
        {
            pintar = false;
            drawSegment(xPos, yPos, nuevaDir);
        }
        else
        {
            drawSegment(xPos, yPos, nuevaDir);
        }

        return pintar;
    }

    /**
     * Metodo para generar una nueva direccion no coincidente con la introducida
     * @param Dir La dirección del segmento previo
     * @return Una nueva direccion no contraria a la introducida
     */
    private int generaDireccion(int dir, int xPos, int yPos)
    {
        int nuevaDir = 0;
        boolean coinciden = true;

        while(coinciden)
        {
            nuevaDir = rand.nextInt(4);
            // Comprobamos lo primero que no tome la direccion en la que venia el segmento,
            // y tambien que no choque con los lados del canvas, en ese caso deberia cambiar de direccion
            switch(dir)
            {
                case Segmento.IZQUIERDA:
                if(nuevaDir != Segmento.DERECHA)
                {
                    coinciden = (lados(dir, xPos, yPos));
                }
                break;
                case Segmento.DERECHA:
                if(nuevaDir != Segmento.IZQUIERDA)
                {
                    coinciden = (lados(dir, xPos, yPos));
                }
                break;
                case Segmento.ABAJO:
                if(nuevaDir != Segmento.ARRIBA)
                {
                    coinciden = (lados(dir, xPos, yPos));
                }
                break;
                case Segmento.ARRIBA:
                if(nuevaDir != Segmento.ABAJO)
                {
                    coinciden = (lados(dir, xPos, yPos));
                }
            }
            
        }
        return nuevaDir;
    }

    /**
     * Metodo para comprobar si choca con los lados en esa direccion
     * @return True si no existe choque en esa direccion, false si no
     */
    private boolean lados(int dir, int xPos, int yPos)
    {
        boolean choca = false;
        // Calculamos la posicion final
        switch(dir)
        {
            case Segmento.IZQUIERDA:
            xPos -= size; 
            break;
            case Segmento.DERECHA:
            xPos += size;
            break;
            case Segmento.ABAJO:
            yPos += size;
            break;
            case Segmento.ARRIBA:
            yPos -= size;
        }
        // Comprobamos que esa posicion no sea conflictiva
        if (xPos >= canvas.getSize().getWidth() || yPos >= canvas.getSize().getHeight()
        || xPos <= 0 || yPos <= 0)
        {
            choca = true;
        }
        return choca;
    }

    /**
     * Metodo para comprobar las posiciones de la serpiente
     * @return True si alguno de los segmentos colisiona con las coordenadas introducidas
     */
    private boolean comprobarPosiciones(int xPos, int yPos, int dir)
    {
        boolean colisionan = false;
        int index = 0;
        // Cambiamos las posiciones para que sean las finales
        switch(dir)
        {
            case Segmento.IZQUIERDA:
            xPos -= size; 
            break;
            case Segmento.DERECHA:
            xPos += size;
            break;
            case Segmento.ABAJO:
            yPos += size;
            break;
            case Segmento.ARRIBA:
            yPos -= size;
        }
        // Compruebo la posicion final de mi nuevo segmento con las posiciones iniciales y finales
        // de todos los segmentos, si alguna coincide, chocan
        while((index < (serpiente.size() - 1)) && !(colisionan))
        {
            Segmento temp = serpiente.get(index);
            if(((temp.getXPos() == xPos) && (temp.getYPos() == yPos)) 
            || ((temp.getXPosFinal() == xPos) && (temp.getYPosFinal() == yPos)))
            {
                colisionan = true;
            }
            index++;
        }
        return colisionan;
    }

    /**
     * Dibuja un nuevo segmento de la serpiente
     */
    private void drawSegment(int xPos, int yPos, int dir)
    {
        //Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        serpiente.add(new Segmento(xPos, yPos, size, color, canvas, dir));
    }
}
