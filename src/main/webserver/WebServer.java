package main.webserver;

/**
 * Created by xyzhang on 2021/10/20 9:08
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

class WebServer extends Thread{
    public Socket connectionSocket;
    public String requestMessageLine;
    public String fileName;
    public WebServer(Socket a){
        connectionSocket=a;
    }
    public void run(){
        try{
            BufferedReader inFromClient=
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient=
                    new DataOutputStream(connectionSocket.getOutputStream());
            requestMessageLine=inFromClient.readLine();
            StringTokenizer tokenizedLine= new StringTokenizer(requestMessageLine);
            if(tokenizedLine.nextToken().equals("GET")){
                fileName=tokenizedLine.nextToken();
                String[] strings = fileName.split("=");
                fileName = strings[1];
                fileName = fileName.replaceAll("%5C", "\\\\");
                if(fileName.startsWith("/")==true)
                    fileName=fileName.substring(1);
                File file=new File(fileName);
                int numOfBytes=(int) file.length();
                FileInputStream inFile=new FileInputStream(fileName);
                byte[] fileInBytes=new byte[numOfBytes];
                inFile.read(fileInBytes);
                outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
                if(fileName.endsWith(".jpg"))
                    outToClient.writeBytes("Content-Type:image /jpeg\r\n");
                if(fileName.endsWith(".gif"))
                    outToClient.writeBytes("Content-Type:image /gif\r\n");
                outToClient.writeBytes("Content-Length:"+numOfBytes+"\r\n");
                outToClient.writeBytes("\r\n");
                outToClient.write(fileInBytes,0,numOfBytes);
                System.out.println("Response has been send to the client! Timestamp:"+ System.currentTimeMillis());
            }
            else System.out.println("Bad Request Message");
        } catch(Exception ex){
            System.out.print("ERROR:"+ex.toString());
        }
        try {
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String argv[]) throws Exception {
        ServerSocket listenSocket=new ServerSocket(6789);
        while(true){
            Socket oldconnectionSocket = listenSocket.accept();
            Thread a = new WebServer(oldconnectionSocket);
            a.start();
        }
    }
}
