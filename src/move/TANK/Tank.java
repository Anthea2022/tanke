package move.TANK;

public class Tank
{
    private  int x;
    private int y;
    private int direction;
    public int speed=3;
    public boolean frag=true;
    public Tank()
    {
        x=0;
        y=0;
    }
    public Tank(int x,int y)
    {
        setX(x);
        setY(y);
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
    public void  setDirection(int n)
    {
        direction=n;
    }
    public int getDirection()
    {
        return  direction;
    }
    public void moveUp()
    {
        y-=speed;
    }
    public void moveDown()
    {
        y+=speed;
    }
    public void moveRight()
    {
        x+=speed;
    }
    public  void moveLeft()
    {
        x-=speed;
    }
}
