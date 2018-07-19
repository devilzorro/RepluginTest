package com.example.gaozhelong.replugintest.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaozhelong.replugintest.R;
import com.example.gaozhelong.replugintest.util.Download;
import com.example.gaozhelong.replugintest.widget.HorizontalProcessBar;

public class ProcessActivity extends AppCompatActivity {

    TextView processText;
    ProgressBar processBar;
    TextView tv_fileSize;

    private float fileSize;

//    public ProcessActivity(@NonNull Context context) {
//        super(context);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        processText = findViewById(R.id.process_text);
        processBar = findViewById(R.id.process_progress);
        tv_fileSize = findViewById(R.id.tv_filesize);

        final String url = getIntent().getExtras().getString("url");

//        Toast.makeText(getApplicationContext(),"processActivity:"+url,Toast.LENGTH_LONG).show();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int pro = processBar.getProgress() + msg.arg1;
//                Toast.makeText(getApplicationContext(),"file Szie: " + fileSize,Toast.LENGTH_LONG).show();
//                if (fileSize != 0) {
//                   pro = (msg.arg1/fileSize)*100;
//                   processBar.setCurrentProgress(pro);
                    processBar.setProgress(pro);
                   processText.setText("download process:" + Float.toString(pro));

                   if (pro >= processBar.getMax()) {
                       finish();
                   }
//                }


            }
        };

//        processBar.setProgressListener(new HorizontalProcessBar.ProgressListener() {
//            @Override
//            public void currentProgressListener(float currentProgress) {
//
//            }
//        });
//
//        processBar.setProgressWithAnimation(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Download l = new Download(url);
//                fileSize = l.getFileLength();
                processBar.setMax(l.getFileLength());
                tv_fileSize.setText("total file size:" + l.getFileLength());
//                processBar.startProgressAnimation();
                int status = l.down2sd("downtemp/", "nsdTest.apk", l.new downhandler() {
                    @Override
                    public void setSize(int size) {
                        Message msg = handler.obtainMessage();
                        msg.arg1 = size;
                        msg.sendToTarget();
                        Log.d("log", "size:"+Integer.toString(size));
                    }
                });
                Log.d("log", "status : " + Integer.toString(status));
            }
        }).start();
    }

}
