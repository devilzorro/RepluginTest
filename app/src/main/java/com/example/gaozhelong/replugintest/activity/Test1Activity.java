package com.example.gaozhelong.replugintest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaozhelong.replugintest.R;
import com.example.gaozhelong.replugintest.adapter.DownloadedListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test1Activity extends AppCompatActivity {

    Button btnDownload;
    EditText textUrl;
    TextView textStatus;
    RecyclerView downloadedList;
    DownloadedListAdapter adapter;

    Looper looper = Looper.getMainLooper();
    Handler handler;
    List<String> fileNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        btnDownload = findViewById(R.id.btn_download);
        textUrl = findViewById(R.id.edit_url);
        textStatus = findViewById(R.id.text_status);
        downloadedList = findViewById(R.id.rc_downloaded_list);
//        handler = new Handler(looper) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what) {
//                    case 1:
//
//                        break;
//                }
//            }
//        };
        initBtn();
        initRcList();
    }

    private void initBtn() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!"".equals(textUrl.getText().toString())) {
//                            Download load = new Download(textUrl.getText().toString());
//                            String value = load.downloadAsString();
//                            Message msg = handler.obtainMessage();
//
//                        }
//
//                    }
//                }).start();
//                Toast.makeText(Test1Activity.this,textUrl.getText().toString(),Toast.LENGTH_LONG);
                if (!"".equals(textUrl.getText().toString())) {
                    Intent intent = new Intent(Test1Activity.this,DownloadProcessActivity.class);
                    intent.putExtra("url",textUrl.getText().toString());
//                    startActivity(intent);
                    startActivityForResult(intent,2);
                } else {
                    Toast.makeText(Test1Activity.this,"请输入下载url地址!",Toast.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Test1Activity", "onActivityResult: requestCode" + requestCode + " | resultCode " + resultCode);
        if (resultCode == 1) {
            fileNames.clear();
//            List<String> tmpNames = new ArrayList<String>();
//            tmpNames = getFileNames(getApplicationContext().getFilesDir().getAbsolutePath());
            fileNames.addAll(getFileNames(getApplicationContext().getFilesDir().getAbsolutePath()));
            Log.d("Test1Activity", "onActivityResult: downloaded list: " + fileNames);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRcList() {
        fileNames = new ArrayList<>();
        fileNames = getFileNames(getApplicationContext().getFilesDir().getAbsolutePath());
//        Log.d("Test1Activity", "initRcList: fileDir : "+getApplicationContext().getFilesDir().getAbsolutePath());
//        Log.d("Test1Activity", "initRcList: fileList : " + fileNames);

        downloadedList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DownloadedListAdapter(fileNames);
        adapter.setOnitemDoneListener(new DownloadedListAdapter.onItemDoneListener() {
            @Override
            public void onItemDone(View v, String val, int position) {
                if ("delete".equals(val)) {
                    String filePath = getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileNames.get(position);
                    if (deleteDownloadedFile(filePath)) {
                        fileNames.clear();
//                        fileNames = getFileNames(getApplicationContext().getFilesDir().getAbsolutePath());
                        fileNames.addAll(getFileNames(getApplicationContext().getFilesDir().getAbsolutePath()));
                        Log.d("Test1Activity", "onItemDone: after delete fileNames list: " + fileNames);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(),"删除失败！",Toast.LENGTH_LONG).show();
                    }
                } else if ("install".equals(val)) {
                    Toast.makeText(getApplicationContext(),"prepare install!",Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });
        downloadedList.setAdapter(adapter);
    }

    public List<String> getFileNames(String fileAbsolutePath) {
        List<String> files = new ArrayList<>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();
        if (subFile != null) {
            for (int iFileLength=0;iFileLength<subFile.length;iFileLength++) {
                if (!subFile[iFileLength].isDirectory()) {
                    String tmpName = subFile[iFileLength].getName();
                    files.add(tmpName);
                }
            }
        }
        return files;
    }

//    private void refreshRcData() {
//        fileNames = getFileNames(getApplicationContext().getFilesDir().getAbsolutePath());
//    }

    boolean deleteDownloadedFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
