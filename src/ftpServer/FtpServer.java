package ftpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    private int port;
    ServerSocket serverSocket;
    
    public FtpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        //��ʼ��ϵͳ��Ϣ
        
        Share.init();
    }
    
    public void listen() throws IOException {
        Socket socket = null;
            
        //����ǽ�������,�������ֵĹ��̣������ӽ�����֮������socket֮���ͨѶ��ֱ��ͨ�������еģ�������ͨ����һ��
            
        socket = serverSocket.accept();
        ControllerThread thread = new ControllerThread(socket);
        thread.start();
    }
}