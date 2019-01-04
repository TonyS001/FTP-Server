package ftpServer;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static FTPServerFrame server_Frame;

    public static void main(String args[]) {
        //导致 runnable 的 run 方法在 EventQueue 的指派线程上被调用。
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //使用 LookAndFeel 对象设置当前的默认外观。
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());//设置一个非常漂亮的外观
                    server_Frame = new FTPServerFrame();
                    server_Frame.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FTPServerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
