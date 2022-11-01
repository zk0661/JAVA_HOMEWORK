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
    private ClassSet classSet;

    public AppGui() throws IOException {
        classSet = new ClassSet();
        jFrame = new JFrame("学生信息管理系统");
        setMenu();
        setjTable();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jFrame.setLayout(new BorderLayout());

        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);
        jMenuBar.add(jMenu4);
        jMenuBar.add(jMenu5);
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
        jMenu6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        jMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddDialog addDialog = new AddDialog();
            }
        });
    }

    class AddDialog {
        AddDialog() {
            JDialog jDialog = new JDialog(jFrame, "请输入需要增加的学生的信息");
            jDialog.setVisible(true);
            jDialog.setBounds(500, 200, 250, 300);
            jDialog.setLayout(new GridLayout(6,2));
            jDialog.add(new JLabel("学号"));
            jDialog.add(new TextArea());
            jDialog.add(new JLabel("姓名"));
            jDialog.add(new TextArea());
            jDialog.add(new JLabel("性别"));
            jDialog.add(new TextArea());
            jDialog.add(new JLabel("年龄"));
            jDialog.add(new TextArea());
            jDialog.add(new JLabel("得分"));
            jDialog.add(new TextArea());
            jDialog.add(new JButton("确定"));
            jDialog.add(new JButton("取消"));
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

        jTable = new JTable(tokenmodel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.getTableHeader().setReorderingAllowed(false);
    }
}
