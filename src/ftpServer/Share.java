package ftpServer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

 /**
 * �����̹߳���ı���
 **/

public class Share {

    /*��Ŀ¼��·��*/

    public static  String rootDir = "C:"+File.separator;

    /*�����¼���û�*/

    public static Map<String,String> users = new HashMap<String,String>();

    /*�Ѿ���¼���û�*/

    public static HashSet<String> loginedUser = new HashSet<String>();

    /*ӵ��Ȩ�޵��û�*/

    public static HashSet<String> adminUsers = new HashSet<String>();

    //��ʼ����Ŀ¼��Ȩ���û����ܹ���¼���û���Ϣ

    public static void init(){
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            
            //���÷�������Ĭ��Ŀ¼

            rootDir = root.getChildText("rootDir");

            //�����¼���û�

            Element usersE = root.getChild("users");
            List<Element> usersEC = usersE.getChildren();
            String username = null;
            String password = null;
            for(Element user : usersEC) {
                username = user.getChildText("username");
                password = user.getChildText("password");
                users.put(username,password);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
