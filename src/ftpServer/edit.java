package ftpServer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class edit extends JDialog implements ActionListener {
    private userCtrl dialog; // ���游��������ö���
    private JTextField userNameField; // �û����ı������
    private JTextField passwordField;// �����ı������
    private userInfoBean infoBean; // �û���Ϣ��JavaBean����

    /**
     * �������FTP�û��Ի���
     *
     * @param frame
     *            �Ի���ĸ�����
     */
    public edit(userCtrl frame) {
        super(frame); // ���ø���Ĺ��췽��
        dialog = frame; // ��ֵ���������
        initComponents(); // ���ó�ʼ���Ի������ķ���
    }

    /**
     * �����༭FTP�û��Ի���
     *
     * @param frame
     *            �����������
     * @param bean
     *            FTP�û���Ϣ��JavaBean
     */
    public edit(userCtrl frame, userInfoBean bean) {
        this(frame);
        dialog = frame; // ��ֵ���������ö���
        this.infoBean = bean; // ��ֵFTP�û����ö���
        initInput(); // ��ʼ���������������
        setTitle("�༭FTP�û�"); // ���öԻ���ı���
    }

    /**
     * ��ʼ����ӽ���ķ���
     */
    public void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setTitle("���FTP�û�");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0, 1, 4, 3));

        content.add(new JLabel("�û�����"));
        userNameField = new JTextField();
        content.add(userNameField);
        content.add(new JLabel("���룺"));
        passwordField = new JTextField();
        content.add(passwordField);

        JPanel panel = new JPanel();
        final FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(20);
        panel.setLayout(layout);
        final Insets insets = new Insets(0, 8, 0, 8);
        JButton okButton = new JButton("ȷ��");
        okButton.setActionCommand("ok");
        okButton.addActionListener(this);
        JButton cancelButton = new JButton("����");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        cancelButton.setMargin(insets);
        okButton.setMargin(insets);
        panel.add(okButton);
        panel.add(cancelButton);
        content.add(panel);
        content.setBorder(new EmptyBorder(4, 6, 4, 6));
        add(content);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 200) / 2, (screenSize.height - 400) / 2,
                317, 383);
        setVisible(true);
    }

    /**
     * ���水ť���¼�������
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // ��ȡ��ť��command����
        if (command.equals("ok")) { // �����ȷ����ť
            try {
                if (dialog == null) {
                    dispose();
                    return;
                }
                // ��ȡ���������ı��������
                String username = userNameField.getText().trim();
                String password = passwordField.getText().trim();
                // �ж��Ƿ���д��ȫ���ı���
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "����дȫ����Ϣ");
                    return;
                }

                // �����û���Ϣ��JavaBean����
                userInfoBean bean = new userInfoBean(username,password);
                // ����Ի����siteBean��Ϊ��
                if (infoBean != null)
                    dialog.delSite(infoBean); // ���ø������delSite����ɾ��ԭ��վ��
                dialog.addSite(bean); // ���ø������addSite�������վ��
                dialog.loadSiteList(); // ���ø������loadSiteList��������վ���б�
                dispose();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (command.equals("cancel")) { // ��������ð�ť
            if (infoBean == null) // ����Ի����infoBean����Ϊ��
                clearInput(); // ��������ı������ݵķ���
            else
                initInput();
        }
    }

    /**
     * ������������ı������ݵķ���
     */
    private void clearInput() {
        userNameField.setText("");
        passwordField.setText("");
    }

    /**
     * ��ʼ���༭����������ݵķ���
     */
    private void initInput() {
        userNameField.setText(infoBean.getUserName()); // �����û���
        passwordField.setText(infoBean.getPassWord()); // ����FTP��ַ
    }
}
