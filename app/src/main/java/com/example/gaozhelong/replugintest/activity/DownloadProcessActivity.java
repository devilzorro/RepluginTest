package com.example.gaozhelong.replugintest.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaozhelong.replugintest.R;
import com.example.gaozhelong.replugintest.util.DownloadUtil;

import java.io.File;

import okhttp3.OkHttpClient;

public class DownloadProcessActivity extends AppCompatActivity {

    TextView tv_downloadName;
    ProgressBar progressBar;

//    OkHttpClient okHttpClient;

    String fileUrl;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_process);

        tv_downloadName = findViewById(R.id.tv_downloadname);
        progressBar = findViewById(R.id.downlaodprocess);

        fileUrl = getIntent().getExtras().getString("url");
        fileName = getFileName(fileUrl);
//        tv_downloadName.setText(fileName);
        downFile(fileUrl);
    }

    public void downFile(String url) {
        DownloadUtil.get().download(url, Environment.getExternalStorageDirectory().getAbsolutePath(), fileName, new DownloadUtil.OnDownloadListener() {


            @Override
            public void onDownloadSucces(File file) {
                finish();
            }

            @Override
            public void onDownloading(int progress) {
                Log.d("DownloadProcessActivity", "onDownloading: process:" + progress);
                progressBar.setProgress(progress);
//                tv_downloadName.setText("当前文件下载进度：" + progress + " %");
            }

            @Override
            public void onDownloadFailed(Exception e) {
//                Toast.makeText(getApplicationContext(),"下载异常！",Toast.LENGTH_LONG).show();
                Log.d("DownloadProcessActivity", "onDownloadFailed: " + e);
            }
        });
    }

    public String getFileName(String strUrl) {
        String strRet = "";
        String[] array = strUrl.split("/");
        strRet = array[array.length - 1];
        return strRet;
    }
}
