package com.arouter.jingshuai.javademo.utils;

/**
 * Created by jings on 2020/7/3.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



/**
 * 合并sql脚本文件工具类
 *5、在贴个合并文件的代码，每个文件介绍之后都加入换行代码，
 * 其中使用了普通Io和Nio，这个是我自己的需求，
 * 文件目录例如:c:/文件夹1/文件夹2/test.txt，代码中传入参数为c:/文件夹1


 * @author webapp
 *
 */

public class MergeSqlFileUtil {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String fileName = ".md";


    public static void main(String [] args){
        System.out.println("11111111");
        String filepath = "I:\\1mat\\优化";
        mergerNIO(filepath);
    }

    /**
     * 获取当前路径下的所有文件中
     *
     * @time 2014年8月25日
     * @auther yuxu.feng
     */
    private static List<File> getFiles(String filepath) {
        File file = new File(filepath);
        if (!file.exists() | file.listFiles(new MyFileFileter()) == null) return null;
        List<File> fileList = Arrays.asList(file.listFiles(new MyFileFileter()));
        // 最好在这里把 文件过滤掉 只获取文件夹
        //return sortFolder(fileList);
        return fileList;
    }

    /**
     * 按照文件夹按照文件名进行升序 或按着文件名的名字进行升序
     *
     * @time 2014年8月25日
     * @auther yuxu.feng
     */
    private static List<File> sortFolder(List<File> files) {
        if (files.size() == 0) return null;
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) return -1;
                if (o1.isFile() && o2.isDirectory()) return 1;
                if (o1.isDirectory() && o2.isDirectory())
                    return sortFolderName(o1.getName(), o2.getName());
                else return 0;
            }
        });
        return files;
    }

    /**
     * 根据文件夹名称排序
     *
     * @time 2014年8月25日
     * @auther yuxu.feng
     */
    private static int sortFolderName(String startName, String endName) {
        if ((parseFloderName(startName) - parseFloderName(endName)) >= 0) return 1;
        else return -1;
    }

    /**
     * 将文件夹的名字(格式为 A.B.C.D) 转换为long型的数字
     *
     * @time 2014年8月25日
     * @auther yuxu.feng
     */
    private static long parseFloderName(String floderName) {
        Scanner sc = new Scanner(floderName).useDelimiter("\\.");
        return (sc.nextLong() << 24) + (sc.nextLong() << 16)
            + (sc.nextLong() << 8) + (sc.nextLong());
    }

    /**
     * 使用NIO进行文件合并
     *
     * @time 2014年8月25日
     * @auther yuxu.feng
     */
    public static void mergerNIO(String filepath) {

        SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = fd.format(new Date());
        List<File> fileList = getFiles(filepath);

        if (fileList == null) return;
        File fout = new File(filepath + File.separator + nowTime + fileName);

        try {
            @SuppressWarnings("resource") FileChannel mFileChannel = new FileOutputStream(fout)
                .getChannel();
            FileChannel inFileChannel;
            List<File> files = null;

            for (File folder : fileList) {
                if (folder.isDirectory()) {
                    files = Arrays.asList(folder.listFiles());
                    // sortFolder(files);
                    for (File fin : files) {
                        if (fin.getName().endsWith(fileName)) {
                            inFileChannel = new FileInputStream(fin).getChannel();
                            inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);

                            inFileChannel.close();

                            mFileChannel.write(ByteBuffer.wrap("\r\n".getBytes()));
                        }
                    }
                }
            }
            mFileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用普通IO进行拷贝文件
     *
     * @time 2014年8月27日
     * @auther yuxu.feng
     */
    public static void mergerIO(String filepath) {
        SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = fd.format(new Date());
        List<File> fileList = getFiles(filepath);

        if (fileList == null) return;
        File fout = new File(filepath + File.separator + nowTime + fileName);
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;

        try {

            outBuff = new BufferedOutputStream(new FileOutputStream(fout));
            List<File> files = null;
            FileInputStream finput = null;
            for (File folder : fileList) {
                if (folder.isDirectory()) {
                    files = Arrays.asList(folder.listFiles());
                    for (File fin : files) {
                        finput = new FileInputStream(fin);
                        inBuff = new BufferedInputStream(finput);
                        byte[] b = new byte[finput.available()];
                        int len;
                        while ((len = inBuff.read(b)) != -1) {
                            outBuff.write(b, 0, len);
                        }

                        inBuff.close();
                        outBuff.flush();
                    }
                }
            }
            outBuff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @time 2014年8月27日
     * @auther yuxu.feng
     */
    public static void mergeByBuffWriter(String filepath) {

        SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = fd.format(new Date());
        List<File> fileList = getFiles(filepath);

        if (fileList == null) return;

        try {
            File fout = new File(filepath + File.separator + nowTime + fileName);// 输出文件位置
            List<File> files = null;
            BufferedReader br = null;
            BufferedWriter bw = null;
            FileOutputStream fops = new FileOutputStream(fout);
            bw = new BufferedWriter(new OutputStreamWriter(fops, "UTF-8"));
            //            bw.write('\ufeff'); 带有bom格式的utf-8
            //            fops.write(new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF}); 带有bom格式的utf-8
            for (File folder : fileList) {
                if (folder.isDirectory()) {
                    files = Arrays.asList(folder.listFiles());
                    for (File fin : files) {
                        FileInputStream finput = new FileInputStream(fin);
                        br = new BufferedReader(new InputStreamReader(finput, "UTF-8"));
                        char[] cbuf = new char[finput.available()];
                        int len = cbuf.length;
                        int off = 0;
                        int ret = 0;
                        while ((ret = br.read(cbuf, off, len)) > 0) {
                            off += ret;
                            len -= ret;
                        }
                        br.close();
                        bw.write(cbuf, 0, off);

                        bw.newLine();
                        bw.flush();
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class MyFileFileter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if(pathname.isDirectory()){
                return true;
            }
            return false;
        }
    }
}

