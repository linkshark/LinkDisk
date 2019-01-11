package com.linkjb.IOTest;

import org.junit.Test;

import java.io.*;

/**
 * @author sharkshen
 * @data 2019/1/11 17:11
 * @Useage
 */
public class OutputStreamTest {
    @Test
    public void OutPutStreamDemo01() throws IOException {
        String fileName="D:"+ File.separator+"hello.txt";
        File f = new File(fileName);
        OutputStream out = new FileOutputStream(f);
        String str = "Hello World";
        byte[] b = str.getBytes();
        out.write(b);
        out.close();
    }

    @Test
    //向文件中追加新内容
    public void OutPutStreamDemo02() throws IOException {
        String fileName="D:"+ File.separator+"hello.txt";
        File f = new File(fileName);
        OutputStream out = new FileOutputStream(f,true);//true表示追加模式，否则为覆盖
        String str = "Hello World";
        byte[] b = str.getBytes();
        out.write(b);
        out.close();
    }


    //复制文件
    public void OutPutStreamDemo03(String[] args) throws IOException {
        if(args.length!=2){
            System.out.println("命令行参数输入有误，请检查");
            System.exit(1);
        }
        File file1=new File(args[0]);
        File file2=new File(args[1]);

        if(!file1.exists()){
            System.out.println("被复制的文件不存在");
            System.exit(1);
        }
        InputStream in = new FileInputStream(file1);
        OutputStream out = new FileOutputStream(file2,true);
        if(in!=null&&out!=null){
            int temp = 0;
            while((temp = in.read())!=-1){
                out.write(temp);
            }
        }
        in.close();
        out.close();

    }

    @Test
    public void Test() throws IOException {
        OutPutStreamDemo03(new String[]{"D:"+File.separator+"hello.txt","D:"+File.separator+"Bye.txt"});

    }

    @Test
    //使用内存操作流将一个大写字母转化为小写字母
    public void OutPutStreamDemo04() throws IOException {
        String str = "Hello World";
        ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int tmp = 0;
        while((tmp = input.read())!=-1){
            char ch = (char)tmp;
            output.write(Character.toLowerCase(ch));
        }
        String outstr = output.toString();
        input.close();
        output.close();
        System.out.println(outstr);
    }

    //验证管道流：进程间通信
    /**
     * 消息发送类
     * */
    class Send implements Runnable{
        private PipedOutputStream out=null;
        public Send() {
            out=new PipedOutputStream();
        }
        public PipedOutputStream getOut(){
            return this.out;
        }
        public void run(){
            String message="hello , shark";
            try{
                out.write(message.getBytes());
            }catch (Exception e) {
                e.printStackTrace();
            }try{
                out.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接受消息类
     * */
    class Recive implements Runnable{
        private PipedInputStream input=null;
        public Recive(){
            this.input=new PipedInputStream();
        }
        public PipedInputStream getInput(){
            return this.input;
        }
        public void run(){
            byte[] b=new byte[1000];
            int len=0;
            try{
                len=this.input.read(b);
            }catch (Exception e) {
                e.printStackTrace();
            }try{
                input.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("接受的内容为 "+(new String(b,0,len)));
        }
    }
    @Test
    //ToDo unTest
    public void chanelTest(){
        Send send=new Send();
        Recive recive=new Recive();
        try{
        //管道连接
            send.getOut().connect(recive.getInput());
        }catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(send).start();
        new Thread(recive).start();
    }



}
