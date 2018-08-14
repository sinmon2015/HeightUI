package com.example.administrator.heightui.RecyclerViewAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import com.example.administrator.heightui.R;

import java.util.List;

public class RecyclerAnimation extends Activity implements StartDragListener{

	private RecyclerView recyclerView;
	private ItemTouchHelper itemTouchHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyclerhdh);
		
		recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		
		List<QQMessage> list = DataUtils.init();
		QQAdapter adapter = new QQAdapter(list,this);
		recyclerView.setAdapter(adapter);
		//条目触摸帮助类
		Callback callback = new MyItemTouchHelperCallback(adapter);
		itemTouchHelper = new ItemTouchHelper(callback);
		itemTouchHelper.attachToRecyclerView(recyclerView);
		
	}

	@Override
	public void onStartDrag(ViewHolder viewHolder) {
		itemTouchHelper.startDrag(viewHolder);
	}

}
