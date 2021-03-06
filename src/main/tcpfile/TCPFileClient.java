package main.tcpfile;

/**
 * Created by xyzhang on 2021/10/20 8:57
 * Email : xyzhang.top@foxmail.com
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
class TCPFileClient{
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost",6789);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Please Enter File Name:");
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER:"+modifiedSentence);
        clientSocket.close();
    }
}
