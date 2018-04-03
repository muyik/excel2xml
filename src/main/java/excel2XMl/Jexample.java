package excel2XMl;



import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Jexample implements ActionListener {
    JFrame frame = new JFrame("Excel2Xml");// 框架布局
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
    Container con = new Container();//
    JLabel label2 = new JLabel("xlsFile");
    JLabel label1 = new JLabel("xmlDirctory");
    JTextField text1 = new JTextField();
    JTextField text2 = new JTextField();
    JButton button1 = new JButton("...");
    JButton button2 = new JButton("...");
    JFileChooser jfc = new JFileChooser();
    JButton button3 = new JButton("Click");
    String filePath =null;
    String sourceFilepath=null;

    Jexample() {
        jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘

        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(600, 400);// 设定窗口大小
        frame.setContentPane(tabPane);// 设置布局
        label2.setBounds(10, 20, 100, 40);
        text2.setBounds(110, 20, 120, 40);
        button2.setBounds(240, 20, 50, 40);
        label1.setBounds(10, 70, 300, 40);
        text1.setBounds(110, 70, 120, 40);
        button1.setBounds(240, 70, 50, 40);
        button3.setBounds(110, 120, 100, 40);
        button2.addActionListener(this); // 添加事件处理
        button1.addActionListener(this); // 添加事件处理
        button3.addActionListener(this); // 添加事件处理
        con.add(label2);
        con.add(text2);
        con.add(button1);
        con.add(label1);
        con.add(text1);
        con.add(button2);
        con.add(button3);
        frame.setVisible(true);// 窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
        tabPane.add("", con);// 添加布局1
    }

    /**
     * 时间监听的方法
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Exml exml=new Exml();

        if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;
            } else {
                File f = jfc.getSelectedFile();// f为选择到的目录
                text1.setText(f.getAbsolutePath());
                filePath=f.getAbsolutePath();
            }
        }
        // 绑定到选择文件，先择文件事件
        if (e.getSource().equals(button2)) {
            jfc.setFileSelectionMode(0);// 设定只能选择到文件
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;// 撤销则返回
            } else {
                File f = jfc.getSelectedFile();// f为选择到的文件
                text2.setText(f.getAbsolutePath());
                sourceFilepath=f.getAbsolutePath();
            }
        }
        if (e.getSource().equals(button3)) {
            try {
                exml.genratexml(sourceFilepath,filePath);
                JOptionPane.showMessageDialog(null, "Done!", "提示", 2);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,e1.getMessage(), "提示", 2);
            }


        }
    }

    public static void main(String[] args) {
        new Jexample();
    }
}
