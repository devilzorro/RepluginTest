package com.example.gaozhelong.replugintest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gaozhelong.replugintest.adapter.MainListAdapter;
import com.example.gaozhelong.replugintest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    RecyclerView rc_MainList;
//    MainListAdapter adapter;

    Button nsdBtn;
    Button addBtn;
    Button appBtn;
    Button uninstallBtn;

    List<String> appDatas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rc_MainList = findViewById(R.id.rc_mainlist);
        nsdBtn = findViewById(R.id.nsd_btn);
        addBtn = findViewById(R.id.add_btn);
        appBtn = findViewById(R.id.app_btn);
        uninstallBtn = findViewById(R.id.app_uninstall_btn);

//        initRcView();
        initBtns();
    }

    private void initBtns() {
        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.nsd_btn:
                        String tmpPath = getApplicationContext().getFilesDir().getAbsolutePath() + "/app-release.apk";
                        File file = new File(tmpPath);
                        if (file.exists()) {
//                            Toast.makeText(MainActivity.this,"file exist!",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this,"安装文件不存在，请先下载！",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.add_btn:
                        Intent intent = new Intent(MainActivity.this,Test1Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.app_btn:
                        break;
                    case R.id.app_uninstall_btn:
                        break;
                    default:
                        break;
                }
            }
        };
        nsdBtn.setOnClickListener(btnClick);
        addBtn.setOnClickListener(btnClick);
        appBtn.setOnClickListener(btnClick);
        uninstallBtn.setOnClickListener(btnClick);
    }

    @Override
    public void onClick(View view) {

    }

//    void initRcView() {
//        rc_MainList.setLayoutManager(new GridLayoutManager(this,4));
//        rc_MainList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        appDatas = new ArrayList<>();
//        for (int i=0;i<20;i++) {
//            appDatas.add("appName:" + i);
//        }
//        adapter = new MainListAdapter(appDatas);
//        adapter.setOnItemDoneListener(new MainListAdapter.OnItemDoneListener() {
//            @Override
//            public void onItemDone(View view, String val) {
//                if ("addDownload".equals(val)) {
//                    Intent intent = new Intent(MainActivity.this,Test1Activity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//        rc_MainList.setAdapter(adapter);
//    }



}
