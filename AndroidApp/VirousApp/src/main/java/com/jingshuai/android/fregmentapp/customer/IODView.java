package com.jingshuai.android.fregmentapp.customer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Point;
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
    private int focusRectLength=100;
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

        circleX = width-150-200;
        circleY = height/2-50+200;

        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        //mPaint.setPathEffect(new CornerPathEffect(80));
        mPaint.setStrokeWidth(4.0f);
        //mPaint.setColor(Color.GREEN);
        mPaint.setColor(getResources().getColor(R.color.lightskyblue));
        //canvas.drawCircle(300.0f,400.0f,80.0f,mPaint);
        //canvas.drawCircle(300.0f,400.0f,150.0f,mPaint);

        int targetY = height/2-50;

        int bigRectX = 150;
        int bigRectY = 50;
        //canvas.drawRect(bigRectX, bigRectY, width-150,targetY, mPaint);
        drawBigRect(bigRectX,bigRectY,width-150,targetY,50,mPaint, canvas);

        drawFocusRect(circleX,circleY,mPaint,canvas);
        //canvas.drawRect(circleX-50, circleY-50, circleX+50,circleY+50, mPaint);

        mPaint.setStrokeWidth(5.0f);
       // mPaint.setColor(Color.GREEN);
        mPaint.setColor(getResources().getColor(R.color.lightskyblue));
        //mPaint.setPathEffect(new DashPathEffect(new float[] {20, 10}, 1));
        Path mPath = new Path();
        //
        int line1X = circleX-50;
        int line1y = circleY-50;
        int line1ToX = bigRectX;
        int line1ToY = bigRectY;
        mPath.moveTo(line1X, line1y);
        mPath.lineTo(line1ToX,line1ToY);

        mPath.moveTo(circleX+50, circleY+50);
        mPath.lineTo(width-150,targetY);

        mPath.moveTo(circleX+50, circleY-50);
        mPath.lineTo(width-150,line1ToY);

        mPath.moveTo(circleX-50, circleY+50);
        mPath.lineTo(line1ToX,targetY);

        canvas.drawPath(mPath,mPaint);



        //drawCircleRoated(canvas,jiaodu);

    }

    private void drawBigRect(int x,int y,int tox,int toy,int sideLength, Paint mPaint, Canvas canvas){
        //        0 -------- 1
        //
        //        3 -------- 2
        int SIDE_LENGTH = sideLength;
        Paint mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        //mPaint1.setPathEffect(new DashPathEffect(new float[] {20, 10}, 1));
        mPaint1.setStrokeWidth(4.0f);
        mPaint1.setColor(getResources().getColor(R.color.lightskyblue));

        Point point0 = new Point(x,y);
        Point point1 = new Point(tox,y);
        Point point2 = new Point(tox,toy);
        Point point3 = new Point(x,toy);

        //draw point 0 lines
        Point pointStart = new Point(point0.x,point0.y+SIDE_LENGTH);
        Point pointEnd = new Point(point0.x+SIDE_LENGTH,point0.y);
        Path mPath = new Path();
        mPath.moveTo(pointStart.x, pointStart.y);
        mPath.lineTo(point0.x,point0.y);
        mPath.lineTo(pointEnd.x,pointEnd.y);


        //draw point 1 lines
        Point pointStart1 = new Point(point1.x-SIDE_LENGTH,point1.y);
        Point pointEnd1 = new Point(point1.x,point1.y+SIDE_LENGTH);

        mPath.moveTo(pointStart1.x, pointStart1.y);
        mPath.lineTo(point1.x,point1.y);
        mPath.lineTo(pointEnd1.x,pointEnd1.y);


        //draw point 2 lines
        Point pointStart2 = new Point(point2.x-SIDE_LENGTH,point2.y);
        Point pointEnd2 = new Point(point2.x,point2.y-SIDE_LENGTH);

        mPath.moveTo(pointStart2.x, pointStart2.y);
        mPath.lineTo(point2.x,point2.y);
        mPath.lineTo(pointEnd2.x,pointEnd2.y);



        //draw point 3 lines
        Point pointStart3 = new Point(point3.x,point3.y-SIDE_LENGTH);
        Point pointEnd3 = new Point(point3.x+SIDE_LENGTH,point3.y);

        mPath.moveTo(pointStart3.x, pointStart3.y);
        mPath.lineTo(point3.x,point3.y);
        mPath.lineTo(pointEnd3.x,pointEnd3.y);
        canvas.drawPath(mPath,mPaint1);


    }


    private void drawFocusRect(int x,int y,Paint mPaint, Canvas canvas){
        //        0 -------- 1
        //
        //        3 -------- 2
        int SIDE_LENGTH = focusRectLength/4;
        Paint mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        //mPaint1.setPathEffect(new DashPathEffect(new float[] {20, 10}, 1));
        mPaint1.setStrokeWidth(4.0f);
        mPaint1.setColor(getResources().getColor(R.color.lightskyblue));

        Point point0 = new Point(x-focusRectLength/2,y-focusRectLength/2);
        Point point1 = new Point(x+focusRectLength/2,y-focusRectLength/2);
        Point point2 = new Point(x+focusRectLength/2,y+focusRectLength/2);
        Point point3 = new Point(x-focusRectLength/2,y+focusRectLength/2);

        //draw point 0 lines
        Point pointStart = new Point(point0.x,point0.y+SIDE_LENGTH);
        Point pointEnd = new Point(point0.x+SIDE_LENGTH,point0.y);
        Path mPath = new Path();
        mPath.moveTo(pointStart.x, pointStart.y);
        mPath.lineTo(point0.x,point0.y);
        mPath.lineTo(pointEnd.x,pointEnd.y);


        //draw point 1 lines
        Point pointStart1 = new Point(point1.x-SIDE_LENGTH,point1.y);
        Point pointEnd1 = new Point(point1.x,point1.y+SIDE_LENGTH);

        mPath.moveTo(pointStart1.x, pointStart1.y);
        mPath.lineTo(point1.x,point1.y);
        mPath.lineTo(pointEnd1.x,pointEnd1.y);


        //draw point 2 lines
        Point pointStart2 = new Point(point2.x-SIDE_LENGTH,point2.y);
        Point pointEnd2 = new Point(point2.x,point2.y-SIDE_LENGTH);

        mPath.moveTo(pointStart2.x, pointStart2.y);
        mPath.lineTo(point2.x,point2.y);
        mPath.lineTo(pointEnd2.x,pointEnd2.y);



        //draw point 3 lines
        Point pointStart3 = new Point(point3.x,point3.y-SIDE_LENGTH);
        Point pointEnd3 = new Point(point3.x+SIDE_LENGTH,point3.y);

        mPath.moveTo(pointStart3.x, pointStart3.y);
        mPath.lineTo(point3.x,point3.y);
        mPath.lineTo(pointEnd3.x,pointEnd3.y);
        canvas.drawPath(mPath,mPaint1);


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
