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
    // El tamaño debe ir en relacion con el tamaño del canvas, de lo contrario
    // las galletas no se generan en posiciones validas, deben ser multiplos
    // del tamaño del canvas
    public static final int SIZE = 25;
    private int puntuacion;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int xPos, int yPos, Canvas can)
    {
        // initialise instance variables
        rand = new Random();
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        serpiente = new ArrayList<Segmento>();
        canvas = can;
        drawSegment(xPos, yPos, 0);
        drawSegment(xPos - SIZE, yPos, 0);
        drawSegment(xPos - SIZE*2, yPos, 0);
        puntuacion = 0;
    }

    /**
     * Metodo que devuelve el arraylist de segmentos que componen la serpiente
     * @return El arraylist de segmentos que componen la serpiente
     */
    public ArrayList<Segmento> getSerpiente()
    {
        return serpiente;
    }

    /**
     * Metodo que devuelve el color de la serpiente
     * @return el color de la serpiente
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Metodo que devuelve la puntuacion
     * @return La puntuacion de esta serpiente
     */
    public int getPuntuacion()
    {
        return puntuacion;
    }
    
    /**
     * Metodo que setea la puntuacion de la serpiente
     * @param La nueva puntuacion de la serpiente
     */
    public void setPuntuacion(int puntos)
    {
        puntuacion = puntos;
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
        // Pinta una cabeza a la serpiente en el ultimo segmento
        int PosX = serpiente.get(serpiente.size() -1).getXPosFinal() - 2;
        int PosY = serpiente.get(serpiente.size() -1).getYPosFinal() - 2;
        canvas.fillCircle(PosX, PosY, 4);
    }

    /**
     * Añade un nuevo segmento a la serpiente
     * @param num Numero de segmentos a añadir a la serpiente
     * @return Devuelve false si no puede dibujar el segmento
     */
    public boolean makeSnakeBigger(int num)
    {
        boolean pintar = true;
        int index = 0;
        while((index < num) && (pintar))
        {
            // Tomamos las coordenadas del ultimo segmento y su direccion
            int xPos = serpiente.get(serpiente.size()-1).getXPosFinal();
            int yPos = serpiente.get(serpiente.size()-1).getYPosFinal();
            int dir = serpiente.get(serpiente.size()-1).getDireccion();
            // Generamos la direccion del nuevo segmento
            int nuevaDir = generaDireccion(dir, xPos, yPos);
            // Comprobamos el valor de la direccion del nuevo segmento, si es valido lo dibuja
            if (nuevaDir != -1)
            {
                drawSegment(xPos, yPos, nuevaDir);
            } else
            {
                pintar = false;
            }
            index++;
        }
        return pintar;
    }

    /**
     * Elimina un segmento de la serpiente
     */
    public void remove()
    {
        serpiente.remove(0);
    }

    /**
     * Mueve la serpiente
     * @return true si se ha podido mover, false si no
     */
    public boolean move()
    {
        remove();
        boolean move = makeSnakeBigger(1);
        // draw again at new position
        draw();
        return move;
    }    

    /**
     * Metodo para generar una nueva direccion no coincidente con la introducida
     * @param Dir La dirección del segmento previo
     * @return Una nueva direccion no contraria a la introducida, -1 si no quedan
     * direcciones validas
     */
    private int generaDireccion(int dir, int xPos, int yPos)
    {
        int nuevaDir = 0;
        boolean coinciden = true;
        ArrayList<Integer> direcciones = new ArrayList<>();
        direcciones.add(new Integer(Segmento.IZQUIERDA));
        direcciones.add(new Integer(Segmento.DERECHA));
        direcciones.add(new Integer(Segmento.ABAJO));
        direcciones.add(new Integer(Segmento.ARRIBA));
        int posDireccion;
        while((coinciden) && (direcciones.size() > 0))
        {
            // Generamos una direccion entre las que queden en el arraylist
            posDireccion = rand.nextInt(direcciones.size());
            nuevaDir = direcciones.get(posDireccion);
            // Lo eliminamos de la lista
            direcciones.remove(posDireccion);
            // Comprobamos lo primero que no tome la direccion en la que venia el segmento,
            // y tambien que no choque con los lados del canvas, en ese caso deberia cambiar de direccion
            switch(dir)
            {
                case Segmento.IZQUIERDA:
                if(nuevaDir != Segmento.DERECHA)
                {
                    coinciden = (colisiona(nuevaDir, xPos, yPos));
                }
                break;
                case Segmento.DERECHA:
                if(nuevaDir != Segmento.IZQUIERDA)
                {
                    coinciden = (colisiona(nuevaDir, xPos, yPos));
                }
                break;
                case Segmento.ABAJO:
                if(nuevaDir != Segmento.ARRIBA)
                {
                    coinciden = (colisiona(nuevaDir, xPos, yPos));
                }
                break;
                case Segmento.ARRIBA:
                if(nuevaDir != Segmento.ABAJO)
                {
                    coinciden = (colisiona(nuevaDir, xPos, yPos));
                }
            }           
        }
        // Si no encontro direcciones validas, retornara -1
        if(coinciden)
        {
            nuevaDir = -1;
        }
        return nuevaDir;
    }

    /**
     * Metodo para comprobar si choca con los lados o consigo misma en esa direccion
     * @return True si no existe choque en esa direccion, false si no
     */
    private boolean colisiona(int dir, int xPos, int yPos)
    {
        boolean choca = false;
        // Calculamos la posicion final
        switch(dir)
        {
            case Segmento.IZQUIERDA:
            xPos -= SIZE; 
            break;
            case Segmento.DERECHA:
            xPos += SIZE;
            break;
            case Segmento.ABAJO:
            yPos += SIZE;
            break;
            case Segmento.ARRIBA:
            yPos -= SIZE;
        }
        // Comprobamos que esa posicion no sea conflictiva
        if (xPos >= canvas.getSize().getWidth() || yPos >= canvas.getSize().getHeight()
        || xPos <= 0 || yPos <= 0)
        {
            choca = true;
        }
        else
        {
            int index = 0;
            // Compruebo la posicion final de mi nuevo segmento con las posiciones iniciales y finales
            // de todos los segmentos, si alguna coincide, chocan
            while((index < (serpiente.size() - 1)) && !(choca))
            {
                Segmento temp = serpiente.get(index);
                if(((temp.getXPos() == xPos) && (temp.getYPos() == yPos)) 
                || ((temp.getXPosFinal() == xPos) && (temp.getYPosFinal() == yPos)))
                {
                    choca = true;
                }
                index++;
            }
        }
        return choca;
    }

    /**
     * Dibuja un nuevo segmento de la serpiente
     */
    private void drawSegment(int xPos, int yPos, int dir)
    {
        //Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        serpiente.add(new Segmento(xPos, yPos, SIZE, color, canvas, dir));
    }
}
