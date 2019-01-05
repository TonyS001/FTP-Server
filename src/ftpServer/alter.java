package ftpServer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class alter extends JDialog implements ActionListener {

    private JTextField dir;

    public alter() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setTitle("�޸ĸ�Ŀ¼");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0, 1, 4, 3));

        content.add(new JLabel("�޸�Ϊ��"));
        dir = new JTextField();
        dir.setText(getPath());
        content.add(dir);

        JPanel panel = new JPanel();
        final FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(20);
        panel.setLayout(layout);
        final Insets insets = new Insets(0, 8, 0, 8);
        JButton okButton = new JButton("ȷ��");
        okButton.setActionCommand("ok");
        okButton.addActionListener(this);
        okButton.setMargin(insets);
        panel.add(okButton);
        content.add(panel);
        content.setBorder(new EmptyBorder(4, 6, 4, 6));
        add(content);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 200) / 2, (screenSize.height - 400) / 2,
                300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // ��ȡ��ť��command����
        if (command.equals("ok")) { // �����ȷ����ť
            try {
                String path = dir.getText().trim();
                if (path.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "����д��Ϣ");
                    return;
                }
                rootDir(path);
                dispose();
            }catch (NullPointerException ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    /**
     * �޸ĸ�Ŀ¼�ķ���
     **/
    public void rootDir(String newPath){
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            Element rootDir = root.getChild("rootDir");
            rootDir.setText(newPath);
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat().setEncoding("gbk"));
            out.output(parse, new FileOutputStream(path));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ��Ŀ¼·���ķ���
     **/
    public String getPath(){
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            return root.getChildText("rootDir");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
