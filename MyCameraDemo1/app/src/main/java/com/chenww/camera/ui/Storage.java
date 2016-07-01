package com.chenww.camera.ui;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera.Size;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


/**
 * for storage picture
 * 
 * @author swj
 *
 */
public class Storage {
	public static final String DCIM = Environment.
	             getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
	
	public static final String DIRECTORY = "/storage/sdcard1/11test/";
	
	public static boolean savePicture(byte[] jpeg,String fileName,
			Size pictureSize,int pictureOrientation){
		
		
		String path = null;
		path = DIRECTORY + File.separator + fileName;
		Log.v("shenwenjian","savePicture DIRECTORY:"+DIRECTORY +" path:"+path);
		/**
		 * test 1
		 */
//		FileOutputStream out = null;
//		try {
//			File dir = new File(DIRECTORY);
//			if(!dir.exists()) {dir.mkdirs();Log.v("shenwenjian","not exits ");}
//			out = new FileOutputStream(path);
//			out.write(jpeg);
//			out.close();
//		} catch (Exception e) {
//			Log.v("shenwenjian","1 return false");
//			return false;
//		} finally{
//			try {
//				out.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return true;
		/**
		 * test 2
		 */
//		// picture operate
//		Bitmap bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
//		if(bitmap == null){
//			Log.v("shenwenjian","bitmap == null");
//			return false;
//		}
//		// picture operate
//		BufferedOutputStream out = null;
//		try {
//			File dir = new File(DIRECTORY);
//			if(!dir.exists()) {dir.mkdirs();Log.v("shenwenjian","not exits ");}
//			out = new BufferedOutputStream(new FileOutputStream(path));
//			bitmap.compress(CompressFormat.JPEG, 80, out);
//			out.close();
//			bitmap.recycle();
//		} catch (Exception e) {
//			return false;
//		}
		/**
		 * YUV TO RGB
		 * test 3
		 */
		FileOutputStream out = null;
		try {
			File dir = new File(DIRECTORY);
			if(!dir.exists()) {dir.mkdirs();Log.v("shenwenjian","not exits ");}
			YuvImage yuvImage = new YuvImage(jpeg, 
					ImageFormat.NV21, pictureSize.width, pictureSize.height, null);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			yuvImage.compressToJpeg(new Rect(0,0,pictureSize.width,pictureSize.height), 100, baos);
			out = new FileOutputStream(path);
			byte[] data = baos.toByteArray();
			out.write(data);
			out.close();
			yuvImage = null;
		} catch (Exception e) {
			return false;
		}
		//readExifInfo(path);
		return true;
	}

	public static boolean savePictureCamera2(byte [] data){
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		Matrix matrix = new Matrix();
		matrix.preRotate(90);
		bitmap = Bitmap.createBitmap(bitmap ,0,0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);

		//创建并保存图片文件
		String filename =String.valueOf( new Date().getTime());
		File pictureFile = new File(getDir(), filename+"ca.jpg");
		try {
			Log.i("Demo","pictureFile="+pictureFile.getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(pictureFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
			return true;
		} catch (Exception error) {
			Log.d("Demo", "保存照片失败" + error.toString());
			error.printStackTrace();
			return false;

		}
	}

	//获取文件夹
	private static File getDir()
	{
		//得到SD卡根目录
		File dir = Environment.getExternalStorageDirectory();
		dir = new File("/storage/sdcard1/11test/");
		if (dir.exists()) {
			return dir;
		}
		else {
			dir.mkdirs();
			return dir;
		}
	}
	public static void readExifInfo(String path){
		ExifInterface exifInterface = null;
		try {
			exifInterface = new ExifInterface(path);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int tag = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
		int orientation = 0;
		switch(tag){
		case ExifInterface.ORIENTATION_ROTATE_90:  orientation = 90;  break;
		case ExifInterface.ORIENTATION_ROTATE_180: orientation = 180; break;
		case ExifInterface.ORIENTATION_ROTATE_270: orientation = 270; break;
		}
		
		try {
			exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, "90");
			exifInterface.saveAttributes();
			//exifInterface.
		} catch (IOException e) {
			Log.v("shenwenjian","saveAttributes fail");
			e.printStackTrace();
		}
		Log.v("shenwenjian","tag:"+tag+" orientation:"+orientation+"get ori:"+exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1));
	}
	public static void rotatePicture(int orientation,String patch,int width,int height){
		//Options option = new Options();
	}
	
}
