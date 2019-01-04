package ftpServer;

import java.io.*;

import java.net.Socket;

 /**
 * 处理文件的发送
 **/

public class RetrCommand implements Command{

    @Override

    public void getResult(String data, Writer writer, ControllerThread t) {
        Socket s;
        String desDir = t.getNowDir()+File.separator+data;
        File file = new File(desDir);
        System.out.println(desDir);
        if(file.exists())
        {
            try {
                writer.write("150 Open ASCII Mode...\r\n");
                writer.flush();
                s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()),null,20);
                BufferedOutputStream dataOut = new BufferedOutputStream(s.getOutputStream());
                byte[] buf = new byte[1024];
                InputStream is = new FileInputStream(file);
                while(-1 != is.read(buf)) {
                    dataOut.write(buf);
                }
                dataOut.flush();
                s.close();
                writer.write("220 Transfer Complete...\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                writer.write("550 No such File.\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
