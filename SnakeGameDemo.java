
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
        snake = new Snake(350, 350, 50, myCanvas);
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
        boolean dibujado = (snake.makeSnakeBigger());
        snake.draw();
        return dibujado;
    }

    public void erase(){
        myCanvas.erase();
    }
}
