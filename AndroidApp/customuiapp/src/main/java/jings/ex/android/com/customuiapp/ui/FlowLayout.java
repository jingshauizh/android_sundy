package jings.ex.android.com.customuiapp.ui;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jings on 2020/6/13.
 */

public class FlowLayout  extends ViewGroup{
    private static final String LOG_TAG = FlowLayout.class.getName();

    private List<View> lineViews;
    private List< List<View>> views; //所有行的view
    private List<Integer> allLinesHeights;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        lineViews = new ArrayList<>();
        views =  new ArrayList<>();
        allLinesHeights = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //    2 为每一个子view 计算测量的 限制信息
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //记录当前行的高度和宽度
        int lineCurWidth = 0; //当前行宽度之和
        int lineCurHeight = 0; //高度是 当前行左右子view 的 高度最大值

        //整个流式布局的
        int flowLayoutWidth = 0;   //所有行的 宽度最大值
        int flowLayoutHeight = 0;  //所有高度值的 累加和

        //初始化参数列表
        init();

        int childCount = this.getChildCount();
        //遍历所有的子view  对子view 进行测量 并 分配具体的 行
        for (int i = 0; i < childCount; i++) {
            View childeView = getChildAt(i);
            //测量子 view 获取 当前 子view的 宽度和 高度
            measureChild(childeView,widthMeasureSpec,heightMeasureSpec);

            int childWidth = childeView.getMeasuredWidth();
            int childHeight = childeView.getMeasuredHeight();


            //当前行剩余的宽度 是否可以容纳 子view
            //如果放不下 换行， 保存当前所有子view  累加行高， 当前高度 宽度 置 0
            if (lineCurWidth + childWidth > widthSize) {  //换行
                views.add(lineViews);
                lineViews = new ArrayList<>();
                flowLayoutWidth = Math.max(flowLayoutWidth, childWidth);
                flowLayoutHeight += lineCurWidth;
                allLinesHeights.add(lineCurHeight);
                lineCurWidth = 0;
                lineCurHeight = 0;
            }
            lineCurWidth += childWidth;
            LayoutParams lp = childeView.getLayoutParams();
            if (lp.height != LayoutParams.MATCH_PARENT) {
                lineCurHeight = Math.max(lineCurHeight, childHeight);
            }
            lineViews.add(childeView);
            if(i == childCount-1){
                flowLayoutWidth = Math.max(flowLayoutWidth, childWidth);
                flowLayoutHeight += lineCurWidth;
                views.add(lineViews);
                allLinesHeights.add(lineCurHeight);
            }


        }
        setMeasuredDimension(widthModel == MeasureSpec.EXACTLY ? widthSize : flowLayoutWidth,
            heightModel == MeasureSpec.EXACTLY ? heightSize : flowLayoutHeight);

        //重新测量一次 处理 match_parent
        reMeasureChild(widthMeasureSpec,heightMeasureSpec);

    }

    private void reMeasureChild(int widthMeasureSpec, int heightMeasureSpec) {
        int lineCount = views.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = views.get(i);  //取一行
            int lineHeight = allLinesHeights.get(i);  //取 行高
            int size = lineViews.size();
            for (int k = 0; k < size; k++) {
                View child = lineViews.get(k);
                LayoutParams lp = child.getLayoutParams();
                if(lp.height == LayoutParams.MATCH_PARENT){
                    int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
                    int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lineHeight);
                    child.measure(childWidthSpec, childHeightSpec);
                }
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineCount = views.size();
        //遍历所有的子view  对子view 进行测量 并 分配具体的 行

        int currentX = 0; //
        int currentY = 0; //
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = views.get(i);  //取一行
            int lineHeight = allLinesHeights.get(i);  //取 行高
            int size = lineViews.size();

            for (int k = 0; k < size; k++) {
                View child = lineViews.get(k);
                int left = currentX;
                int top = currentY;
                int right =left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();
                child.layout(left,top,right,bottom);
                //确定下一个view 的 left 值
                currentX += child.getMeasuredWidth();
            }
            currentX = 0;
            currentY += lineHeight;

        }
    }
}
