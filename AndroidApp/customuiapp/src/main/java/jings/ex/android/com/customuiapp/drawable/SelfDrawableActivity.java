package jings.ex.android.com.customuiapp.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import jings.ex.android.com.customuiapp.R;
import jings.ex.android.com.customuiapp.paint.Utils;

public class SelfDrawableActivity extends Activity {

    private ImageView mImageView;
    private TaskClearDrawable mTaskClearDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawable);

        mImageView = findViewById(R.id.imageView);
        mTaskClearDrawable = new TaskClearDrawable(this, Utils.dp2px(300), Utils.dp2px(300));
        mImageView.setImageDrawable(mTaskClearDrawable);

        mImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("Zero", "mTaskClearDrawable = " + mTaskClearDrawable.isRunning());
                if (false == mTaskClearDrawable.isRunning()) {
                    mTaskClearDrawable.start();
                }
            }
        });
        
    }
}
