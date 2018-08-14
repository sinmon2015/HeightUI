package com.example.administrator.heightui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.heightui.P1FlowLayout.FlowLayoutActivity;
import com.example.administrator.heightui.RecyclerViewAnimation.RecyclerAnimation;
import com.example.administrator.heightui.WrapRecyclerView.MyRecyClerView;
import com.example.administrator.heightui.bezier.BezierActivity;
import com.example.administrator.heightui.canvas.CanvasActivity;
import com.example.administrator.heightui.douying.DouYingActivity;
import com.example.administrator.heightui.drawerLayout.DrawerLayoutActivity;
import com.example.administrator.heightui.layoutmanager.LayoutManagerActivity;
import com.example.administrator.heightui.pain.PainActivity;
import com.example.administrator.heightui.svgchinamap.SvgActivity;
import com.example.administrator.heightui.svgchinamapanimation.ChinaMapAnimationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_flow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FlowLayoutActivity.class));
            }
        });
        findViewById(R.id.btn_pain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PainActivity.class));
            }
        });
        findViewById(R.id.btn_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
            }
        });
        findViewById(R.id.btn_bezier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BezierActivity.class));
            }
        });
        findViewById(R.id.btn_recycler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyRecyClerView.class));
            }
        });
        findViewById(R.id.btn_recycleranima).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerAnimation.class));
            }
        });
        findViewById(R.id.btn_drawerlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
            }
        });
        findViewById(R.id.btn_svg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SvgActivity.class));
            }
        });
        findViewById(R.id.btn_layoutmanager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LayoutManagerActivity.class));
            }
        });
        findViewById(R.id.btn_DouYingActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DouYingActivity.class));
            }
        });
        findViewById(R.id.btn_svganimation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChinaMapAnimationActivity.class));
            }
        });


    }

}
