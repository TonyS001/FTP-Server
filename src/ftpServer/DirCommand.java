package ftpServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.Socket;
import java.net.UnknownHostException;

public class DirCommand implements Command{

    /**
     * 获取ftp目录里面的文件列表
    **/

    @Override

    public void getResult(String data, Writer writer,ControllerThread t) {
        String desDir = t.getNowDir()+data;
        File dir = new File(desDir);
        if(!dir.exists()) {
            try {
                writer.write("550 No such File or Directory.\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            StringBuilder dirs = new StringBuilder();
            dirs.append("List as Follow:\n");
            String[] lists= dir.list();
            String flag = null;
            for(String name : lists) {
                File temp = new File(desDir+File.separator+name);
                if(temp.isDirectory()) {
                    flag = "d";
                }
                else {
                    flag = "f";
                }
                dirs.append("\t");
                dirs.append(flag);
                dirs.append("  ");
                dirs.append(name);
                dirs.append("\n");
            }

            //开启数据连接，将数据发送给客户端，这里需要有端口号和ip地址

            Socket s;
            try {
                writer.write("150 Open ASCII Mode...\r\n");
                writer.flush();
                s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()),null,20);
                BufferedWriter dataWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                dataWriter.write(dirs.toString());
                dataWriter.flush();
                s.close();
                writer.write("220 Transfer Complete...\r\n");
                writer.flush();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
