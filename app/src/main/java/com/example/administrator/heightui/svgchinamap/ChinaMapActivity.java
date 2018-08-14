package com.example.administrator.heightui.svgchinamap;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.heightui.R;

public class ChinaMapActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinamap);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AnimatedVectorDrawable drawable= (AnimatedVectorDrawable) imageView.getDrawable();
               drawable.start();

            }
        });
    }
}
