
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

    /**
     * Constructor for objects of class SnakeGameDemo
     */
    public SnakeGameDemo()
    {
        myCanvas = new Canvas("Snake Game Demo", 500, 500);
        snake = new Snake(350, 350, myCanvas);
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
        myCanvas.setVisible(true);
        boolean dibujado = (snake.makeSnakeBigger(1));
        snake.draw();
        return dibujado;
    }
    
    /**
     * Añade varios segmentos a la serpiente
     * @param num Numero de segmentos a añadir a la serpiente
     */
    public boolean makeSnakeBigger(int num)
    {
        myCanvas.setVisible(true);
        boolean dibujado = (snake.makeSnakeBigger(num));
        snake.draw();
        return dibujado;
    }

    public void erase(){
        myCanvas.erase();
    }
    
    /**
     * Metodo que mueve la serpiente por pantalla
     */
    public void animateSnake()
    {
        boolean moverse = true;
        while(moverse)
        {
            myCanvas.wait(300);
            moverse = snake.move();
        }
    }
    
    public void gameOver()
    {
        myCanvas.drawString("GAME OVER",220, 240);
    }
}
