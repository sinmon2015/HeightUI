package com.example.administrator.heightui.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.heightui.R;

public class TestView extends View {

    private static final String TAG = "BARRY";

    private Paint mPaint = null;
    private Bitmap mBitmap = null;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lsj);
        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //默认有一个绘图坐标系

        RectF r = new RectF(0,0,400,400);
        canvas.drawRect(r,mPaint);
        canvas.save();//0,0
        //translate  他对坐标系进行了改变
        canvas.translate(50,50);

        canvas.save();//50,50
        mPaint.setColor(Color.BLUE);
        RectF r2 = new RectF(0,0,400,400);
        canvas.drawRect(r2,mPaint);



        canvas.restore();//?  0,0    50,50
        canvas.translate(70,50);

        //此处再次进行绘制运用了-50那么这里不是将坐标系改变
        mPaint.setColor(Color.YELLOW);
        RectF r3 = new RectF(0,0,400,400);
        canvas.drawRect(r3,mPaint);

    }
}
