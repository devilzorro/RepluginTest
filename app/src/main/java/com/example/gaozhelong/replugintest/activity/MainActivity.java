package com.example.gaozhelong.replugintest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gaozhelong.replugintest.adapter.MainListAdapter;
import com.example.gaozhelong.replugintest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc_MainList;
    MainListAdapter adapter;
    List<String> appDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc_MainList = findViewById(R.id.rc_mainlist);
        initRcView();
    }

    void initRcView() {
        rc_MainList.setLayoutManager(new GridLayoutManager(this,4));
        rc_MainList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        appDatas = new ArrayList<>();
        for (int i=0;i<20;i++) {
            appDatas.add("appName:" + i);
        }
        adapter = new MainListAdapter(appDatas);
        adapter.setOnItemDoneListener(new MainListAdapter.OnItemDoneListener() {
            @Override
            public void onItemDone(View view, String val) {
                if ("addDownload".equals(val)) {
                    Intent intent = new Intent(MainActivity.this,Test1Activity.class);
                    startActivity(intent);
                }
            }
        });
        rc_MainList.setAdapter(adapter);
    }



}
