package ftpServer;

public class userInfoBean {
    private String id; // ID编号
    private String userName; // 登录用户名
    private String passWord; //密码

    public userInfoBean(String name, String pass) {
        id = System.currentTimeMillis() + ""; // 赋值ID编号
        this.passWord = pass;
        this.userName = name; // 赋值用户名
    }

    public userInfoBean(String obj) {
        id = System.currentTimeMillis() + ""; // 赋值ID编号
        String[] strings = obj.split("---");
        this.passWord = strings[1];
        this.userName = strings[0]; // 赋值用户名
    }

    public String getId() {return id;}
    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setId(String id) { this.id = id;}
}
