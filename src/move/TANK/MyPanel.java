package move.TANK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author HP
 */
public class MyPanel extends JPanel implements KeyListener,Runnable
{
    MyTank myTank=null;
    Vector<EnemyTank> EnemyTanks=new Vector<>();
    Vector<Node> nodes=new Vector<>();
    Vector<Bomb> Bombs=new Vector<>();
    Image image1=null;
    Image image2=null;
    Image image3=null;
    int  EnemyNum=6;
    public void showCredit(Graphics g)
    //展示成绩
    {
        g.setColor(Color.BLACK);
        Font font=new Font("宋体",Font.BOLD+Font.ITALIC,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        draw(1020,60,1,0,g);
        g.setColor(Color.BLACK);
        g.drawString(Record.getNum()+"",1080,100);
    }
    public void  hit(Shot shot, Tank tank)
    //放射子弹及爆炸
       {
           switch (tank.getDirection()) {
               case 0:
               case 2:
                   if (shot.x > tank.getX() && shot.x < (tank.getX() + 40)
                           && shot.y > tank.getY() && shot.y < (tank.getY() + 60)) {
                       tank.frag = false;
                       if(!(tank instanceof MyTank))
                           //如果坦克是敌人的，那么击败的敌人坦克数量+1
                       {
                           EnemyTanks.remove(tank);
                           //移除坦克
                           Record.add();
                       }
                       shot.frag = false;
                       Bomb bomb = new Bomb(tank.getX(), tank.getY());
                       //爆炸
                       Bombs.add(bomb);
                   }
                   break;
               case 1:
               case 3:
                   if (shot.x > tank.getX() && shot.x < (tank.getX() + 60) && shot.y > tank.getY() && shot.y < (tank.getY() + 40)) {
                      tank.frag = false;
                       if(!(tank instanceof MyTank))
                           //如果是敌人坦克，那么击败的敌人坦克数量+1
                       {
                           Record.add();
                           EnemyTanks.remove(tank);
                       }
                       shot.frag = false;
                       Bomb bomb = new Bomb(tank.getX(), tank.getY());
                       //爆炸
                       Bombs.add(bomb);
                   }
                   break;
               default:
                   break;
           }
    }
    public void hitMe()//判断我方坦克是否被击中
    {
        for (int i = 0; i < EnemyTanks.size(); i++)
        {
            EnemyTank  enemyTank=EnemyTanks.get(i);
            for(int  j=0;j<enemyTank.shots.size();j++)
            {
                Shot shot=enemyTank.shots.get(j);
                if(myTank.frag&&shot.frag)
                    hit(shot,myTank);
            }
        }
    }
    public MyPanel(String choice)
    {
        nodes= Record.getData();
        myTank = new MyTank(300, 300);
        switch (choice)
        {
            case "1":
                //开启新游戏
                for (int i = 0; i < EnemyNum; i++)
                {
                    Record.setNum(0);
                    EnemyTank enemyTank = new EnemyTank((i + 1) * 100, 0);
                    enemyTank.setDirection(2);
                    enemyTank.setEnemyTanks(EnemyTanks);
                    Thread thread1=new  Thread(enemyTank);
                    thread1.start();
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    enemyTank.shots.add(shot);
                    Thread thread = new Thread(shot);
                    thread.start();
                    EnemyTanks.add(enemyTank);
                }
                Record.setEnemyTanks(EnemyTanks);
                //把EnemyTanks设置给Record
                image1=Toolkit.getDefaultToolkit().getImage("/Image1.jpg");
                image2=Toolkit.getDefaultToolkit().getImage("/Image2.jpg");
                image3=Toolkit.getDefaultToolkit().getImage("/Image3.jpg");
                break;
            case "2":
                //继续上局游戏
                for (int i = 0; i < nodes.size(); i++)
                {
                    Node node=nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    enemyTank.setDirection(node.getDirection());
                    enemyTank.setEnemyTanks(EnemyTanks);
                    Thread thread1=new  Thread(enemyTank);
                    thread1.start();
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    enemyTank.shots.add(shot);
                    Thread thread = new Thread(shot);
                    thread.start();
                    EnemyTanks.add(enemyTank);
                }
                Record.setEnemyTanks(EnemyTanks);
                //把EnemyTanks设置给Record
                image1=Toolkit.getDefaultToolkit().getImage("/Image1.jpg");
                image2=Toolkit.getDefaultToolkit().getImage("/Image2.jpg");
                image3=Toolkit.getDefaultToolkit().getImage("/Image3.jpg");
                break;
            default:
                break;
        }
        new Music("src//坦克大战原声.wav").start();
    }
    @Override
    public void paint(Graphics g) //画窗口
    {
        super.paint(g);
        drawMenu(g);
        showCredit(g);
        //展示成绩
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,750);
        for(int i=0;i<EnemyTanks.size();i++)
            //画敌人坦克和子弹
        {
            EnemyTank enemyTank=EnemyTanks.get(i);
            if(enemyTank.frag&&enemyTank!=null) {
                draw(enemyTank.getX(), enemyTank.getY(), 1, enemyTank.getDirection(), g);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.frag) {
                        g.drawOval(shot.x, shot.y, 2, 2);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
        if(myTank!=null&&myTank.frag)
            //画我方坦克
        {
            draw(myTank.getX(), myTank.getY(), 0, myTank.getDirection(), g);
            for (int i = 0; i < myTank.shots.size(); i++) {
                Shot shot = myTank.shots.get(i);
                if (shot != null && shot.frag) {
                    g.drawOval(shot.x, shot.y, 2, 2);
                } else {
                    myTank.shots.remove(shot);
                }
            }
        }
        for(int k=0;k<Bombs.size();k++)
            //添加爆炸图
        {
            Bomb bomb=Bombs.get(k);
            if(bomb.life>6) {
                g.drawImage(image1, bomb.x, bomb.y, 60,60, this);
            }
            else  if(bomb.life>3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }
            else
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            bomb.lifeDown();
            if(bomb.life==0)
                Bombs.remove(bomb);
        }
    }
    public void drawMenu(Graphics g)
    {
        g.setFont(new Font("宋体",Font.BOLD,24));
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,750);
        g.setColor(Color.RED);
        g.drawString("开始新游戏",500,350);
        g.drawString("继续上局游戏",500,550);
    }
    public void draw(int  x,int  y,int type,int direction,Graphics g)
    //画坦克
    {
        switch(type)
        {
            case 0:
                g.setColor(Color.red);
                break;
            case 1:
                g.setColor(Color.cyan);
        }
        switch(direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            if(myTank.getY()+60<750) {
                myTank.moveDown();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP) {
            myTank.setDirection(0);
            if(myTank.getY()>0) {
                myTank.moveUp();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            if(myTank.getX()>0) {
                myTank.moveLeft();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            if (myTank.getX()+60<1000)
                myTank.moveRight();
        }
        if(e.getKeyCode()==KeyEvent.VK_J)
            myTank.ShotEnemyTank();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(myTank!=null&&myTank.frag) {
                for (int i = 0; i < myTank.shots.size(); i++) {
                    Shot shot = myTank.shots.get(i);
                    if (shot != null && shot.frag)
                        for (int j = 0; j < EnemyTanks.size(); j++) {
                            EnemyTank enemyTank = EnemyTanks.get(j);
                            hit(shot, enemyTank);
                            //遍历敌人坦克，是否击中敌方坦克
                        }
                }
            }
            hitMe();
            //判断是否我方坦克
            this.repaint();
        }
    }
}
