import java.awt.event.KeyEvent;

public class Game {

    private int height;
    private int width;
    private Snake snake;
    private Target target;
    public static Game game;

    public Game(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Snake getSnake() {
        return snake;
    }

    public void run() throws InterruptedException {
        KeyBoardHandler keyBoardHandler = new KeyBoardHandler();
        keyBoardHandler.start();


        while (snake.isAlive()) {

            if (keyBoardHandler.hasKeyEvents()) {
                KeyEvent event = keyBoardHandler.getEventFromTop();

                if (event.getKeyChar() == 'q') return;

                if (event.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != Direction.RIGHT)
                    snake.setDirection(Direction.LEFT);
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.LEFT)
                    snake.setDirection(Direction.RIGHT);
                else if (event.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != Direction.DOWN)
                    snake.setDirection(Direction.UP);
                else if (event.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != Direction.UP)
                    snake.setDirection(Direction.DOWN);
            }

            snake.move();
            print();
            Thread.sleep(snake.snakeBody.size()*20 < 300 ? 520-snake.snakeBody.size()*20 : 200);
        }

        System.out.println("GAME OVER");
    }

    public void print() {
        Character[][] field = new Character[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = '.';
            }
        }

        field[target.getY()][target.getX()] = 'o';

        for (int i = 0; i < snake.snakeBody.size(); i++ ) {
            if (i == 0) field[snake.snakeBody.get(i).getY()][snake.snakeBody.get(i).getX()] = '@';
            else field[snake.snakeBody.get(i).getY()][snake.snakeBody.get(i).getX()] = 'x';
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" " + field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Target getTarget() {
        return target;
    }
    public void createTarget() {
        target = new Target((int) (Math.random()*width), (int) (Math.random()*height));
    }

    public static void main(String[] args) throws InterruptedException {
        Snake snake = new Snake(5,10);
        snake.setDirection(Direction.DOWN);
        Game game = new Game(20,20, snake);
        game.createTarget();
        game.run();
    }
}
