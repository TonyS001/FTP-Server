package ftpServer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class edit extends JDialog implements ActionListener {
    private userCtrl dialog; // 保存父窗体的引用对象
    private JTextField userNameField; // 用户名文本框组件
    private JTextField passwordField;// 密码文本框组件
    private userInfoBean infoBean; // 用户信息的JavaBean对象

    /**
     * 创建添加FTP用户对话框
     *
     * @param frame
     *            对话框的父窗体
     */
    public edit(userCtrl frame) {
        super(frame); // 调用父类的构造方法
        dialog = frame; // 赋值父窗体对象
        initComponents(); // 调用初始化对话框界面的方法
    }

    /**
     * 创建编辑FTP用户对话框
     *
     * @param frame
     *            父窗体的引用
     * @param bean
     *            FTP用户信息的JavaBean
     */
    public edit(userCtrl frame, userInfoBean bean) {
        this(frame);
        dialog = frame; // 赋值父窗体引用对象
        this.infoBean = bean; // 赋值FTP用户引用对象
        initInput(); // 初始化界面组件的内容
        setTitle("编辑FTP用户"); // 设置对话框的标题
    }

    /**
     * 初始化添加界面的方法
     */
    public void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setTitle("添加FTP用户");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0, 1, 4, 3));

        content.add(new JLabel("用户名："));
        userNameField = new JTextField();
        content.add(userNameField);
        content.add(new JLabel("密码："));
        passwordField = new JTextField();
        content.add(passwordField);

        JPanel panel = new JPanel();
        final FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(20);
        panel.setLayout(layout);
        final Insets insets = new Insets(0, 8, 0, 8);
        JButton okButton = new JButton("确定");
        okButton.setActionCommand("ok");
        okButton.addActionListener(this);
        JButton cancelButton = new JButton("重置");
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
     * 界面按钮的事件处理方法
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // 获取按钮的command属性
        if (command.equals("ok")) { // 如果是确定按钮
            try {
                if (dialog == null) {
                    dispose();
                    return;
                }
                // 获取界面所有文本框的内容
                String username = userNameField.getText().trim();
                String password = passwordField.getText().trim();
                // 判断是否填写了全部文本框
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "请填写全部信息");
                    return;
                }

                // 创建用户信息的JavaBean对象
                userInfoBean bean = new userInfoBean(username,password);
                // 如果对话框的siteBean不为空
                if (infoBean != null)
                    dialog.delSite(infoBean); // 调用父窗体的delSite方法删除原有站点
                dialog.addSite(bean); // 调用父窗体的addSite方法添加站点
                dialog.loadSiteList(); // 调用父窗体的loadSiteList方法重载站点列表
                dispose();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (command.equals("cancel")) { // 如果是重置按钮
            if (infoBean == null) // 如果对话框的infoBean属性为空
                clearInput(); // 调用清除文本框内容的方法
            else
                initInput();
        }
    }

    /**
     * 清除界面所有文本框内容的方法
     */
    private void clearInput() {
        userNameField.setText("");
        passwordField.setText("");
    }

    /**
     * 初始化编辑界面组件内容的方法
     */
    private void initInput() {
        userNameField.setText(infoBean.getUserName()); // 设置用户名
        passwordField.setText(infoBean.getPassWord()); // 设置FTP地址
    }
}
