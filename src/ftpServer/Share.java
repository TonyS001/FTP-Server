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
 * 所有线程共享的变量
 **/

public class Share {

    /*根目录的路径*/

    public static  String rootDir = "C:"+File.separator;

    /*允许登录的用户*/

    public static Map<String,String> users = new HashMap<String,String>();

    /*已经登录的用户*/

    public static HashSet<String> loginedUser = new HashSet<String>();

    /*拥有权限的用户*/

    public static HashSet<String> adminUsers = new HashSet<String>();

    //初始化根目录，权限用户，能够登录的用户信息

    public static void init(){
        String path = System.getProperty("user.dir") + "/server.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document parse = builder.build(file);
            Element root = parse.getRootElement();
            
            //配置服务器的默认目录

            rootDir = root.getChildText("rootDir");

            //允许登录的用户

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
