package jings.ex.android.com.customuiapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jings on 2020/6/13.
 */

public class StareViewGroup extends ViewGroup {
    private Context mContext;
    private int offset = 100;

    public StareViewGroup(Context context) {
        super(context);
    }

    public StareViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public StareViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StareViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //    1 测量自身
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //    2 为每一个子view 计算测量的 限制信息
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //    3 把 上一步 确定的信息 传递给 每一个子view 然后 子view 开始 measure 自己的尺寸
        int childCount = this.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childeView = getChildAt(i);
            ViewGroup.LayoutParams lp = childeView.getLayoutParams();
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
            childeView.measure(childWidthSpec, childHeightSpec);
        }
        int width = 0;
        int height = 0;
        //    4 获取 子view 测量完成后的 尺寸
        //    5 Viewgroup 根据自身情况 计算自己的尺寸
        switch (widthModel) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    View childeView = getChildAt(i);
                    int widthAddOffset = i * offset + childeView.getMeasuredWidth();
                    width = Math.max(width, widthAddOffset);  //取最大的 宽度
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        switch (heightModel) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    View childeView = getChildAt(i);
                    height += childeView.getMeasuredHeight();
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        //保存自身的 尺寸
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*
        1 遍历子view
        2 确定自己的规则
        3 子view 布局
        4 设置 子view left top right buttom
        5 child.layout
         */
        int left = 0, top = 0, right = 0, bottom = 0;
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childeView = getChildAt(i);
            left = offset * i;
            right = left + childeView.getMeasuredWidth();
            bottom = top + childeView.getMeasuredHeight();
            childeView.layout(left, top, right, bottom);
            top += childeView.getMeasuredHeight();
        }
    }
}
