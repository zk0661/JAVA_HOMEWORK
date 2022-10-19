import javax.swing.*;

public class AppGui {

    private JLabel jLabel;
    private JFrame jFrame;
    private JButton jButton;
    private JPanel jPanel;

    // 实现与ClassSet与界面的连接，应提供接口
    public AppGui() { // 
        JFrame jFrame = new JFrame();
//        JLabel jLabel = new JLabel();

        jFrame.setSize(620, 400);
        jFrame.setVisible(true);
    }
    

}
