package ftpServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FTPServerFrame extends javax.swing.JFrame{

    private final ImageIcon icon = new ImageIcon(getClass().getResource("/ftpServer/figure/trayIcon.png"));
    public  JTextField state = new JTextField();

    public FTPServerFrame() {
        initComponents();
    }

    //��ʼ���������ķ���
    private void initComponents() {
        setDefaultCloseOperation(0); //Ĭ�Ϲرմ���ģʽ
        setIconImage(icon.getImage());
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel jPanel1 = new JPanel();
        JButton addUsers = new JButton();

        setTitle("����Socket��FTP�����������");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(final WindowEvent e) {
                setExtendedState(Frame.ICONIFIED);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmDialog = JOptionPane.showConfirmDialog(null,
                        "��ʦȷ����������",null ,JOptionPane.YES_NO_OPTION); // �û�ȷ���Ƿ��˳�
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        getContentPane().setLayout(new java.awt.GridBagLayout());
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        addUsers.setText("����û���");
        addUsers.setFont(new Font(null,Font.BOLD,20));
        addUsers.setFocusable(false);
        addUsers.setPreferredSize(new Dimension(200,80));
        addUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCtrl dialog = new userCtrl(FTPServerFrame.this);
                dialog.setVisible(true);
            }
        });
        jPanel1.add(addUsers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        getContentPane().add(jPanel1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 700);

        JButton start = new JButton();
        start.setText("�򿪷�����");
        start.setFont(new Font(null,Font.BOLD,20));
        start.setFocusable(false);
        start.setPreferredSize(new Dimension(200,80));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "�˿ڼ�����");
                FtpServer ftpServer = null;
                try {
                    ftpServer = new FtpServer(21);
                    ftpServer.listen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jPanel1.add(start);

        state.setHorizontalAlignment(JTextField.CENTER);
        state.setEditable(false);
        state.setFont(new Font(null,Font.BOLD,20));
        state.setPreferredSize(new Dimension(360,80));
        jPanel1.add(state);
    }
}
