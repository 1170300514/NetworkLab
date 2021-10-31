package main.tcpfile;

/**
 * Created by xyzhang on 2021/10/20 8:59
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
class TCPFileServer{
    public static void main(String argv[]) throws Exception {
        String ClientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader infromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
            ClientSentence = infromClient.readLine();
            System.out.printf("Target File Name: %s \n", ClientSentence);
            try{
                RandomAccessFile in = new RandomAccessFile(ClientSentence,"rw");
                String s;
                String total = " ";
                while ((s = in.readLine()) != null) {
                    total = total + s;
                }
                System.out.printf("File Content is %s \n", total);
                outToClient.writeBytes(total+'\n');
                in.close();
            } catch(Exception e){
                outToClient.writeBytes("文件不存在！");
            }
        }
    }
}
