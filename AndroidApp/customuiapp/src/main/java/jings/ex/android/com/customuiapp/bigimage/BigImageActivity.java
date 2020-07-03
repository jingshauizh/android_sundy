package jings.ex.android.com.customuiapp.bigimage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import jings.ex.android.com.customuiapp.R;

public class BigImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        BigImageViewJ bigImageView = findViewById(R.id.iv_big);
        InputStream is = null;
        try {
           is = getAssets().open("big.png");
           // is = getAssets().open("world.jpg");

            bigImageView.setImageInStream(is);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
