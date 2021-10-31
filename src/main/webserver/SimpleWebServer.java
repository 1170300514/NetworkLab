package main.webserver;

/**
 * Created by xyzhang on 2021/10/20 9:03
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
import java.util.*;

class SimpleWebServer {
    public static void main(String argv[]) throws Exception {
        String requestMessageLine;
        String filename;
        ServerSocket ListenSocket = new ServerSocket(6789);
        Socket connectionSocket = ListenSocket.accept();
        BufferedReader infromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient =
                new DataOutputStream(connectionSocket.getOutputStream());
        requestMessageLine = infromClient.readLine();
        System.out.println("requestMessageLine: "+requestMessageLine);
        StringTokenizer tokenizedLine =
                new StringTokenizer(requestMessageLine);
        System.out.println("tokenizedLine:" + tokenizedLine.toString());
        if (tokenizedLine.nextToken().equals("GET")) {
            filename = tokenizedLine.nextToken();
            String[] strings = filename.split("=");
            filename = strings[1];
            filename = filename.replaceAll("%5C", "\\\\");
            System.out.println("filename:" + filename);
            if (filename.startsWith("/") == true) {
                filename = filename.substring(1);
            }
            File file = null;
            try {
                file = new File(filename);
            } catch (Exception e) {
                outToClient.writeBytes("File Not Found!");
            }
            int numOfBytes = (int) file.length();
            FileInputStream inFile = new FileInputStream(filename);
            byte[] fileInBytes = new byte[numOfBytes];
            inFile.read(fileInBytes);
            outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
            if (filename.endsWith(".jpg")) {
                outToClient.writeBytes("Content-Type:image/jpeg\r\n");
            }
            if (filename.endsWith(".gif")) {
                outToClient.writeBytes("Content-Type:image/gif\r\n");
            }
            outToClient.writeBytes("Content-Length:"+numOfBytes+"\r\n");
            outToClient.writeBytes("\r\n");
            outToClient.write (fileInBytes, 0, numOfBytes);
            connectionSocket.close();
        }
        else {
            System.out.println("Bad Request Message");
        }
    }
}
