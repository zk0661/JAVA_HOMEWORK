import java.io.*;
import java.util.HashMap;

public class ClassSet {
    HashMap<String, Student> StudentTable;
    HashMap<String, String> NameTable;
    // 从文件中读取数据构造hashmap

    public ClassSet() throws IOException {
        // 遍历每一个元素
        ImportStudentInformation();
        // 构建姓名-学号索引表
        for (String o : StudentTable.keySet()) {
            if (StudentTable.get(o) != null) {
                NameTable.put(StudentTable.get(o).getSname() + "#", StudentTable.get(o).getSnumber());
            }
            else {
                NameTable.put(StudentTable.get(o).getSname(), o);
            }
        }
    }

    private void ImportStudentInformation() throws IOException {
        FileReader fileReader = new FileReader("lib/Student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String Line;
        String[] Message;
        Student temp = new Student();
        while ((Line = bufferedReader.readLine()) != null) {
            Message = Line.split(" ");
            temp.setSnumber(Message[0]);
            temp.setSex(Message[1].charAt(0));
            temp.setSname(Message[2]);
            temp.setAge(Integer.parseInt(Message[3]));
            temp.setScore(Integer.parseInt(Message[4]));
            StudentTable.put(temp.getSnumber(), temp);
        }
        bufferedReader.close();
        fileReader.close();
    }

    // 将学生信息写回文件
    private void ExportStudentInformation() throws IOException {
        FileWriter fileWriter = new FileWriter("lib/Student.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.close();;
        fileWriter.close();
    }

    public void UpdateNameTable(Student o) {
        while (NameTable.get(o.getSname()) != null) {
            o.setSname(o.getSname() + "#");
        }
        NameTable.put(o.getSname(), o.getSnumber());
    }

    // 增加一个学生
    public boolean Additions(Student o) {
        if (StudentTable.get(o.getSnumber()) != null) {
            return false;
        }
        StudentTable.put(o.getSnumber(), o);
        // 更新姓名-学号索引表
        UpdateNameTable(o);
        return true;
    }

    // 修改学生信息
    public boolean Modify(Student o) {
        // 不允许修改学号
        if (StudentTable.get(o.getSnumber()) == null) {
            return false;
        }
        StudentTable.put(o.getSnumber(), o);
        UpdateNameTable(o);
        return true;
    }

    // 删除学生
    public void Delete(Student o) {
        StudentTable.remove(o.getSnumber());
    }

    public int getStudentSum() {
        return StudentTable.size();
    }

    // 按学号查询学生
    public Student SelectNumber(String number) {
        return StudentTable.get(number);
    }

    // 按名查找
    public Student SelectName(String name) {
        return StudentTable.get(NameTable.get(name));
    }


}
