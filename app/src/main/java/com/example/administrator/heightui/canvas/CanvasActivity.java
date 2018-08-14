package com.example.administrator.heightui.canvas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.heightui.R;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView3(this));

        setContentView(R.layout.activity_canvas);
        initData();
        initView();
    }


    private ImageView iv;
    private int[] mImgIds = new int[] { //7个
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;




    private void initData(){
        revealDrawables = new Drawable[mImgIds.length];
    }

    private void initView()
    {
        for (int i = 0; i < mImgIds.length; i++)
        {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]),
                    RevealDrawable.HORIZONTAL);
            revealDrawables[i] = rd;
        }
        hzv = (GallaryHorizonalScrollView)findViewById(R.id.hsv);
        hzv.addImageViews(revealDrawables);
    }
}
