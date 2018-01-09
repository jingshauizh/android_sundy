package com.jingshuai.android.fregmentapp.bitmap;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.bitmap.BitMapRoatateUtil;
import com.jingshuai.appcommonlib.bitmap.BitmapCacheLoadUtil;
import com.jingshuai.appcommonlib.bitmap.BitmapCutUtil;
import com.jingshuai.appcommonlib.bitmap.BitmapUtils;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ActBitMapLoad extends AppCompatActivity {
    @BindView(R.id.btn_bitmap_load)
    Button btnLoad;
    @BindView(R.id.img_bitmap_pic)
    ImageView imgBitmap;

    private ImageLoadTask mImageLoadTask;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_bit_map_load);
        mUnbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_bitmap_load)
    public void loadBitMap(){
        MLog.i("loadBitMap");
        String[] params = {
                "http://images.cnitblog.com/i/169207/201408/112229149526951.png",
                "p2" };
        mImageLoadTask = new ImageLoadTask(getApplicationContext(), null);
        mImageLoadTask.execute(params);
    }


    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        mImageLoadTask.cancel(true);
        super.onDestroy();

    }


    public class ImageLoadTask extends AsyncTask<String, Void, Void> {
        private Handler msgHandler;
        private Bitmap bitmap;
        // ³õÊ¼»¯
        public ImageLoadTask(Context context, Handler msgHandler) {
            this.msgHandler = msgHandler;
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            String p2 = params[1];
            bitmap = BitmapFactory.decodeStream(Request
                    .HandlerData(url));
            //Bitmap bitmapNew = BitmapCacheLoadUtil.setBitmapSize(bitmap,200,200);
            Bitmap bitmapNew = BitMapRoatateUtil.getRoundImage(bitmap,200);

            BitmapCutUtil.recycleBitmap(bitmap);
            //bean.setImage(bitmapNew);
            bitmap = bitmapNew;
            publishProgress(); // Í¨ÖªÈ¥¸üÐÂUI
            return null;
        }

        public void onProgressUpdate(Void... voids) {
            if (isCancelled())
                return;
            // ¸üÐÂUI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgBitmap.setImageBitmap(bitmap);
                }
            });


        }
    }


}
