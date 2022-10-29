import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class ClassSet {
    HashMap<String, Student> StudentTable;
    HashMap<String, String> NameTable; // 姓名-学号表，实现按名查找
    // 从文件中读取数据构造hashmap
    public ClassSet() throws IOException {
        // 遍历每一个元素
        StudentTable = new HashMap<String, Student>();
        NameTable = new HashMap<String, String>();
        ImportStudentInformation();
        // 构建姓名-学号索引表
        Rebuild();
        NameNumberMap();
    }

    private void Rebuild() {
        HashMap<String, Integer> stringHashMap = new HashMap<String, Integer>();
        for (String o : StudentTable.keySet()) { // 遍历学号，出现同名的学生自动加‘#’
            String ss = StudentTable.get(o).getSname();
            if (stringHashMap.get(ss) == null) {
                stringHashMap.put(ss, 1);
            }
            else {
                stringHashMap.put(ss, stringHashMap.get(ss) + 1);
                int temp = stringHashMap.get(ss);
                // 增加 temp - 1 个 '#'
                String temp2 = ss + "#".repeat(Math.max(0, temp - 1));
                StudentTable.get(o).setSname(temp2);
            }
        }
    }

    private void NameNumberMap() {
        for (String o : StudentTable.keySet()) {
            NameTable.put(StudentTable.get(o).getSname(), o);
        }
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{\n");
        for (String o : StudentTable.keySet()) {
            if (StudentTable.get(o) != null) {
                res.append(StudentTable.get(o).toString()).append('\n');
            }
        }
        return res + "}";
    }

    public void DispNameTable() {
        for (String o : NameTable.keySet()) {
            System.out.println(o + "->" + NameTable.get(o));
        }
    }

    private void ImportStudentInformation() throws IOException {
        FileReader fileReader = new FileReader("lib/Student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String Line;
        String[] Message;
        while ((Line = bufferedReader.readLine()) != null) {
            Student temp = new Student();
            Message = Line.split(" ");
            temp.setSnumber(Message[0]);
            temp.setSex(Message[1].charAt(0));
            temp.setSname(Message[2]);
            temp.setAge(Integer.parseInt(Message[3]));
            temp.setScore(Integer.parseInt(Message[4]));
            this.StudentTable.put(temp.getSnumber(), temp);
        }
        bufferedReader.close();
        fileReader.close();
    }

    // 将学生信息写回文件
    private void ExportStudentInformation() throws IOException {
        FileWriter fileWriter = new FileWriter("lib/Student.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        // 将学生信息表中的信息写回文件

        bufferedWriter.close();
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

    public Vector<String> getAll() {
        Vector<String> res = new Vector<String>();
        for (String o : StudentTable.keySet()) {
            Student temp = StudentTable.get(o);
            String temp2 = temp.getSnumber() + ' ' +
                           temp.getSex() + ' ' +
                           temp.getSname() + ' ' +
                           temp.getAge() + ' ' +
                           temp.getScore();
            res.add(temp2);
        }
        return res;
    }

}
