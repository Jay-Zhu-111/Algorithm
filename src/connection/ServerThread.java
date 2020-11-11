package connection;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private final Socket server_socket;

    public ServerThread(Socket server_socket) {
        this.server_socket = server_socket;
    }

    @Override
    public void run() {
        OutputStream outputStream;
        InputStream inputStream;
        //已连接
        try {
            outputStream = server_socket.getOutputStream();
            inputStream = server_socket.getInputStream();
            //给客户端发送数据
            PrintWriter server_printWriter = new PrintWriter(outputStream, true);
            //接受客户端数据
            BufferedReader server_bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            server_printWriter.println("连接成功，服务器待传输");
            while((msg = server_bufferedReader.readLine()) != null){
                System.out.println("接收到客户端数据： " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
