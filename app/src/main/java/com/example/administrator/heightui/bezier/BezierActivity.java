package com.example.administrator.heightui.bezier;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2018/7/2.
 */

public class BezierActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bezier);
        setContentView(new BezierViewDemo(this));
    }
}
