package com.example.administrator.heightui.douying;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyLayoutManager  extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private int mDrift;//位移，用来判断移动方向

    private PagerSnapHelper mPagerSnapHelper;//专门针对滑动的类
    private OnViewPagerListener mOnViewPagerListener;
    public MyLayoutManager(Context context) {
        super(context);
    }

    public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        //只有当recyclerview真正被添加到window里面时才可添加此监听，否则不是无效的
        view.addOnChildAttachStateChangeListener(this);//监听每个ITEM移入与移出
        mPagerSnapHelper.attachToRecyclerView(view);//绑定该RecyclerView,此时RecyclerView就具备类似viewpage滑动的效果
        super.onAttachedToWindow(view);
    }
//当Item添加进来了  调用这个方法

//
    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
//        播放视频操作 即，将要播放的是上一个视频 还是下一个视频
        if (mDrift > 0) {
//            向上
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(view), true);
            }

        }else {
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(view), false);
            }
        }
    }
    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
//暂停播放操作
        if (mDrift >= 0){
            if (mOnViewPagerListener != null) mOnViewPagerListener.onPageRelease(true,getPosition(view));
        }else {
            if (mOnViewPagerListener != null) mOnViewPagerListener.onPageRelease(false,getPosition(view));
        }
    }
    public void setOnViewPagerListener(OnViewPagerListener mOnViewPagerListener) {
        this.mOnViewPagerListener = mOnViewPagerListener;
    }

    /**
     * 滑动的状态改变
     * @param state
     */

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE://完成状态
//                RecyclerView.SCROLL_STATE_DRAGGING://拖动状态
//                RecyclerView.SCROLL_STATE_SETTLING://惯性滑动状态
                //获取当前进来的view
               View view= mPagerSnapHelper.findSnapView(this);
                int position = getPosition(view);
                if (mOnViewPagerListener != null)
                {
                    //第2个参数是判断是不是下一个ITEM,即从下面上来的VIEW,只适用于本例
                    mOnViewPagerListener.onPageSelected(position, position == getItemCount() - 1);
                    mOnViewPagerListener.onPageRelease(position == getItemCount() - 1, position);

                }
//                postion  ---回调 ----》播放


                break;
        }
        super.onScrollStateChanged(state);
    }




    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
