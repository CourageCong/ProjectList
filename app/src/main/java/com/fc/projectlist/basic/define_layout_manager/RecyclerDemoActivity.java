package com.fc.projectlist.basic.define_layout_manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fc.projectlist.R;

/**
 * Created by zuoyebang on 2019/8/7.
 */

public class RecyclerDemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecylerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_demo_layout);
        recyclerView = findViewById(R.id.recycler_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecylerAdapter();
        recyclerView.setAdapter(adapter);
    }
}
