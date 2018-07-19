package com.example.gaozhelong.replugintest.util;

import java.io.File;

import okhttp3.OkHttpClient;

public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    public void download(final String url,final String destFileDir,final String destFileName,final OnDownloadListener listener) {

    }

    public interface OnDownloadListener {
        void onDownloadSucces(File file);

        void onDownloading(int progress);

        void onDownloadFailed(Exception e);
    }
}
