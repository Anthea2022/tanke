package move.TANK;
//播放音乐
import javax.sound.sampled.*;
import java.io.File;

public class Music extends Thread
{
    private String path;

    public Music(String path) {
        this.path = path;
    }
    public void run()
    {
        File music=new File(path);
        AudioInputStream audioInputStream=null;
        try {
            audioInputStream= AudioSystem.getAudioInputStream(music);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        AudioFormat audioFormat=audioInputStream.getFormat();
        SourceDataLine line=null;
        DataLine.Info info=new DataLine.Info(SourceDataLine.class,audioFormat);
        try {
            line=(SourceDataLine)AudioSystem.getLine(info);
            line.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        line.start();
        int Read=0;
        byte[]  data=new byte[512];
        try {
            while(Read!=-1)
            {
                Read=audioInputStream.read(data,0,data.length);
                if(Read>=0)
                {
                    line.write(data,0,Read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        finally
        {
            line.drain();
            line.close();
        }
    }
}
