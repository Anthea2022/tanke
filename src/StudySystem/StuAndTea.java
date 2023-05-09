package StudySystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public class StuAndTea
{
    public static void main(String[] args)
    {
        Student s1=new Student(654325,"Jack",90,83,85);
        Student s2=new Student(654326,"Make",85,79,88);
        Student s3=new Student(652425,"Maria",78,92,78);
        Student s4=new Student(654326,"Lily",94,85,94);
        Student s5=new Student(614325,"Jane",90,87,76);
        Collection<Student> set=new TreeSet<>();
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        set.add(s5);
        String filePath="D://学生信息.txt";
        BufferedWriter buffer=null;
        for(Student s:set)
        {
            try {
                buffer=new BufferedWriter(new FileWriter(filePath,true));
                buffer.write(s.id);
                buffer.write(s.name);
                buffer.write(s.getMath());
                buffer.write(s.getEnglish());
                buffer.write(s.getCpp());
                buffer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if(buffer!=null) {
                    try {
                        buffer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public  void find(String name,Collection<Student> set)
    {
        for(Student s:set)
        {
            if(s.name.equals(name)) {
                System.out.println(s.id + " " + s.name + " " + s.getMath() + " " + s.getEnglish() + " " + s.getCpp());
            }
            else {
                continue;
            }
        }
    }
    public void  changeScores(int  id,int  m1,int e1,int c1,Collection<Student> set)
    {
        for(Student  s:set)
        {
            if(s.id==id) {
                s.setMath(m1);
                s.setEnglish(e1);
                s.setCpp(c1);
            }
            else {
                continue;
            }
        }
    }
}
