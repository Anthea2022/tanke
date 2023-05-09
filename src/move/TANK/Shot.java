package move.TANK;

/**
 * @author HP
 */
public class Shot implements  Runnable
{
    int x;
    int y;
    int speed=2;
    int direction=0;
    boolean frag=true;
    public Shot(int x,int y,int  direction)
    {
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
    @Override
    public void run() {
        while(frag)
        {
            if(!(x>=0&&x<=1000&&y>=0&&y<=750))
            {
                frag=false;
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction)
            {
                case  0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case  2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
            }
        }
    }
}
