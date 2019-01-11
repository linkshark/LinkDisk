package com.linkjb.IOTest;

import org.junit.Test;
import scala.Char;

import java.io.*;

/**
 * @author sharkshen
 * @data 2019/1/11 15:18
 * @Useage
 */
public class InputStreamTest {
    @Test
//    该示例中由于b字节数组长度为1024，如果文件较小，则会有大量填充空格。
    public void FileInputStream01() throws IOException {
        String fileNmae = "D:"+ File.separator+"hello.txt";
        File file = new File(fileNmae);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];
        in.read(b);
        in.close();
        System.out.println(new String(b));
    }

    @Test
//    demo1升级
    public void FileInputStream02() throws IOException {
        String fileNmae = "D:" + File.separator + "hello.txt";
        File file = new File(fileNmae);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];
        int len = in.read(b);
        in.close();
        System.out.println("读取长度为:"+len);
        System.out.println(new String(b));
    }

    @Test
//    demo2升级
    //节省空间
    public void FileInputStream03() throws IOException {
        String fileNmae = "D:" + File.separator + "hello.txt";
        File file = new File(fileNmae);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[(int)file.length()];
        in.read(b);
        in.close();
        System.out.println("读取长度为:"+ file.length());
        System.out.println(new String(b));
    }

    @Test
//    demo2升级
    //节省空间,逐字读取
    public void FileInputStream04() throws IOException {
        String fileNmae = "D:" + File.separator + "hello.txt";
        File file = new File(fileNmae);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[(int)file.length()];
        for(int i = 0;i<b.length;i++){
            b[i] = (byte) in.read();
        }
        in.close();
        System.out.println("读取长度为:"+ file.length());
        System.out.println(new String(b));
    }

    @Test
//    demo2升级
    //节省空间,逐字读取
    //上面的几个例子都是在知道文件的内容多大，然后才展开的，
    // 有时候我们不知道文件有多大，这种情况下，我们需要判断是否独到文件的末尾。
    public void FileInputStream05() throws IOException {
        String fileNmae = "D:" + File.separator + "hello.txt";
        File file = new File(fileNmae);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];
        int count = 0;
        int temp = 0;
        while((temp = in.read())!=(-1)){
            b[count++] = (byte)temp;
        }
        in.close();
        //System.out.println("读取长度为:"+ file.length());
        System.out.println(new String(b));
    }

    @Test
//    demo2升级
    //节省空间,逐字读取
    //datainputStream
    public void FileInputStream06() throws IOException {
        String fileNmae = "d:" + File.separator + "hello.txt";
        File file = new File(fileNmae);
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        char[] ch = new char[1024];
        int count = 0;
        char temp;
        while((temp = in.readChar()) != 'C'){
            ch[count++] = temp;
        }
        in.close();
        //System.out.println("读取长度为:"+ file.length());
        System.out.println(ch);
    }







}
