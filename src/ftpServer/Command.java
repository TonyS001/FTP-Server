package ftpServer;

import java.io.Writer;

public interface Command {
        /**
         * @param data    ��ftp�ͻ��˽��յĳ�ftp����֮�������
         * @param writer  ���������
         * @param t       ������������Ӧ�Ĵ����߳�
         * */
        void getResult(String data, Writer writer, ControllerThread t);
}
