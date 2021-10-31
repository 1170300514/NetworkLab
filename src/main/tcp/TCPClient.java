package main.tcp;

/**
 * Created by xyzhang on 2021/10/20 8:34
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
class TCPClient{
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        // 获取标准IO输入信息
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        // 初始化Socket
        Socket clientSocket = new Socket("localhost",6789);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        // 获取服务器返回的字节流
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        // 向Socket中传输数据
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER:"+modifiedSentence);
        clientSocket.close();
    }
}
