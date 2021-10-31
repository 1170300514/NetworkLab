package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by xyzhang on 2021/10/31 17:23
 * Email : xyzhang.top@foxmail.com
 */
public class Application {
    public static void main(String[] args) throws IOException {
        String fileName = "resource\\html\\BookNotFound.html";
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(fileName);
        int length = (int) file.length();
        byte[] fileInByte = new byte[length];
        inputStream.read(fileInByte);
        String fileContent = new String(fileInByte);
        System.out.println("fileName:"+fileName+"\n fileContent:"+fileContent);
    }
}
