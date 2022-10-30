import javax.swing.*;

public class AppGui {
    AppGui() {
        JFrame jFrame = new JFrame("学生信息管理系统");
        jFrame.setBounds(300,100,650,500);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
