import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class SnakeGameDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeGameDemo
{
    // 
    private Canvas myCanvas;
    private Snake snake;
    private ArrayList<Galleta> galletas;

    /**
     * Constructor for objects of class SnakeGameDemo
     */
    public SnakeGameDemo()
    {
        myCanvas = new Canvas("Snake Game Demo", 500, 500);
        snake = new Snake(350, 350, myCanvas);
        galletas = new ArrayList<Galleta>();
    }

    /**
     * Metodo que dibuja una serpiente formada por tres segmentos
     */
    public void drawSnake()
    {
        myCanvas.setVisible(true);
        snake.draw();
    }

    /**
     * Añade un segmento a la serpiente
     */
    public boolean makeSnakeBigger()
    {
        boolean dibujado = (snake.makeSnakeBigger(1));
        return dibujado;
    }

    /**
     * Añade varios segmentos a la serpiente
     * @param num Numero de segmentos a añadir a la serpiente
     */
    public boolean makeSnakeBigger(int num)
    {
        boolean dibujado = (snake.makeSnakeBigger(num));
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
        boolean moverse = true;
        boolean galletaComida = false;
        while(moverse)
        {
            myCanvas.wait(100);
            // remove from canvas
            erase();
            moverse = snake.move();
            galletaComida = compruebaGalletas();
            if(galletaComida)
            {
                snake.setPuntuacion(snake.getPuntuacion() + Galleta.PUNTOS);
                makeSnakeBigger();
            }
            // Pinta las galletas
            pintaGalletas();
            drawPuntos();
        }
        return moverse;
    }

    /**
     * Metodo que inicia el juego de la serpiente
     */
    public void startGame()
    {
        // Crea un numero de galletas aleatorias
        Random rand = new Random();
        int numeroGalletas = rand.nextInt(50) + 20;
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
     */
    public Galleta creaGalleta()
    {
        Galleta galleta = null;
        // Creamos un random para generar coordenadas
        Random rand = new Random();
        int coordX = 0;
        int coordY = 0;
        boolean validas = true;
        boolean seleccionadas = false;
        // Comprueba si las coordenadas las tiene la serpiente u otra galleta. Usa un indice para darle
        // una salida posible y evitar bucles infinitos si se queda sin coordenadas validas
        int indice = 0;
        while(!seleccionadas && indice < 10)
        {
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
                // Recorre cada segmento de la serpiente, y comprueba los puntos inicial y final
                ArrayList<Segmento> serpiente = new ArrayList<Segmento>(snake.getSerpiente());
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
            }
            indice++;
            seleccionadas = validas;
        }
        if(seleccionadas)
        {
            galleta = new Galleta(coordX, coordY, myCanvas);
        }
        return galleta;
    }

    private void pintaGalletas()
    {
        for(int i = 0; i < galletas.size(); i++)
        {
            galletas.get(i).drawGalleta();
        }
    }

    private boolean compruebaGalletas()
    {
        boolean galletaComida = false;
        if((snake != null) && (galletas.size() > 0))
        {
            // Toma el ultimo segmento de la serpiente
            ArrayList<Segmento> serpiente = new ArrayList<Segmento>(snake.getSerpiente());
            Segmento segmento = serpiente.get(serpiente.size() - 1);
            int index = 0;
            // Recorremos la arraylist de galletas para ver si concide alguna coordenada
            while(index < galletas.size() && !(galletaComida))
            {
                Galleta galleta = galletas.get(index);
                if(((segmento.getXPos() == galleta.getXPos()) && (segmento.getYPos() == galleta.getYPos())) ||
                ((segmento.getXPosFinal() == galleta.getXPos()) && (segmento.getYPosFinal() == galleta.getYPos())))
                {
                    galletaComida = true;
                    galletas.remove(galleta);
                }
                index++;
            }
        }
        return galletaComida;
    }

    public void gameOver()
    {
        myCanvas.drawString("GAME OVER",220, 240);
    }

    public void drawPuntos()
    {
        String puntos = "Puntos: " + snake.getPuntuacion();
        myCanvas.drawString(puntos, 10, 20);
    }
}
