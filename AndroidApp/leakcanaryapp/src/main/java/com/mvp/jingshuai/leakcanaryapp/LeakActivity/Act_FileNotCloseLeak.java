package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.LeakBaseActivity;
import com.mvp.jingshuai.leakcanaryapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Act_FileNotCloseLeak extends LeakBaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_file_not_cloas_leak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        checkPermission();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    writeDataFile("aaaaaaaaaaaaaaa","aa.txt");
                    Act_FileNotCloseLeak.this.finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else
        {
            //startMyService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //startMyService();
            } else
            {
                // Permission Denied
                Toast.makeText(Act_FileNotCloseLeak.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //写数据
    private void writeDataFile(String fileName,String writestr) throws IOException{
        try{
            FileOutputStream fout =openFileOutput(fileName, Context.MODE_PRIVATE | Context.MODE_APPEND);

            byte[] bytes = writestr.getBytes();
            String str = new String(bytes);
            MLog.i("writeDataFile:" + "bytes.toString=" + str + ";");
            fout.write(bytes);
           // fout.close();
        }

        catch(Exception e){
            e.printStackTrace();
            Log.e("","writeDataFile Error!");
        }
    }

    //读数据
    public String readDataFile(String fileName) throws IOException{
        String res = "";
        try{

//            String[] strings = fileList();
//            for(int i = 0; i < strings.length; i++){
//                Log.i(TAG, "fileList String["+i+"]="+strings[i].toString());
//            }
            FileInputStream fin = openFileInput(fileName);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            res = new String(buffer);
            fin.close();

        }
        catch(Exception e){
            e.printStackTrace();
            Log.e("", "readDataFile Error!");
        }
        return res;
    }




















    public  String readerFile(File file) {
        StringBuffer sb = new StringBuffer();
        if (file.isFile()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                // fileInputStream.available()获取文件的字节数
                byte[] b = new byte[fileInputStream.available()];
                int read = fileInputStream.read(b);
                while (read != -1) {
                    sb.append(new String(b));
                    read = fileInputStream.read(b);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
        return null;
    }

    public  void writer(String str, File newfile) {
        FileOutputStream fileOutputStream = null;

        try {
            if(!newfile.exists()){
                newfile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(newfile);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileOutputStream!= null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取文件夹
    private  File getDir()
    {
        //得到SD卡根目录
        File dir = Environment.getExternalStorageDirectory();
        //File dir = Environment.getDataDirectory();
        dir = new File(dir.getPath()+"/aa.txt");
        //dir = new File(dir.getPath()+"/aa.txt");
        MLog.i(dir);
        if (dir.exists()) {
            return dir;
        }
        else {
            dir.mkdirs();
            return dir;
        }
    }



}
