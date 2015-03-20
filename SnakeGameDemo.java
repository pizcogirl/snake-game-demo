import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

/**
 * Representa una demo del juego de las serpientes
 * 
 * @author Julia Zuara
 * @version 1.0
 */
public class SnakeGameDemo
{
    // El lienzo
    private Canvas myCanvas;
    // ArrayList de serpientes
    private ArrayList<Snake> snake;
    // ArrayList de galletas
    private ArrayList<Galleta> galletas;
    // Random para generar coordenadas y otros aleatorios en la clase
    private Random rand;

    /**
     * Constructor for objects of class SnakeGameDemo
     * 
     */
    public SnakeGameDemo()
    {
        myCanvas = new Canvas("Snake Game Demo", 500, 500);
        snake = new ArrayList<Snake>();
        // snake = new Snake(350, 350, myCanvas);
        galletas = new ArrayList<Galleta>();
        rand = new Random();
    }

    /**
     * Metodo que dibuja una serpiente formada por tres segmentos
     */
    public void drawSnake()
    {
        if(snake.size() > 0)
        {
            myCanvas.setVisible(true);
            for(int i = 0; i < snake.size(); i++)
            {
                snake.get(i).draw();
            }
        }
    }

    /**
     * Añade un segmento a la serpiente
     * @return True si se ha podido aumentar el tamaño, false si no
     */
    public boolean makeSnakeBigger()
    {
        boolean dibujado = false;
        if(snake.size() > 0)
        {
            for(int i = 0; i < snake.size(); i++)
            {
                dibujado = snake.get(i).makeSnakeBigger(1);
            }

        }
        return dibujado;
    }

    /**
     * Añade varios segmentos a la serpiente
     * @param num Numero de segmentos a añadir a la serpiente
     * @return true si puede crecer, false si no
     */
    public boolean makeSnakeBigger(int num)
    {
        boolean dibujado = false;
        if(snake.size() > 0)
        {
            for(int i = 0; i < snake.size(); i++)
            {
                dibujado = snake.get(i).makeSnakeBigger(num);
            }

        }
        return dibujado;
    }

    public void erase(){
        myCanvas.erase();
    }

    /**
     * Metodo que mueve la serpiente por pantalla
     * @return True mientras pueda moverse, false si no
     */
    public boolean animateSnake()
    {
        boolean moverse = false;
        int index = 0;
        if(snake.size() > 0)
        {
            moverse = true;
            Galleta galletaComida = null;
            while(moverse)
            {
                myCanvas.wait(100);
                // remove from canvas
                erase();
                while(moverse && index < snake.size())
                {
                    moverse = snake.get(index).move();

                    galletaComida = compruebaGalletas(snake.get(index));
                    if(galletaComida != null)
                    {
                        // Si comio la galleta le suma los puntos, incrementa el tamaño de la serpiente
                        // y quita la galleta
                        snake.get(index).setPuntuacion(snake.get(index).getPuntuacion() + galletaComida.getPuntos());
                        snake.get(index).makeSnakeBigger(1);
                        galletaComida = null;
                    }
                    drawSnake();
                    index++;
                }
                index = 0;
                // Pinta las galletas y los puntos
                pintaGalletas();
                drawPuntos();

            }
        }
        return moverse;
    }

    /**
     * Metodo que inicia el juego de la serpiente
     * @param numSerpientes Numero de serpientes a añadir al juego
     */
    public void startGame(int numSerpientes)
    {
        // Genera las coordenadas para la serpiente, seran siempre coordenadas validas
        int tempX = (int)myCanvas.getSize().getWidth() - Snake.SIZE*2 - 1;
        int tempY = (int)myCanvas.getSize().getHeight() - Snake.SIZE*2 - 1;
        int coordX = rand.nextInt(tempX) + Snake.SIZE*2;
        int coordY = rand.nextInt(tempY) + Snake.SIZE*2;
        // Deben ser multiplos del size de los segmentos
        coordX = coordX - (coordX%Snake.SIZE);
        coordY = coordY - (coordY%Snake.SIZE);
        // Crea las serpientes indicadas
        for(int i = 0; i < numSerpientes; i++)
        {
            Snake newSnake = new Snake(coordX, coordY, myCanvas);
            newSnake.makeSnakeBigger(2);
            snake.add(newSnake);
        }
        // Crea un numero de galletas aleatorias
        int numeroGalletas = rand.nextInt(80) + (10*snake.size());
        Galleta galleta;
        for(int i = 0; i < numeroGalletas; i++)
        {
            galleta = creaGalleta();
            if(galleta != null)
            {
                galletas.add(galleta);
            }
        }
        // Comienza a moverse la serpiente
        boolean juega = true;
        while(juega)
        {
            juega = animateSnake();
        }
        gameOver();
    }

    /**
     * Metodo que crea una galleta de forma aleatoria, en posiciones validas
     * @return La galleta creada si puede crearla, devolvera una galleta null si no
     */
    public Galleta creaGalleta()
    {
        Galleta galleta = null;
        // Genera coordenadas
        int coordX = 0;
        int coordY = 0;
        int lado;
        boolean validas = true;
        boolean seleccionadas = false;
        // Comprueba si las coordenadas las tiene la serpiente u otra galleta. Usa un indice para darle
        // una salida posible y evitar bucles infinitos si se queda sin coordenadas validas
        int indice = 0;
        while(!seleccionadas && indice < 10)
        {
            validas = true;
            // Genera coordenadas para X e Y
            int tempX = (int)myCanvas.getSize().getWidth() - Snake.SIZE - 1;
            int tempY = (int)myCanvas.getSize().getHeight() - Snake.SIZE - 1;
            coordX = rand.nextInt(tempX) + Snake.SIZE;
            coordY = rand.nextInt(tempY) + Snake.SIZE;
            // Deben ser multiplos del size de los segmentos, para que la serpiente pueda comerlas
            coordX = coordX - (coordX%Snake.SIZE);
            coordY = coordY - (coordY%Snake.SIZE);
            // Ahora comprueba que no se genere en ninguna posicion de la serpiente, ni en ninguna
            // posicion de las galletas
            int index = 0;
            // Bucle para las galletas
            while(index < galletas.size() && (validas))
            {
                Galleta temp = galletas.get(index);
                if((temp.getXPos() == coordX) && (temp.getYPos() == coordY))
                {
                    validas = false;
                }
                index++;
            }
            // Comprueba la serpiente
            index = 0;
            if(snake != null && validas)
            {
                int indiSerp = 0;
                // Recorre todas las serpientes
                while(indiSerp < snake.size() && (validas))
                {
                    // Recorre cada segmento de la serpiente, y comprueba los puntos inicial y final
                    ArrayList<Segmento> serpiente = new ArrayList<Segmento>(snake.get(indiSerp).getSerpiente());
                    while(index < serpiente.size() && (validas))
                    {
                        Segmento segmento = serpiente.get(index);
                        if(((segmento.getXPos() == coordX) && (segmento.getYPos() == coordY)) ||
                        ((segmento.getXPosFinal() == coordX) && (segmento.getYPosFinal() == coordY)))
                        {
                            validas = false;
                        }
                        index++;
                    }
                    indiSerp++;
                }
            }
            indice++;
            seleccionadas = validas;
        }
        if(seleccionadas)
        {
            lado = rand.nextInt(Snake.SIZE/2) + 1;
            galleta = new Galleta(coordX, coordY, lado, myCanvas);
        }
        return galleta;
    }

    /**
     * Metodo que pinta las galletas por pantalla
     */
    private void pintaGalletas()
    {
        for(int i = 0; i < galletas.size(); i++)
        {
            galletas.get(i).drawGalleta();
        }
    }

    /**
     * Metodo que comprueba si existe una galleta para comerse en la posicion de la serpiente
     * @param La serpiente a comprobar
     * @return Si se come una galleta, la galleta, sino devuelve null
     */
    private Galleta compruebaGalletas(Snake serp)
    {
        Galleta galletaComida = null;
        if((snake != null) && (galletas.size() > 0))
        {
            // Toma el ultimo segmento de la serpiente
            ArrayList<Segmento> serpiente = new ArrayList<Segmento>(serp.getSerpiente());
            Segmento segmento = serpiente.get(serpiente.size() - 1);
            int index = 0;
            // Recorre la arraylist de galletas para ver si concide alguna coordenada
            while(index < galletas.size() && (galletaComida == null))
            {
                Galleta galleta = galletas.get(index);
                if(((segmento.getXPos() == galleta.getXPos()) && (segmento.getYPos() == galleta.getYPos())) ||
                ((segmento.getXPosFinal() == galleta.getXPos()) && (segmento.getYPosFinal() == galleta.getYPos())))
                {
                    // Guarda la galleta que se come y la borra
                    galletaComida = galleta;
                    galletas.remove(galleta);
                }
                index++;
            }
        }
        return galletaComida;
    }

    /**
     * Muestra el mensaje de game over
     */
    public void gameOver()
    {
        // Setea un color, busca un punto medio en el canvas, y dibuja el mensae
        myCanvas.setForegroundColor(Color.RED);
        int posX = (int)(myCanvas.getSize().getWidth())/2 - 30;
        int posY = (int)(myCanvas.getSize().getHeight())/2 - 10;
        myCanvas.drawString("GAME OVER",posX, posY);
    }

    /**
     * Muestra los puntos de las serpientes
     */
    public void drawPuntos()
    {
        // Toma una posicion inicial
        String puntos = "";
        int posY = 20;
        // Para cada serpiente los coloca seguidos hacia abajo
        for(int i = 0; i < snake.size(); i++)
        {
            myCanvas.setForegroundColor(snake.get(i).getColor());
            puntos = "Puntos: " + snake.get(i).getPuntuacion();
            posY = 20 + 20*i;
            myCanvas.drawString(puntos, 10, posY);
        }
    }

    /**
     * Reinicia el snakeGameDemo para poder volver a jugar
     */
    public void reset()
    {
        erase();
        snake = new ArrayList<Snake>();
        galletas = new ArrayList<Galleta>();
    }
}
