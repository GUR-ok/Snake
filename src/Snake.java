import java.util.ArrayList;
import java.util.List;

public class Snake {

    private boolean isAlive;
    private Direction direction;
    public List<SnakePart> snakeBody;

    public Snake(int x, int y) {
        isAlive = true;
        snakeBody = new ArrayList<>();
        snakeBody.add(new SnakePart(x,y));
    }

    public void move() {
        if (isAlive) {
            switch (direction) {
                case LEFT: {move(-1,0); break;}
                case RIGHT: {move(1,0); break;}
                case DOWN: {move(0,1); break;}
                default: {move(0,-1);}
            }
        }
    }

    public void move(int x, int y){
        SnakePart newHead = new SnakePart(this.getHeadX()+x, this.getHeadY()+y);
        checkCollision(newHead);
        if (!isAlive) return;
        if (Game.game.getTarget().getX() == newHead.getX() &&
        Game.game.getTarget().getY() == newHead.getY()) {
            snakeBody.add(0,(new SnakePart(getHeadX()+x,getHeadY()+y)));
            Game.game.createTarget();
        } else {
            snakeBody.add(0, (new SnakePart(getHeadX() + x, getHeadY() + y)));
            snakeBody.remove(snakeBody.get(snakeBody.size() - 1));
        }
    }

    public int getHeadX() {
        return snakeBody.get(0).getX();
    }

    public int getHeadY() {
        return snakeBody.get(0).getY();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void checkCollision(SnakePart head) {
        if (head.getX() > Game.game.getWidth()-1 || head.getX() < 0 ||
                head.getY() > Game.game.getHeight()-1 || head.getY() < 0 || this.snakeBody.contains(head))
        {this.setAlive(false);}
    }

}
