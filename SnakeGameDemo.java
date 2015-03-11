
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

    /**
     * Constructor for objects of class SnakeGameDemo
     */
    public SnakeGameDemo()
    {
        myCanvas = new Canvas("Snake Game Demo", 500, 500);
    }

    /**
     * Metodo que dibuja una serpiente formada por tres segmentos
     */
    public void drawSnake()
    {
        myCanvas.setVisible(true);
        Snake snake = new Snake(250, 250, 10, myCanvas);
        snake.makeSnakeBigger();
        snake.makeSnakeBigger();
        snake.makeSnakeBigger();
        snake.makeSnakeBigger();
        snake.makeSnakeBigger();
        snake.makeSnakeBigger();
        snake.draw();
    }
}
