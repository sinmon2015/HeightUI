package com.example.administrator.heightui.svgchinamapanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathMeasureView  extends View {
    private Path path,dst;
    private Paint mPaint;
    private float length;
    private PathMeasure pathMeasure;
    private float mAnimatorValue;
    private float[] pos = new float[2];
    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //Path路径点的坐标追踪，这个类就是PathMeasure
        pathMeasure = new PathMeasure();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        path = new Path();
        dst = new Path();
        //添加圆形路径，圆心100,400,半径100,方法顺时针
        path.addCircle(100, 400, 100, Path.Direction.CW);

        //给pathMeasure设置一个path路径，true代表将路径闭合
        pathMeasure.setPath(path, false);

        length = pathMeasure.getLength();//获取长度
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }
//不断重绘
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dst.reset();
//        dst.lineTo(0, 0);
//            stop  不断计算长度
        float stop = length * mAnimatorValue;
        //截取0到stop的位置的路径给新的dst路径path,在不断改变stop的值和重绘，就能让它动起来
        pathMeasure.getSegment(0, stop, dst, true);//true代表从开始到结束
        canvas.drawPath(dst, mPaint);//绘制新的path路径

        //可以得到当前stop的坐标值，然后将坐标值传给pos数组，第三个参数为角度(tan角，切值)
        pathMeasure.getPosTan(stop, pos, null);
//        在每个终点画个小圆圈
        canvas.drawCircle(pos[0],pos[1],25,mPaint);
    }
}
