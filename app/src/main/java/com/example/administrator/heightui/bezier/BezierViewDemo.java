package com.example.administrator.heightui.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
/**
 * 多介贝塞尔曲线
 */
public class BezierViewDemo extends View {


    //曲线开始点
    private float mStartX, mStartY;
    //结束点
    private float mEndX, mEndY;
    //控制点
    private float mContorlX = 200, mContorlY = 60;//默认值
    private Paint mPaint,mLinePointPaint;
    private Path mPath;

    //多控制点
    private ArrayList<PointF> mControlPoints = null;    // 控制点集



    public BezierViewDemo(Context context) {
        this(context,null);
    }

    public BezierViewDemo(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);



        mLinePointPaint = new Paint();
        mLinePointPaint.setAntiAlias(true);
        mLinePointPaint.setStrokeWidth(4);
        mLinePointPaint.setStyle(Paint.Style.STROKE);
        mLinePointPaint.setColor(Color.RED);


        mStartX = 60;
        mStartY = 350;
        mEndX = 450;
        mEndY = 350;
        mPath = new Path();
        mControlPoints = new ArrayList<>();
        init();
    }



    private void init(){
        Random random = new Random();
        for (int i = 0;i < 5;i++){
            int x = random.nextInt(600)+100;
            int y = random.nextInt(600)+100;
            Log.i("barry","x:"+x+"--y:"+y);
            PointF pointF = new PointF(x,y);
            mControlPoints.add(pointF);
        }
    }

    public BezierViewDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }








    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




//        // 控制点和控制点连线
        int size = mControlPoints.size();
        PointF point;
        for (int i = 0; i < size; i++) {
            point = mControlPoints.get(i);
            if (i > 0) {
                // 控制点连线
                canvas.drawLine(mControlPoints.get(i - 1).x, mControlPoints.get(i - 1).y, point.x, point.y,
                        mLinePointPaint);
            }
            // 控制点
            canvas.drawCircle(point.x, point.y, 12, mLinePointPaint);



        }
//
//
//
//
        buildBezierPoints();
        canvas.drawPath(mPath,mPaint);
//        mPath.reset();
//        mPaint.setColor(Color.BLACK);
//        mPath.moveTo(mStartX,mStartY);
//        //二介曲线绘制方法
//        mPath.quadTo(mContorlX,mContorlY,mEndX,mEndY);
//        canvas.drawPath(mPath,mPaint);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(mContorlX,mContorlY,mPaint);



    }








    /**
     * deCasteljau算法
     *  p(i,j) =  (1-t) * p(i-1,j)  +  u * p(i-1,j-1);
     * @param i 阶数   4
     * @param j 控制点 3
     * @param t 时间,比例
     * @return
     */
    private float deCasteljauX(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).x + t * mControlPoints.get(j + 1).x;
        }
        return (1 - t) * deCasteljauX(i - 1, j, t) + t * deCasteljauX(i - 1, j + 1, t);
    }

    /**
     * deCasteljau算法
     *
     * @param i 阶数
     * @param j 点
     * @param t 时间
     * @return
     */
    private float deCasteljauY(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).y + t * mControlPoints.get(j + 1).y;
        }
        return (1 - t) * deCasteljauY(i - 1, j, t) + t * deCasteljauY(i - 1, j + 1, t);
    }


    private ArrayList<PointF> buildBezierPoints() {
        mPath.reset();

        ArrayList<PointF> points = new ArrayList<>();
        int order = mControlPoints.size() - 1;
        //画的密集度，帧
        float delta = 1.0f / 10;
        for (float t = 0; t <= 1; t += delta) {
            // Bezier点集
            PointF pointF = new PointF(deCasteljauX(order, 0, t), deCasteljauY(order, 0, t));
            points.add(pointF);
            if(points.size() == 1){
                mPath.moveTo(points.get(0).x,points.get(0).y);
            }else{
                mPath.lineTo(pointF.x,pointF.y);
            }

        }
        return points;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            mContorlX = event.getX();
            mContorlY = event.getY();
            invalidate();
        }
        return true;
    }
}
