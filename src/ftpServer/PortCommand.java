package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PortCommand implements Command{

    @Override

    public void getResult(String data, Writer writer,ControllerThread t) {
        String response = "200 Port Transferred.";
        try {
            String[] iAp =  data.split(",");
            String ip = iAp[0]+"."+iAp[1]+"."+iAp[2]+"."+iAp[3];
            String port = Integer.toString(256*Integer.parseInt(iAp[4])+Integer.parseInt(iAp[5]));
            t.setDataIp(ip);
            t.setDataPort(port);
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
