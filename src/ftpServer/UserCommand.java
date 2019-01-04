package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class UserCommand implements Command{
    /**
     * 检验是否有这个用户名存在
    **/

    @Override

    public void getResult(String data,Writer writer,ControllerThread t) {
        String response = "";
        if(Share.users.containsKey(data)) {
            ControllerThread.USER.set(data);
            response = "331 Need Password for User " + data + ".";
        }
        else {
            response = "501 User not Allowed.";
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
