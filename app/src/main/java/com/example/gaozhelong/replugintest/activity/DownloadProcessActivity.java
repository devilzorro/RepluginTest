package com.example.gaozhelong.replugintest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gaozhelong.replugintest.R;

import okhttp3.OkHttpClient;

public class DownloadProcessActivity extends AppCompatActivity {

    TextView tv_downloadName;
    ProgressBar progressBar;

//    OkHttpClient okHttpClient;

    String fileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_process);

        tv_downloadName = findViewById(R.id.tv_downloadname);
        progressBar = findViewById(R.id.downlaodprocess);

        fileUrl = getIntent().getExtras().getString("url");
    }
}
