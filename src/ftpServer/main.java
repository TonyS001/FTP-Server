package ftpServer;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static FTPServerFrame server_Frame;

    public static void main(String args[]) {
        //���� runnable �� run ������ EventQueue ��ָ���߳��ϱ����á�
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //ʹ�� LookAndFeel �������õ�ǰ��Ĭ����ۡ�
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());//����һ���ǳ�Ư�������
                    server_Frame = new FTPServerFrame();
                    server_Frame.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FTPServerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
