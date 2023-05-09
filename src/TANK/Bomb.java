package TANK;

public class Bomb
{
    int x;
    int y;
    int life=14;//太小看不出来效果
    boolean bfrag=true;

    public Bomb(int x,int y) {
        this.x = x;
        this.y=y;
    }
    public void lifeDown()
    {
        if(life>0)
            life--;
        else
            bfrag=false;
    }
}
