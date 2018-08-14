package com.example.administrator.heightui.WrapRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.administrator.heightui.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyClerView extends Activity {

	private WrapRecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecyclerview);
		recyclerView = findViewById(R.id.recyclerView);
		
		
//		View headerView = View.inflate(this, resource, root);
		TextView headerView = new TextView(this);
		//		TextView tv = headerView.findViewById(id);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, 200);
		headerView.setLayoutParams(params);
		headerView.setText("HeaderView");
		recyclerView.addHeaderView(headerView);
		
		TextView footerView = new TextView(this);
		params = new LayoutParams(LayoutParams.FILL_PARENT, 200);
		footerView.setLayoutParams(params);
		footerView.setText("FooterView");
		recyclerView.addFooterView(footerView);
		
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			list.add("item "+i); 
		}
		
		MyAdapter adapter = new MyAdapter(list);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		
	}

}
