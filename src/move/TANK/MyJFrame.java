package move.TANK;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class MyJFrame extends JFrame {
    MyPanel myPanel=null;
    public static  void main(String[]  args)
    {
        new MyJFrame();
    }
    public MyJFrame()
    {
        System.out.println("请选择:1:新游戏，2:继续上局");
        Scanner s=new Scanner(System.in);
        //输入选择
        String choice=s.next();
        myPanel=new MyPanel(choice);
        add(myPanel);
        Thread thread=new Thread(myPanel);
        thread.start();//开启线程
        addKeyListener(myPanel);
        //myPanel的键盘监控
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Record.myRecorder();
                //退出时记录数据
                System.exit(1);
            }
        });
        setSize(1300,950);
        setVisible(true);
    }
}
