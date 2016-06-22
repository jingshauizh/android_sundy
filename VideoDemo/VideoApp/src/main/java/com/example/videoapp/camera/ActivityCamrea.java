package com.example.videoapp.camera;

import android.app.Activity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Bundle;

import com.example.videoapp.R;


public class ActivityCamrea extends Activity {



    SurfaceView sView;
    SurfaceHolder surfaceHodler;
    int screenWidth, screenHeight;
    // 定义系统所用的照相机
    Camera camera;
    // 是否存在预览中
    boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_activity_camrea);
        // 获取窗口管理器
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获取屏幕的宽和高
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        sView = (SurfaceView) findViewById(R.id.SurfaceView1);
        // 设置surface不需要自己的维护缓存区
        sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 获得SurfaceView的SurfaceHolder
        surfaceHodler = sView.getHolder();
        // 为srfaceHolder添加一个回调监听器
        surfaceHodler.addCallback(new Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder arg0) {
                // 如果camera不为null，释放摄像头
                if (camera != null) {
                    if (isPreview)
                        camera.stopPreview();
                    camera.release();
                    camera = null;
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder arg0) {
                // 打开摄像头
                initCamera();

            }

            @Override
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
                                       int arg3) {
            }
        });
    }

    private void initCamera() {
        if (!isPreview) {
            // 此处默认打开后置摄像头
            // 通过传入参数可以打开前置摄像头
            camera = Camera.open();
            camera.setDisplayOrientation(90);
        }
        if (!isPreview && camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            // 设置预览照片的大小
            parameters.setPreviewSize(screenWidth, screenHeight);
            // 设置预览照片时每秒显示多少帧的最小值和最大值
            parameters.setPreviewFpsRange(4, 10);
            // 设置照片的格式
            parameters.setPictureFormat(ImageFormat.JPEG);
            // 设置JPG照片的质量
            parameters.set("jpeg-quality", 85);
            // 设置照片的大小
            parameters.setPictureSize(screenWidth, screenHeight);
            // 通过SurfaceView显示取景画面
            try {
                camera.setPreviewDisplay(surfaceHodler);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 开始预览
            camera.startPreview();
            isPreview = true;
        }
    }

    public void capture(View source) {
        if (camera != null) {
            // 控制摄像头自动对焦后才拍摄
            camera.autoFocus(autoFocusCallback);
            camera.enableShutterSound(false);
        }
    }

    AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean arg0, Camera arg1) {
            if (arg0) {
                // takePicture()方法需要传入三个监听参数
                // 第一个监听器；当用户按下快门时激发该监听器
                // 第二个监听器；当相机获取原始照片时激发该监听器
                // 第三个监听器；当相机获取JPG照片时激发该监听器
                camera.takePicture(new ShutterCallback() {

                    @Override
                    public void onShutter() {
                        // 按下快门瞬间会执行此处代码
                    }
                }, new PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] arg0, Camera arg1) {
                        // 此处代码可以决定是否需要保存原始照片信息
                    }
                }, myJpegCallback);
            }

        }
    };
    PictureCallback myJpegCallback = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
                    data.length);
            String potoName = String.valueOf(new Date().getTime());
            // 创建一个位于SD卡上的文件
            File file = new File(Environment
                    .getExternalStorageDirectory()
                    + "/11test/"
                    + potoName
                    + ".jpg");
            FileOutputStream  fileOutStream=null;
            try {
                fileOutStream=new FileOutputStream(file);
                //把位图输出到指定的文件中
                bm.compress(CompressFormat.JPEG, 100, fileOutStream);
                fileOutStream.close();
            } catch (IOException io) {
                io.printStackTrace();
            }

            camera.stopPreview();
            camera.startPreview();
            isPreview=true;
        }
    };



}
