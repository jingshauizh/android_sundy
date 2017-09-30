package com.ericsson.NewPlayer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by ehaoqii on 9/5/2017.
 */

public class TabItem {
    //正常情况下显示的图片
    private int imageNormal;
    //选中情况下显示的图片
    private int imagePress;
    //tab的名字
    private int title;
    private String titleString;
    private Context ctx;

    //tab对应的fragment
    private Class<? extends Fragment> fragmentClass;

    public View view;
    private View rootView;
    private ImageView imageView;
    private TextView textView;
    private TabHost tabHost;

    public TabItem(Context ctx, int title, Class<? extends Fragment> fragmentClass) {
        this.ctx = ctx;
        this.title = title;
        this.fragmentClass = fragmentClass;

    }

    public TabItem(Context ctx, int imageNormal, int imagePress, int title, Class<? extends Fragment> fragmentClass) {
        this.ctx = ctx;
        this.imageNormal = imageNormal;
        this.imagePress = imagePress;
        this.title = title;
        this.fragmentClass = fragmentClass;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public int getImageNormal() {
        return imageNormal;
    }

    public int getImagePress() {
        return imagePress;
    }

    public int getTitle() {
        return title;
    }

    public String getTitleString() {
        if (title == 0) {
            return "";
        }
        if (TextUtils.isEmpty(titleString)) {
            titleString = ctx.getString(title);
        }
        return titleString;
    }

    public View getView() {
        if (this.view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            this.view = inflater.inflate(R.layout.view_tab_indicator, null);
            this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
            this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
            if (this.title == 0) {
                this.textView.setVisibility(View.GONE);
            } else {
                this.textView.setVisibility(View.VISIBLE);
                this.textView.setText(getTitleString());
            }
            this.imageView.setImageResource(imageNormal);
        }
        return this.view;
    }

    public View getView2() {
        if (this.view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            this.view = inflater.inflate(R.layout.view_tab_nopics_indicator, null);
            this.textView = (TextView) this.view.findViewById(R.id.tab_tv2_text);
            if (this.title == 0) {
                this.textView.setVisibility(View.GONE);
            } else {
                this.textView.setVisibility(View.VISIBLE);
                this.textView.setText(getTitleString());
            }
        }
        return this.view;
    }


    //切换tab的方法
    public void setChecked(boolean isChecked) {
        if (imageView != null) {
            if (isChecked) {
                imageView.setImageResource(imagePress);
            } else {
                imageView.setImageResource(imageNormal);
            }
        }
        if (textView != null && title != 0) {
            if (isChecked) {
                textView.setTextColor(ctx.getResources().getColor(R.color.main_botton_text_select));
            } else {
                textView.setTextColor(ctx.getResources().getColor(R.color.main_bottom_text_normal));
            }
        }
    }
}
