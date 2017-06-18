package com.jingshuai.android.fregmentapp.customer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jingshuai.android.fregmentapp.R;

import java.util.Calendar;

/**
 * Created by eqruvvz on 10/12/2016.
 */
public class IODView extends View {
    private int width;
    private int height;
    private int circleX ;
    private int circleY ;
    private int circleRadius = 150;
    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    private  String textValue;
    private Paint mPaint;
    private Paint mPaintText;
    private Integer phase =1;
    private Integer jiaodu = 0;
    public static final int NEED_INVALIDATE = 0X23;
    private Calendar mCalendar;
    //每隔一秒，在handler中调用一次重新绘制方法
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case NEED_INVALIDATE:
                    jiaodu += 4;

                    invalidate();//告诉UI主线程重新绘制
                    if(jiaodu >= 360){
                        jiaodu = 0;
                    }else{
                        handler.sendEmptyMessageDelayed(NEED_INVALIDATE,50);
                    }

                    break;
                default:
                    break;
            }
        }
    };

    public IODView(Context context) {
        super(context);
    }



    public IODView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



    }

    public IODView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        //mPaint.setTextSize(mTitleTextSize);
        // mPaint.setColor(this.getResources().getColor(R.color.blue));
        handler.sendEmptyMessage(NEED_INVALIDATE);//向handler发送一个消息，让它开启重绘
        mPaintText = new Paint();
        mPaintText.setColor(Color.BLUE);
        mPaintText.setStrokeWidth(10);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(30);
        TypedArray t = getContext().obtainStyledAttributes(attrs,R.styleable.RadarView);
        textValue = t.getString(R.styleable.RadarView_textValue);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        this.setTextValue("555");
        handler.sendEmptyMessageDelayed(NEED_INVALIDATE,50);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[] {20, 10}, 1));
        mPaint.setStrokeWidth(4.0f);
        mPaint.setColor(Color.GREEN);
        //canvas.drawCircle(300.0f,400.0f,80.0f,mPaint);
        //canvas.drawCircle(300.0f,400.0f,150.0f,mPaint);
         circleX = 500;
         circleY = 500;
        canvas.drawRect(circleX, circleY, circleX+100,circleY+100, mPaint);
        canvas.drawRect(150, 200, circleX+350,circleY+350, mPaint);

        mPaint.setStrokeWidth(5.0f);
        mPaint.setColor(Color.GREEN);
        mPaint.setPathEffect(new DashPathEffect(new float[] {20, 10}, 1));
        Path mPath = new Path();
        //
        int line1X = circleX;
        int line1y = circleY;
        int line1ToX = 150;
        int line1ToY = 200;
        mPath.moveTo(line1X, line1y);
        mPath.lineTo(line1ToX,line1ToY);
        canvas.drawPath(mPath,mPaint);



        //drawCircleRoated(canvas,jiaodu);

    }

    private void drawCircleRoated(Canvas canvas, Integer mParameter){

        Integer angle = mParameter; // angle of rotation in radians
        Paint myPaint = new Paint();
        myPaint.setStrokeWidth(6.0f);
        myPaint.setColor(Color.BLUE);
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.save();
        canvas.rotate(angle,circleX,circleY);
        float radius = circleRadius; // the difference in y positions or the radius
        double dx = circleX  ; // the draw x
        double dy = circleY - radius; // the draw y
        canvas.drawLine(circleX,circleY,(float)dx,(float)dy,myPaint);
        canvas.restore();
        //canvas.drawText(""+mParameter, circleX, circleY+10, mPaintText);
        //textValue
        canvas.drawText(""+textValue, circleX, circleY+10, mPaintText);
        canvas.drawCircle(circleX, circleY,30, mPaint);

    }


    private void drawCircleRoated2(Canvas canvas, Integer mParameter){

        Double angle = mParameter * Math.PI / 180; // angle of rotation in radians
        Paint myPaint = new Paint();
        myPaint.setStrokeWidth(6.0f);
        myPaint.setColor(Color.BLUE);
        myPaint.setStyle(Paint.Style.STROKE);
        float radius = circleRadius; // the difference in y positions or the radius
        double dx = circleX + radius * Math.sin(angle); // the draw x
        double dy = circleY - radius * Math.cos(angle); // the draw y
        canvas.drawLine(circleX,circleY,(float)dx,(float)dy,myPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        if(width > height){
            circleRadius = height/3;
        }
        else{
            circleRadius = width/3;
        }
        setMeasuredDimension(width, height);
    }

}
