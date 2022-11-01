import java.io.*;
import java.util.HashMap;
import java.util.Objects;
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

    private void InvBuild() {
        // 遍历每一个姓名，使姓名背后的'#'去掉
        for (String o : StudentTable.keySet()) {
            String ss = StudentTable.get(o).getSname();
            while (ss.charAt(ss.length() - 1) == '#') {
                ss = ss.substring(0, ss.length() - 1);
            }
            StudentTable.get(o).setSname(ss);
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
        InvBuild();
        FileWriter fileWriter = new FileWriter("lib/Student.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        // 将学生信息表中的信息写回文件

        bufferedWriter.close();
        fileWriter.close();
    }

    private void ReName(Student o) {
        while (NameTable.get(o.getSname()) != null) {
            o.setSname(o.getSname() + '#');
        }
    }
    // 增加一个学生
    public boolean Additions(Student o) {
        if (StudentTable.get(o.getSnumber()) != null) {
            return false;
        }
        ReName(o);
        // 更新姓名-学号索引表
        StudentTable.put(o.getSnumber(), o);
        // 重构索引表
        NameTable.put(o.getSname(), o.getSnumber());
        return true;
    }

    // 修改学生信息
    public boolean Modify(Student origin, Student modify) {
        // 不允许修改学号
        if (StudentTable.get(origin.getSnumber()) == null || !Objects.equals(origin.getSnumber(), modify.getSnumber())) {
            return false;
        }
        Delete(origin);
        // Delete之后一定为原学号对应值一定为null
        Additions(modify);
        return true;
    }

    // 删除学生
    public void Delete(Student o) {
        StudentTable.put(o.getSnumber(), null);
        NameTable.put(o.getSname(), null);
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

    public Vector<Vector<Object>> getAll() {
        Vector<Vector<Object>> res = new Vector<>();
        for (String o : StudentTable.keySet()) {
            Student temp = StudentTable.get(o);
            Vector<Object> temp2 = new Vector<>();
            temp2.add(temp.getSnumber());
            temp2.add(temp.getSname1());
            temp2.add(temp.getSex());
            temp2.add(temp.getAge());
            temp2.add(temp.getScore());
            res.add(temp2);
        }
        return res;
    }
}
