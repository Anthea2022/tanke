package TANK;

import java.util.Vector;

public class EnemyTank extends Tank  implements Runnable
{
    Vector<EnemyTank> enemyTanks=new Vector<>();
    Vector<Shot> shots=new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //通过方法的调用设置EnemyTanks
    public boolean repeat()//确认是否重复
    {
        boolean frag=false;//返回
        boolean frag1=true;//记录结果
        for(int i=0;i<enemyTanks.size();i++)
        {
            EnemyTank tank2 =enemyTanks.get(i);
            if(tank2!=this) {
                if (getDirection() == 0 || getDirection() == 2)//上下
                {
                    if (tank2.getDirection() == 0 || tank2.getDirection() == 2)//上下
                    {
                        //左上
                        if (tank2.getX() >= getX() && tank2.getX() <= getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <= getY() + 60)
                            frag1 = true;
                            //右上
                        else if (tank2.getX() + 40 >= getX() && tank2.getX() + 40 <= getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <= getY() + 60)
                            frag1 = true;
                            //左下
                        else if (tank2.getX() >= getX() && tank2.getX() <= getX() + 40
                                && tank2.getY() + 60 >= getY() && tank2.getY() + 60 <= getY() + 60)
                            frag1 = true;
                            //右下
                        else if (tank2.getX() + 40 >= getX() && tank2.getX() + 40 <= getX() + 40
                                && tank2.getY() + 60 >= getY() && tank2.getY() + 60 <= getY() + 60)
                            frag1 = true;
                        else
                            frag1 = false;
                    } else //if (tank2.getDirection() == 1 || tank2.getDirection() == 3)//左右
                    {
                        //左上
                        if (tank2.getX() >= getX() && tank2.getX() <= getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <=getY() + 60)
                            frag1 = true;
                            //右上
                        else if (tank2.getX() + 60 >= getX() && tank2.getX() + 60 <= getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <= getY() + 60)
                            frag1 = true;
                            //左下
                        else if (tank2.getX() >= getX() && tank2.getX() <= getX() + 40
                                && tank2.getY() + 40 >= getY() && tank2.getY() + 40 <= getY() + 60)
                            frag1 = true;
                            //右下
                        else if (tank2.getX() + 60 >= getX() && tank2.getX() + 60 <= getX() + 40
                                && tank2.getY() + 40 >= getY() && tank2.getY() + 40 <= getY() + 60)
                            frag1 = true;
                        else
                            frag1 = false;
                    }
                } else if (getDirection() == 1 || getDirection() == 3)//左右
                {
                    if (tank2.getDirection() == 0 || tank2.getDirection() == 2)//上下
                    {
                        if (tank2.getX() >=getX() && tank2.getX() <getX() + 60
                                && tank2.getY() >=getY() && tank2.getY() <= getY() + 40)//左上
                            frag1 = true;
                        else if (tank2.getX() + 40 >= getX() && tank2.getX() + 40 < getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <= getY() + 40)//右上
                            frag1 = true;
                        else if (tank2.getX() >= getX() && tank2.getX() < getX() + 60
                                && tank2.getY() + 60 >= getY() && tank2.getY() + 60 <=getY() + 40)//左下
                            frag1 = true;
                        else if (tank2.getX() + 40 >= getX() && tank2.getX() + 40 < getX() + 60
                                && tank2.getY() + 60 >= getY() && tank2.getY() + 60 <= getY() + 40)//左上
                            frag1 = true;
                        else
                            frag1 = false;
                    } else
                    if (tank2.getDirection() == 1 || tank2.getDirection() == 3)//左右
                    {
                        if (tank2.getX() >= getX() && tank2.getX() < getX() + 60
                                && tank2.getY() >= getY() && tank2.getY() <= getY() + 40)//左上
                            frag1 = true;
                        else if (tank2.getX() + 60 >= getX() && tank2.getX() + 60 < getX() + 40
                                && tank2.getY() >= getY() && tank2.getY() <=getY() + 40)//右上
                            frag1 = true;
                        else if (tank2.getX() >= getX() && tank2.getX() <getX() + 60
                                && tank2.getY() + 40 >= getY() && tank2.getY() + 40 <= getY() + 40)//左下
                            frag1 = true;
                        else if (tank2.getX() + 60 >= getX() && tank2.getX() + 60 < getX() + 60
                                && tank2.getY() + 40 >= getY() && tank2.getY() + 40 <= getY() + 40)//左上
                            frag1 = true;
                        else
                            frag1 = false;
                    }
                }
                if (frag1==true)
                    frag = true;
            }
        }
        return frag;
    }
    public EnemyTank(int  x, int y)
    {
        super(x,y);
    }
    public void run() {
        while(true)
        {
            if(!frag)
            {
                break;
            }
            if(shots.size()<5)//使敌人坦克可以连发子弹
            {
                Shot sh=null;
                switch (getDirection())
                {
                    case 0:
                        sh=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        sh=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        sh=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        sh=new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(sh);
                new Thread(sh).start();
            }
            //自由移动
            switch (getDirection())
            {
                case  0:
                    for(int i=0;i<10;i++)
                    {
                        if(getY()>0&&!repeat())//不重叠的时候可以移动
                            moveUp();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case  1:
                    for(int  i=0;i<10;i++)
                    {
                        if(getX()+60<750&&!repeat())
                            moveRight();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<10;i++)
                    {
                        if(getY()+60<1000&&!repeat())
                            moveDown();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case  3:
                    for(int i=0;i<10;i++)
                    {
                        if(getX()>0&&!repeat())
                          moveLeft();
                        try
                        {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            setDirection((int)(Math.random()*4));
        }
    }
}
