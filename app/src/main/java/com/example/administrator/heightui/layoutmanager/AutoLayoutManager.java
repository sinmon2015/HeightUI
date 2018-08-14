package com.example.administrator.heightui.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

public class AutoLayoutManager extends RecyclerView.LayoutManager {
    //保存所有item偏移量信息
    //    所有数据高度和
    private int totalHeight = 0;
    //每个ITEM的的信息(宽、高、位置)
    private SparseArray<Rect> allItemframs = new SparseArray<>();

    /**
     * 滑动偏移量
     * 如果是正的就是在向上滑，展现上面的view
     * 如果是负的向下
     */
    private int verticalScrollOffset = 0;

    /**
     * 测量
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 摆放子控件
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        摆放
        if (getItemCount() <= 0) {
            return;
        }
        //preLayout主要支持动画，正在动画、准备动画时都直接跳过
        if (state.isPreLayout()) {
            return;
        }
        //将视图分离放入scrap缓存中，以准备重新对view进行排版，可以理解为清空
        detachAndScrapAttachedViews(recycler);

        int offsetY = 0;
        int offsetX = 0;
        int viewH = 0;
        for (int i=0;i<getItemCount();i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);//要先添加到父控件中，才能进行测量
            measureChildWithMargins(view, 0, 0);//让系统去测量

            int w=getDecoratedMeasuredWidth(view);//宽度

            int h=getDecoratedMeasuredHeight(view);//高度
            viewH = h;
            Rect fram = allItemframs.get(i);
            if (fram == null){
                fram = new Rect();
            }
//            需要换行
            if (offsetX + w > getWidth()) {
//                换行的View的值
                offsetY += h;
                offsetX=w;
                fram.set(0, offsetY, w, offsetY + h);
            }else {
//                不需要换行
                fram.set(offsetX, offsetY, offsetX + w, offsetY + h);
                offsetX += w;
            }
//            要 针对于当前View   生成对应的Rect  然后放到allItemframs数组
            allItemframs.put(i, fram);
        }
        totalHeight=offsetY + viewH;//数据的总高度(因为换行之后,还要把最后一行的高度加上)


//        detach   轻量级的移除操作    remove  重量级
//        回收不可见的数据
        recyclerViewFillView(recycler, state);
    }

    private void recyclerViewFillView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //        清空RecyclerView的子View
        detachAndScrapAttachedViews(recycler);
        Rect phoneFrame = new Rect(0, verticalScrollOffset, getWidth(), verticalScrollOffset + getHeight());
        //将滑出屏幕的view进行回收
        Rect childRect = new Rect();
        for (int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            Rect child=allItemframs.get(i);
            if (!Rect.intersects(phoneFrame, child)) {
                removeAndRecycleView(childView, recycler);
            }
        }

        //可见区域出现在屏幕上的子view
        for (int j = 0;j<getItemCount();j++){
            if (Rect.intersects(phoneFrame,allItemframs.get(j))){
//                scrap回收池里面拿的
                View scrap = recycler.getViewForPosition(j);
                measureChildWithMargins(scrap,0,0);
                addView(scrap);
                Rect frame = allItemframs.get(j);
                //摆放,recycleview独有的方法
                layoutDecorated(scrap, frame.left, frame.top - verticalScrollOffset,
                        frame.right, frame.bottom - verticalScrollOffset);
            }

        }



    }

    /**
     * 水平滑动
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    /**
     * 垂直滑动
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
            detachAndScrapAttachedViews(recycler);
        //实际滑动距离  dx
        int trval = dy;
        System.out.print("dy====="+dy);
        System.out.print("verticalScrollOffset====="+verticalScrollOffset);
        Log.d("aaa","dy====="+dy);
        Log.d("aaa","verticalScrollOffset====="+verticalScrollOffset);
//        如果滑动到最顶部  往下滑   verticalScrollOffset   -
//        第一个坐标值 减 以前最后一个坐标值  //记死
        if (verticalScrollOffset + dy < 0) {
            trval = -verticalScrollOffset;
        }else if(verticalScrollOffset+dy>totalHeight-getHeight()){
//            如果滑动到最底部  往上滑   verticalScrollOffset   +
            trval = totalHeight - getHeight() - verticalScrollOffset;
        }

//        边界值判断
        verticalScrollOffset += trval;

        //平移容器内的item
        offsetChildrenVertical(trval);
        recyclerViewFillView(recycler, state);
        return trval;
    }

    /**
     * 开启滚动
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }


}
