package connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket ss;
    public static final int port = 7890;

    public Server(){
        try {
            ss = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void setConnection() {
        Socket server_socket;
        while(true){
            try {
                //阻塞接受连接
                server_socket = ss.accept();
                new ServerThread(server_socket).start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.setConnection();
    }
}
