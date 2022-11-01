import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;


public class AppGui {
    public static final int SELECT = 1;
    public static final int ADD = 2;
    public static final int DEFAULT = 0;
    private JFrame jFrame;
    private JTable jTable;
    private JMenuBar jMenuBar;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenu5;
    private JMenu jMenu6;
    private JMenu jMenu7;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private ClassSet classSet;

    public AppGui() throws IOException {
        classSet = new ClassSet();
        jFrame = new JFrame("学生信息管理系统");
        jTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setMenu();
        setjTable(DEFAULT);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jFrame.setLayout(new BorderLayout());

        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);
        jMenuBar.add(jMenu4);
        jMenuBar.add(jMenu5);
        jMenuBar.add(jMenu7);
        jMenuBar.add(jMenu6);
        jFrame.add(jMenuBar, BorderLayout.NORTH);
        jFrame.add(jScrollPane, BorderLayout.CENTER);


        jFrame.setBounds(300,150,650,400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void setMenu() {
        jMenuBar = new JMenuBar();
        jMenu1 = new JMenu("增加记录");
        jMenu2 = new JMenu("修改记录");
        jMenu3 = new JMenu("删除记录");
        jMenu4 = new JMenu("查询");
        jMenu5 = new JMenu("刷新");
        jMenu6 = new JMenu("退出");
        jMenu7 = new JMenu("保存修改");

        jMenuItem1 = new JMenuItem("按学号查找");
        jMenuItem2 = new JMenuItem("按姓名查找");

        jMenuItem1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = JOptionPane.showInputDialog(jFrame, "请输入学号");
                if (str != null) {
                    classSet.SelectNumber(str);
                    setjTable(SELECT);
                }
            }
        });

        jMenuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = JOptionPane.showInputDialog(jFrame, "请输入学生姓名");
                if (str != null) {
                    classSet.SelectName(str);
                    setjTable(SELECT);
                }
            }
        });

        jMenu4.add(jMenuItem1);
        jMenu4.add(jMenuItem2);

        jMenu6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(jFrame, "是否保存当前修改?");
                System.exit(0);
            }
        });
        jMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddDialog addDialog = new AddDialog("请输入需要增加的学生信息", AddDialog.ADD);
            }
        });
        jMenu5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setjTable(DEFAULT);
//                System.out.println(classSet);
            }
        });
        jMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = JOptionPane.showInputDialog(jFrame, "请输入需要修改的学生学号");
                if (str == null) return;
                classSet.SelectNumber(str);
                Vector<Vector<Object>> selectResult = classSet.getSelectResult();
                if (selectResult.size() == 0) {
                    JOptionPane.showMessageDialog(jFrame, "要修改的学生不存在");
                }
                else {
                    AddDialog dialog = new AddDialog("请输入修改后的信息", AddDialog.MOD, selectResult.get(0));
                }
            }
        });
        jMenu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = JOptionPane.showInputDialog(jFrame, "请输入需要删除的学生的学号");
                if (str != null) {
                    boolean tag = classSet.Delete(classSet.getStudentTable().get(str));
                    if (!tag) JOptionPane.showMessageDialog(jFrame, "没有该学生！");
                    else JOptionPane.showMessageDialog(jFrame, "操作成功！");
                }
            }
        });
        jMenu7.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    classSet.ExportStudentInformation();
                    JOptionPane.showMessageDialog(jFrame, "操作成功！");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    class AddDialog {
        private static final int ADD = 1;
        private static final int MOD = 2;
        int type;
        private JDialog jDialog;
        private JTextField jTextField1;
        private JTextField jTextField2;
        private JTextField jTextField3;

        private JTextField jTextField4;
        private JTextField jTextField5;
        private Student buffer;
        AddDialog(String title, int type, Vector<Object> vo) {
            this(title, type);
            jTextField1.setText((String) vo.get(0));
            jTextField2.setText((String) vo.get(1));
            jTextField3.setText(vo.get(2) + "");
            jTextField4.setText(vo.get(3) + "");
            jTextField5.setText(vo.get(4) + "");
            buffer = new Student();
            buffer.setSnumber((String) vo.get(0));
            buffer.setSname((String) vo.get(1));
            buffer.setSex((char)vo.get(2));
            buffer.setAge((int)vo.get(3));
            buffer.setScore((double)vo.get(4));
        }
        AddDialog(String title, int type) {
            this.type = type;
            jDialog = new JDialog(jFrame, title);
            jDialog.setVisible(true);
            jDialog.setBounds(500, 200, 250, 300);
            jDialog.setLayout(new GridLayout(6,2));

            jTextField1 = new JTextField();
            jTextField2 = new JTextField();
            jTextField3 = new JTextField();
            jTextField4 = new JTextField();
            jTextField5 = new JTextField();

            JPanel jPanel2 = new JPanel();
            jTextField1.setPreferredSize(new Dimension(100,25));
            jPanel2.add(jTextField1, BorderLayout.CENTER);
            JPanel jPanel3 = new JPanel();
            jTextField2.setPreferredSize(new Dimension(100,25));
            jPanel3.add(jTextField2);
            JPanel jPanel4 = new JPanel();
            jTextField3.setPreferredSize(new Dimension(100,25));
            jPanel4.add(jTextField3);
            JPanel jPanel5 = new JPanel();
            jTextField4.setPreferredSize(new Dimension(100,25));
            jPanel5.add(jTextField4);
            JPanel jPanel6 = new JPanel();
            jTextField5.setPreferredSize(new Dimension(100,25));
            jPanel6.add(jTextField5);


            jDialog.add(new JLabel("学号", JLabel.CENTER));
            jDialog.add(jPanel2);
            jDialog.add(new JLabel("姓名", JLabel.CENTER));
            jDialog.add(jPanel3);
            jDialog.add(new JLabel("性别", JLabel.CENTER));
            jDialog.add(jPanel4);
            jDialog.add(new JLabel("年龄", JLabel.CENTER));
            jDialog.add(jPanel5);
            jDialog.add(new JLabel("得分", JLabel.CENTER));
            jDialog.add(jPanel6);

            JButton jButton = new JButton("确认");
            if (type == ADD) jButton.addMouseListener(new MyAdapter());
            else jButton.addMouseListener(new MyAdapter1());
            JPanel jPanel = new JPanel();
            jPanel.add(jButton);
            jDialog.add(jPanel);



            JButton jButton1 = new JButton("取消");
            jButton1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jDialog.dispose();
                }
            });
            JPanel jPanel1 = new JPanel();
            jPanel1.add(jButton1);
            jDialog.add(jPanel1);
            jDialog.pack();
        }
        class MyAdapter extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                Student student = new Student();
                student.setSnumber(jTextField1.getText());
                student.setSname(jTextField2.getText());
                student.setSex(jTextField3.getText().charAt(0));
                student.setAge(Integer.parseInt(jTextField4.getText()));
                student.setScore(Double.parseDouble(jTextField5.getText()));
                boolean tag = classSet.Additions(student);
                if (!tag) JOptionPane.showMessageDialog(jDialog, "学号不允许重复！");
                else JOptionPane.showMessageDialog(jDialog, "操作成功！");
                jDialog.dispose();
            }
        }
        class MyAdapter1 extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                Student student = new Student();
                student.setSnumber(jTextField1.getText());
                student.setSname(jTextField2.getText());
                student.setSex(jTextField3.getText().charAt(0));
                student.setAge(Integer.parseInt(jTextField4.getText()));
                student.setScore(Double.parseDouble(jTextField5.getText()));
                boolean tag = classSet.Modify(buffer, student);
                if (!tag) JOptionPane.showMessageDialog(jDialog, "学号不允许被修改！");
                else JOptionPane.showMessageDialog(jDialog, "操作成功！");
                jDialog.dispose();
            }
        }
    }

    private void setjTable(int MODEL) {
        Vector<Vector<Object>> studentList;
        if (MODEL == DEFAULT) studentList = classSet.getAll();
        else studentList = classSet.getSelectResult();
        AbstractTableModel tokenmodel =new AbstractTableModel() {

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return studentList.get(rowIndex).get(columnIndex);
            }
            @Override
            public int getRowCount() {
                return studentList.size();
            }

            @Override
            public int getColumnCount() {
                if (studentList.size() == 0) return 0;
                return studentList.get(0).size();
            }
            @Override//重写了获取列名函数
            public String getColumnName(int column) {
                return switch (column) {
                    case 0 -> "学号";
                    case 1 -> "姓名";
                    case 2 -> "性别";
                    case 3 -> "年龄";
                    default -> "得分";
                };
            }
        };
        jTable.setModel(tokenmodel);
        jTable.getTableHeader().setReorderingAllowed(false);
    }
}
