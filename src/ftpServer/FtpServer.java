package ftpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    private int port;
    ServerSocket serverSocket;
    public FTPServerFrame frame = main.server_Frame;
    
    public FtpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        //��ʼ��ϵͳ��Ϣ
        
        Share.init();
    }
    
    public void listen() throws IOException {
        Socket socket = null;
            
        //����ǽ�������,�������ֵĹ��̣������ӽ�����֮������socket֮���ͨѶ��ֱ��ͨ�������еģ�������ͨ����һ��
            
        socket = serverSocket.accept();
        InetAddress ip = socket.getInetAddress();
        frame.state.setText(ip.toString().replace('/',' ')+"��������");
        ControllerThread thread = new ControllerThread(socket);
        thread.start();
    }
}
