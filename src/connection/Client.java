package connection;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static final String ip = "127.0.0.1";
    public static int port = 7890;
    private Socket client_socket;

    public Client(){
        try {
            client_socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnection() {
        InputStream inputStream;
        OutputStream outputStream;
        try{
            inputStream = client_socket.getInputStream();
            outputStream = client_socket.getOutputStream();
            BufferedReader sys_bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader client_bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter client_printWriter = new PrintWriter(outputStream, true);
            String warnStr;
            if((warnStr = client_bufferedReader.readLine()) != null){
                System.out.println(warnStr);
            }
            while((warnStr = sys_bufferedReader.readLine()) != null){
                client_printWriter.println(warnStr);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setConnection();
    }

}
