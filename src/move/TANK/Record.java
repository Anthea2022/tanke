package move.TANK;

import java.io.*;
import java.util.Vector;

public class Record
{
    private static int num=0;
    //击败的数量
    private static BufferedWriter bufferedWriter;
    private static BufferedReader bufferedReader;
    private static String path="src\\record.txt";
    private static Vector<EnemyTank> EnemyTanks=new Vector<>();
    //由MyPanel的EnemyTanks设置
    private static Vector<Node> nodes=new Vector<Node>();
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        EnemyTanks = enemyTanks;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Record.num = num;
    }
    public static void add()
    {
        num++;
    }
    public static  void myRecorder()
    {
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(num+"\r\n");
            for(int i=0;i<EnemyTanks.size();i++)
            {
                EnemyTank enemyTank=EnemyTanks.get(i);
                if(enemyTank.frag)
                    //遍历敌人坦克，如果活着就记录
                {
                    bufferedWriter.write(enemyTank.getX()+" "+enemyTank.getY()+
                            " "+enemyTank.getDirection());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bufferedWriter!=null)
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }
    public static Vector<Node> getData()
    //返回上局
    {
        try {
            bufferedReader=new BufferedReader(new FileReader(path));
            num=Integer.parseInt(bufferedReader.readLine());
            String str="";
            while((str=bufferedReader.readLine())!=null)
            {
                String[] s=str.split(" ");
                Node node=new Node(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bufferedReader!=null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return nodes;
    }
}
