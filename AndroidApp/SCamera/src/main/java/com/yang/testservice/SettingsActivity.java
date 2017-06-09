package com.yang.testservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class SettingsActivity extends Activity {

    private final String PREFS_NAME = "widefide.cameraPrefs2";
    private Camera cam;
    private int cameraNumber;
    private int cameraPos = 1;
    private Spinner cameraSpinner;
    private Button cancel;
    private int pictureHeight;
    private int pictureQualityNumber;
    private int pictureWidth;
    private int qualityPos = 1;
    private Spinner qualitySpinner;
    private Button save;
    private EditText shutterButtonName;
    private int sizePos = 2;
    private Spinner sizeSpinner;
    private List<Camera.Size> sizes;
    private int vLenPos = 2;
    private List<Camera.Size> vSizes;
    private Spinner videoLenSpinner;
    private int videoLength;
    private int  picCountPerSecond;
    private int picCountPerSecondPos = 2;
    private Spinner picCountPerSecondSpinner;

    private int picTakeTimeLength;
    private int picTakeTimeLengthPos = 0;
    private Spinner picTakeTimeLengthSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_layout);

        getWindow().setSoftInputMode(2);
        this.shutterButtonName = ((EditText) findViewById(R.id.shutterButtonName));
        this.cameraSpinner = ((Spinner) findViewById(R.id.spinnerCamera));
        this.qualitySpinner = ((Spinner) findViewById(R.id.spinnerQuality));
        this.sizeSpinner = ((Spinner) findViewById(R.id.spinnerSize));
        this.videoLenSpinner = ((Spinner) findViewById(R.id.spinnerVLen));
        this.picCountPerSecondSpinner = ((Spinner) findViewById(R.id.spinnerPicCountPerSecond));
        this.picTakeTimeLengthSpinner = ((Spinner) findViewById(R.id.spinnerPicTimeLength));
        this.save = ((Button) findViewById(R.id.saveButton));
        this.cancel = ((Button) findViewById(R.id.cancelButton));
        // paramBundle = (RelativeLayout)findViewById(2131689615);
        RelativeLayout localRelativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        SharedPreferences localSharedPreferences = getSharedPreferences("widefide.cameraPrefs2", 0);
        String str = localSharedPreferences.getString("savedShutterName", "Play");
        this.shutterButtonName.setText(str);
        int i = localSharedPreferences.getInt("savedCameraPos", this.cameraPos);
        this.cameraSpinner.setSelection(i);
        i = localSharedPreferences.getInt("savedQualityPos", this.qualityPos);
        this.qualitySpinner.setSelection(i);
        i = localSharedPreferences.getInt("savedSizePos", this.sizePos);
        this.sizeSpinner.setSelection(i);
        i = localSharedPreferences.getInt("savedVLenPos", this.vLenPos);
        this.videoLenSpinner.setSelection(i);
        i = localSharedPreferences.getInt("savedPicCountPerSecond", this.picCountPerSecondPos);
        this.picCountPerSecondSpinner.setSelection(i);
        i = localSharedPreferences.getInt("savedPicTakeTimeLength", this.picTakeTimeLengthPos);
        this.picTakeTimeLengthSpinner.setSelection(i);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SettingsActivity.this.getAndSaveData();
                Toast.makeText(SettingsActivity.this.getApplicationContext(), "Settings saved.", Toast.LENGTH_SHORT).show();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SettingsActivity.this.startActivity(new Intent(SettingsActivity.this.getApplicationContext(), MainActivity.class));
            }
        });
//        paramBundle.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View paramAnonymousView)
//            {
//                SettingsActivity.this.startActivity(new Intent(SettingsActivity.this.getApplicationContext(), FAQ.class));
//            }
//        });
        localRelativeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Toast.makeText(SettingsActivity.this.getApplicationContext(), "Made in India.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAndSaveData() {
        String str1 = this.shutterButtonName.getText().toString();
        String cameraName = this.cameraSpinner.getSelectedItem().toString();
        String quality = this.qualitySpinner.getSelectedItem().toString();
        String videoLength = this.videoLenSpinner.getSelectedItem().toString();
        String picCountPerSecondStr = this.picCountPerSecondSpinner.getSelectedItem().toString();
        String picTakeTimeLengthStr = this.picTakeTimeLengthSpinner.getSelectedItem().toString();

        settingsTakePic(picCountPerSecondStr,picTakeTimeLengthStr);
        settingsCamera(cameraName);
        settingsHandleQuality(quality);
        settingsHandleVideo(videoLength);
       // setPicSize();

        Object localObject = getSharedPreferences("widefide.cameraPrefs2", 0).edit();
        ((SharedPreferences.Editor) localObject).putString("savedShutterName", str1);
        ((SharedPreferences.Editor) localObject).putInt("savedCamera", this.cameraNumber);
        ((SharedPreferences.Editor) localObject).putInt("savedQuality", this.pictureQualityNumber);
        ((SharedPreferences.Editor) localObject).putInt("savedWidth", this.pictureWidth);
        ((SharedPreferences.Editor) localObject).putInt("savedHeight", this.pictureHeight);
        ((SharedPreferences.Editor) localObject).putInt("savedVLength", this.videoLength);
        ((SharedPreferences.Editor) localObject).putInt("savedCameraPos", this.cameraPos);
        ((SharedPreferences.Editor) localObject).putInt("savedQualityPos", this.qualityPos);
        ((SharedPreferences.Editor) localObject).putInt("savedSizePos", this.sizePos);
        ((SharedPreferences.Editor) localObject).putInt("savedVLenPos", this.vLenPos);

        ((SharedPreferences.Editor) localObject).putInt("savedPicCountPerSecond", this.picCountPerSecondPos);
        ((SharedPreferences.Editor) localObject).putInt("savedPicTakeTimeLength", this.picTakeTimeLengthPos);

        ((SharedPreferences.Editor) localObject).apply();
        finish();
        return;
    }

    private void settingsTakePic( String picCountPerSecondStr, String picTakeTimeLengthStr ){

        if (picCountPerSecondStr.equals("2")) {
            this.picCountPerSecond = 2;
            this.picCountPerSecondPos = 0;
        }
        if (picCountPerSecondStr.equals("3")) {
            this.picCountPerSecond = 3;
            this.picCountPerSecondPos = 1;
        }
        if (picCountPerSecondStr.equals("5")) {
            this.picCountPerSecond = 5;
            this.picCountPerSecondPos = 2;
        }
        if (picCountPerSecondStr.equals("10")) {
            this.picCountPerSecond = 10;
            this.picCountPerSecondPos = 3;
        }

        if (picTakeTimeLengthStr.equals("10")) {
            this.picTakeTimeLength = 10;
            this.picTakeTimeLengthPos = 0;
        }
        if (picTakeTimeLengthStr.equals("20")) {
            this.picTakeTimeLength = 20;
            this.picTakeTimeLengthPos = 1;
        }
        if (picTakeTimeLengthStr.equals("300")) {
            this.picTakeTimeLength = 300;
            this.picTakeTimeLengthPos = 2;
        }
        if (picTakeTimeLengthStr.equals("600")) {
            this.picTakeTimeLength = 600;
            this.picTakeTimeLengthPos = 3;
        }
        if (picTakeTimeLengthStr.equals("3600")) {
            this.picTakeTimeLength = 3600;
            this.picTakeTimeLengthPos = 4;
        }

    }

    private void settingsCamera(String cameraName ){
        if (cameraName.equals("Front Camera")){
            this.cameraNumber = 0;
            this.cameraPos = 0;

        }
        else if (cameraName.equals("Back Camera")) {
            this.cameraNumber = 1;
            this.cameraPos = 1;
        }
    }

    private void settingsHandleQuality(String quality){
        if (quality.equals("Average")) {
            this.pictureQualityNumber = 70;
            this.qualityPos = 0;
        }
        if (quality.equals("Fine")) {
            this.pictureQualityNumber = 85;
            this.qualityPos = 1;
        }
        if (quality.equals("Best")) {
            this.pictureQualityNumber = 100;
            this.qualityPos = 2;
        }


    }

    private void settingsHandleVideo(String videoSelection){
        if (videoSelection.equals("10 Mins")) {
            this.videoLength = 6000000;
            this.vLenPos = 1;
        } else if (videoSelection.equals("30 Mins")) {
            this.videoLength = 18000000;
            this.vLenPos = 2;
        } else if (videoSelection.equals("Unlimited")) {
            this.videoLength = 12345;
            this.vLenPos = 3;
        }
    }

    private void setPicSize() {
        String str = this.sizeSpinner.getSelectedItem().toString();
        Object localObject = this.cam.getParameters();
        this.sizes = ((Camera.Parameters) localObject).getSupportedPictureSizes();
        this.vSizes = ((Camera.Parameters) localObject).getSupportedVideoSizes();
        localObject = this.sizes.iterator();
        Camera.Size localSize;
        while (((Iterator) localObject).hasNext()) {
            localSize = (Camera.Size) ((Iterator) localObject).next();
            System.out.println("Height: " + localSize.height + ". AND Width: " + localSize.width);
        }
        int j = 0;
        int i = 0;
        int i3 = 0;
        int i2 = 0;
        int m = 0;
        int k = 0;
        int i1 = 0;
        int n = 0;
        int i5 = 0;
        int i4 = 0;
        localObject = this.sizes.iterator();
        while (((Iterator) localObject).hasNext()) {
            localSize = (Camera.Size) ((Iterator) localObject).next();
            if ((localSize.width >= 600) && (localSize.width <= 700)) {
                j = localSize.width;
                i = localSize.height;
            } else if ((localSize.width >= 1200) && (localSize.width <= 1300)) {
                i3 = localSize.width;
                i2 = localSize.height;
            } else if ((localSize.width >= 1600) && (localSize.width <= 1700)) {
                m = localSize.width;
                k = localSize.height;
            } else if ((localSize.width >= 2000) && (localSize.width <= 2100)) {
                i1 = localSize.width;
                n = localSize.height;
            } else if ((localSize.width >= 2400) && (localSize.width <= 2700)) {
                i5 = localSize.width;
                i4 = localSize.height;
            }
        }
        if (str.equals("VGA")) {
            this.pictureWidth = j;
            this.pictureHeight = i;
            this.sizePos = 0;
        }
        while (true) {
            System.out.println("Picture Height: " + this.pictureHeight + ". Picture Width " + this.pictureWidth);

            if (str.equals("1.3 MP")) {
                this.pictureWidth = i3;
                this.pictureHeight = i2;
                this.sizePos = 1;
            } else if (str.equals("2 MP")) {
                this.pictureWidth = m;
                this.pictureHeight = k;
                this.sizePos = 2;
            } else if (str.equals("3 MP")) {
                this.pictureWidth = i1;
                this.pictureHeight = n;
                this.sizePos = 3;
            } else if (str.equals("5 MP")) {
                this.pictureWidth = i5;
                this.pictureHeight = i4;
                this.sizePos = 4;
            }
            break;
        }
    }


    protected void onPause() {
        super.onPause();
       // this.cam.release();
       // this.cam = null;
    }

    protected void onResume() {
        super.onResume();
       // this.cam = Camera.open(this.cameraNumber);
    }

}
