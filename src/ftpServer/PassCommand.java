package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command{

    public FTPServerFrame frame = main.server_Frame;
    @Override

    public void getResult(String data, Writer writer,ControllerThread t) {
        
        //����û���

        String key = ControllerThread.USER.get();
        String pass = Share.users.get(key);
        String response = null;
        if(pass.equals(data)) {
            Share.loginedUser.add(key);
            t.setIsLogin(true);
            response = "230 User "+key+" Logged in.";
            frame.state.setText("�û�"+key+"�ɹ���½��");
        }
        else {
            response = "530 Wrong Password.";
            frame.state.setText("�û�"+key+"�ɹ�ʧ�ܣ������´򿪷�����");
        }
        try {
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
