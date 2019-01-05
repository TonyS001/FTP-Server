package ftpServer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;

public class userCtrl extends JDialog implements ActionListener {
    private Properties siteInfo = new Properties();
    private JList list; // ��ʾ�б����
    private FTPServerFrame frame;

    public userCtrl() {
        super();
        initComponents();
    }

    public userCtrl(FTPServerFrame frame) {
        super(frame);
        this.frame = frame;
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setTitle("FTP�û�����");
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(5);
        setLayout(borderLayout);

        list = new JList();
        final BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
        list.setBorder(bevelBorder);
        loadSiteList();
        JScrollPane scrollPanel = new JScrollPane(list);
        add(scrollPanel, CENTER);

        JPanel controlPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(boxLayout);
        JButton addButton = new JButton("���");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        JButton editButton = new JButton("�༭");
        editButton.setActionCommand("edit");
        editButton.addActionListener(this);
        JButton delButton = new JButton("ɾ��");
        delButton.setActionCommand("del");
        delButton.addActionListener(this);
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(delButton);
        add(controlPanel, EAST);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 200) / 2, (screenSize.height - 430) / 2,
                260, 360);
        setVisible(true);
    }

    /**
     * װ�����ݵķ���
     */
    public void loadSiteList() {
        loadSiteProperties();
        Enumeration<Object> keys = siteInfo.keys(); // ��ȡ���Լ��ϵļ�ֵ����
        DefaultListModel model = new DefaultListModel(); // �����б��������ģ��
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement(); // ��ȡÿ����ֵ
            String value = siteInfo.getProperty(key); // ��ȡÿ����ֵ������
            model.addElement(key+"---"+value); // ��վ����Ϣ������ӵ��б������ģ����
        }
        list.setModel(model); // �����б����ʹ�ô�����ģ��
    }

    private void loadSiteProperties() {
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            Element usersE = root.getChild("users");
            List<Element> usersEC = usersE.getChildren();
            String username = null;
            String password = null;
            for(Element user : usersEC) {
                username = user.getChildText("username");
                password = user.getChildText("password");
                siteInfo.put(username,password);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // ��ȡ������ά����ť
        if (command.equals("add")) { // �������Ӱ�ť
            edit dialog = new edit(this); // ��ʾվ��Ի���
        }
        // ��ȡ�б����ѡ���JavaBean����
        String getList = (String)list.getSelectedValue();
        if (getList == null)
            return;
        userInfoBean bean = new userInfoBean(getList);
        if (command.equals("edit")) { // ����Ǳ༭��ť
            edit dialog = new edit(this, bean); // ��ʾվ��Ի�����б༭
        }
        if (command.equals("del")) { // �����ɾ����ť
            delSite(bean); // ����delSite()����
            loadSiteList();
        }
    }

    /**
     * ����û��ķ���
     */
    public void addSite(userInfoBean bean) {
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            Element users = root.getChild("users");
            Element user = new Element("user");
            Element username = new Element("username");
            Element password = new Element("password");
            username.setText(bean.getUserName());
            password.setText(bean.getPassWord());
            user.addContent(username);
            user.addContent(password);
            users.addContent(user);
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat().setEncoding("gbk"));
            out.output(parse, new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    /**
     * ɾ���û��ķ���
     */
    public void delSite(userInfoBean bean) {
        siteInfo.remove(bean.getUserName());
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            Element usersE = root.getChild("users");
            List<Element> usersEC = usersE.getChildren();
            for (Element user : usersEC) {
                if (user.getChild("username").getText().matches(bean.getUserName()))
                    usersE.removeContent(user);
            }
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat().setEncoding("gbk"));
            out.output(parse, new FileOutputStream(path));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
