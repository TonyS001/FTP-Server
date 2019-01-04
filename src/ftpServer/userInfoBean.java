package ftpServer;

public class userInfoBean {
    private String id; // ID���
    private String userName; // ��¼�û���
    private String passWord; //����

    public userInfoBean(String name, String pass) {
        id = System.currentTimeMillis() + ""; // ��ֵID���
        this.passWord = pass;
        this.userName = name; // ��ֵ�û���
    }

    public userInfoBean(String obj) {
        id = System.currentTimeMillis() + ""; // ��ֵID���
        String[] strings = obj.split("---");
        this.passWord = strings[1];
        this.userName = strings[0]; // ��ֵ�û���
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
