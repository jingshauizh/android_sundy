package com.jingshuai.java.study.io.streaming.chars;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Print {  
	   
/** 
 * @param args 
 */  
public static void main(String[] args) {  
    // TODO�Զ����ɵķ������  
    char[] buffer=new char[512];   //һ��ȡ�����ֽ�����С,��������С  
    int numberRead=0;  
    FileReader reader=null;        //��ȡ�ַ��ļ�����  
    PrintWriter writer=null;    //д�ַ�������̨����  
     
    try {  
       reader=new FileReader("R:/ѧϰ����/q.txt");  
       writer=new PrintWriter(System.out);  //PrintWriter��������ַ����ļ���Ҳ�������������̨  
       while ((numberRead=reader.read(buffer))!=-1) {  
          writer.write(buffer, 0, numberRead);  
       }  
    } catch (IOException e) {  
       // TODO�Զ����ɵ� catch ��  
       e.printStackTrace();  
    }finally{  
       try {  
          reader.close();  
       } catch (IOException e) {  
          // TODO�Զ����ɵ� catch ��  
          e.printStackTrace();  
       }  
       writer.close();       //����������쳣  
    }  
        
}  
   
}  