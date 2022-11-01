import javax.swing.*;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import static javax.swing.JButton.*;

public class AppGui {
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
        setjTable();
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
        jMenu6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                setjTable();
            }
        });
        jMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        jMenu7.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    classSet.ExportStudentInformation();
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
    }

    private void setjTable() {
        Vector<Vector<Object>> studentList = classSet.getAll();
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
