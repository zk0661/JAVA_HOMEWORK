public class Student {
    private String Snumber;
    private char Sex;
    private String Sname;
    private int Age;
    private double Score;

    public Student(String snumber, char sex, String sname, int age, double score) {
        Snumber = snumber;
        Sex = sex;
        Sname = sname;
        Age = age;
        Score = score;
    }

    public Student() {
        Snumber = "00000";
        Sex = 'N';
        Sname = "XXXX";
        Age = 0;
        Score = 0;
    }

    @Override
    public String toString() {
        return getSnumber() + ' '
                + getSex() + ' '
                + getSname() + ' '
                + getAge() + ' '
                + getScore();
    }

    public String getSnumber() {
        return Snumber;
    }

    public void setSnumber(String snumber) {
        Snumber = snumber;
    }

    public char getSex() {
        return Sex;
    }

    public void setSex(char sex) {
        Sex = sex;
    }

    public String getSname() {
        return Sname;
    }

    public String getSname1() {
        String s = Sname;
        while (s.charAt(s.length() - 1) == '#') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}
