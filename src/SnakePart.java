public class SnakePart {
    private int x;
    private int y;

    public SnakePart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj) return true;
       if (obj == null || obj.getClass() != this.getClass()) return false;
       SnakePart part = (SnakePart) obj;
       if (this.getX() == part.getX() && this.getY() == part.getY()) return true;
       else return false;
    }

    @Override
    public int hashCode() {
        return 31*this.getX()+31*this.getY();
    }
}
