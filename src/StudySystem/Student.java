package StudySystem;
class Student implements Comparable<Student>
{
    public  int id;
    public String name;
    private int math;
    private int english;
    private int cpp;
    public Student(int id,String name,int math,int english,int cpp)
    {
        this.id=id;
        this.name=name;
        this.math=math;
        this.english=english;
        this.cpp=cpp;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setCpp(int cpp) {
        this.cpp = cpp;
    }

    public int getMath() {
        return math;
    }

    public int getEnglish() {
        return english;
    }

    public int getCpp() {
        return cpp;
    }

    public int compareTo(Student s) {
        return this.id-s.id;
    }

}
