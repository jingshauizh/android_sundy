package com.lzy.zjs;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by eqruvvz on 1/17/2018.
 * 1 当前状态只分析到 事件传递
 * 2 还有 SCroller 需要分析
 * 3
 *
 *
 */

public class HeaderViewPagerZjs extends LinearLayout {

    private Boolean isscrollUpDownFlag = false;     // 是否上下滑动
    private float lastY = 0;
    private float lastX = 0;
    private float minMovedistance =20;
    private float offsetDistance = 0;
    private int maxY = -600;
    private int minY = -100;
    private float mLastY;  //最后一次移动的Y坐标
    private int topOffset;
    private View mHeadView;         //需要被滑出的头部
    private int mHeadHeight;        //滑出头部的高度
    private int mCurY;              //当前已经滚动的距离
    private int mTouchSlop;         //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
    private boolean isClickHead;         //当前点击区域是否在头部

    private HeaderScrollHelperN.ScrollableContainer mscrollableView;


    public HeaderViewPagerZjs(Context context) {
        super(context);
    }

    public HeaderViewPagerZjs(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderViewPagerZjs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        maxY = -300;
        minY = -50;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeaderViewPager);
        topOffset = a.getDimensionPixelSize(a.getIndex(R.styleable.HeaderViewPager_hvp_topOffset), topOffset);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();   //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mHeadView = getChildAt(0);
        //force make head view to measure itself
        measureChildWithMargins(mHeadView, widthMeasureSpec, 0, MeasureSpec.UNSPECIFIED, 0);
        mHeadHeight = mHeadView.getMeasuredHeight();
        if(mHeadHeight <= topOffset){
            maxY = 0;
        }
        else{
            maxY = mHeadHeight - topOffset;
        }
        //增加高度 否则 向上移动 底部会上来
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) + maxY, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int touchAction = ev.getAction();
        float currentY = ev.getY();
        float currentX = ev.getX();
        float shiftX = Math.abs(currentX - lastX);   //当前触摸位置与第一次按下位置的X偏移量
        float shiftY = Math.abs(currentY - lastY);   //当前触摸位置与第一次按下位置的Y偏移量
        float deltaY;
        switch(touchAction){
            case MotionEvent.ACTION_DOWN:
                lastY = currentY;
                lastX = currentX;
                checkIsClickHead((int) currentY, mHeadHeight, getScrollY());
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = Math.abs(currentX-lastX);
                float moveY = Math.abs(currentY-lastY);
                deltaY = mLastY - currentY; //连续两次进入move的偏移量
                mLastY = currentY;
                if(moveX > moveY){
                    isscrollUpDownFlag = false;
                }
                if(shiftY > mTouchSlop && moveX <= moveY){
                    isscrollUpDownFlag = true;
                }
                if (isscrollUpDownFlag && (!isStickied() || isTop()||isClickHead)) {
                    //如果是向下滑，则deltaY小于0，对于scrollBy来说
                    //正值为向上和向左滑，负值为向下和向右滑，这里要注意
                    scrollBy(0, (int) (deltaY + 0.5));
                    invalidate();
                }

//                if(currentY <= maxY){
//                    return true;
//                }
                break;
            case MotionEvent.ACTION_UP:
                float moveDatal = lastY - currentY;
                //Log.i("TAG","offsetDistance="+offsetDistance);
//                if(isscrollUpDownFlag ){
//                    offsetDistance -= moveDatal;
//                    scrollBy(0, (int)moveDatal);
//                }
                break;
            default:
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public void scrollBy(int x, int y) {
        int scrollY = getScrollY();
        int toY = scrollY + y;
        if(toY > maxY){
            toY = maxY;
        }
        if(toY < minY){
            toY = minY;
        }
        y = toY-scrollY;
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y >= maxY) {
            y = maxY;
        } else if (y <= minY) {
            y = minY;
        }
        mCurY = y;
        Log.i("TAG", "mCurY="+mCurY);
        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }


    /** 头部是否已经固定 */
    public boolean isStickied() {
        return mCurY >= maxY;
    }
    public int getMaxY() {
        return maxY;
    }
    public boolean isHeadTop() {
        return mCurY == minY;
    }

    private void checkIsClickHead(int downY, int headHeight, int scrollY) {
        isClickHead = ((downY + scrollY) <= headHeight);
    }

    public void setTopOffset(int topOffset) {

        this.topOffset = topOffset;
        //this.topOffset = 100;
    }

    public void setCurrentScrollableContainer(HeaderScrollHelperN.ScrollableContainer scrollableContainer) {
        mscrollableView = scrollableContainer;
    }


/*=========================================================*/



    private View getScrollableView() {
        if (mscrollableView == null) return null;
        return mscrollableView.getScrollableView();
    }


    /**
     * 判断是否滑动到顶部方法,ScrollAbleLayout根据此方法来做一些逻辑判断
     * 目前只实现了AdapterView,ScrollView,RecyclerView
     * 需要支持其他view可以自行补充实现
     */
    public boolean isTop() {
        View scrollableView = getScrollableView();
        if (scrollableView == null) {
            throw new NullPointerException("You should call ScrollableHelper.setCurrentScrollableContainer() to set ScrollableContainer.");
        }
        if (scrollableView instanceof AdapterView) {
            return isAdapterViewTop((AdapterView) scrollableView);
        }
        if (scrollableView instanceof ScrollView) {
            return isScrollViewTop((ScrollView) scrollableView);
        }
        if (scrollableView instanceof RecyclerView) {
            return isRecyclerViewTop((RecyclerView) scrollableView);
        }
        if (scrollableView instanceof WebView) {
            return isWebViewTop((WebView) scrollableView);
        }
        throw new IllegalStateException("scrollableView must be a instance of AdapterView|ScrollView|RecyclerView");
    }


    private boolean isRecyclerViewTop(RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View childAt = recyclerView.getChildAt(0);
                if (childAt == null || (firstVisibleItemPosition == 0 && childAt.getTop() == 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isAdapterViewTop(AdapterView adapterView) {
        if (adapterView != null) {
            int firstVisiblePosition = adapterView.getFirstVisiblePosition();
            View childAt = adapterView.getChildAt(0);
            if (childAt == null || (firstVisiblePosition == 0 && childAt.getTop() == 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isScrollViewTop(ScrollView scrollView) {
        if (scrollView != null) {
            int scrollViewY = scrollView.getScrollY();
            return scrollViewY <= 0;
        }
        return false;
    }

    private boolean isWebViewTop(WebView scrollView) {
        if (scrollView != null) {
            int scrollViewY = scrollView.getScrollY();
            return scrollViewY <= 0;
        }
        return false;
    }
}
