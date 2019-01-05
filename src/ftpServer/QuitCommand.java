package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class QuitCommand implements Command{

    @Override
    public void getResult(String data, Writer writer, ControllerThread t) {
        try {
            writer.write("221 Goodbye.\r\n");
            writer.flush();
            writer.close();
            t.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
