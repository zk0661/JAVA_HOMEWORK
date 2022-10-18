import java.util.HashMap;

public class ClassSet {
    HashMap<String, Student> StudentTable;
    HashMap<String, String> NameTable;
    // 从文件中读取数据构造hashmap

    public ClassSet() {
        // 遍历每一个元素
        ImportStudentInformation();
        assert false;
        for (String o : StudentTable.keySet()) {
            NameTable.put(StudentTable.get(o).getSname(), o);
        }

    }

    private void ImportStudentInformation() {

    }

    // 将学生信息写回文件
    private void ExportStudentInformation() {

    }

    // 增加一个学生
    public boolean Additions(Student o) {
        return false;
    }

    // 修改学生信息
    public boolean Modify(Student o) {
        return false;
    }

    // 删除学生
    public boolean Delete(Student o) {
        return false;
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
