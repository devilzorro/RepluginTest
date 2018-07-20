package com.example.gaozhelong.replugintest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gaozhelong.replugintest.R;

import java.util.List;

public class DownloadedListAdapter extends RecyclerView.Adapter {
    @NonNull
    private List<String> fileNames;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fileName;
        private TextView deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.tv_filename);
            deleteBtn = itemView.findViewById(R.id.btn_delete);
        }
    }

    public DownloadedListAdapter(@NonNull List<String> fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_downloadedlist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemView = (ViewHolder)holder;
        itemView.fileName.setText(fileNames.get(position));
    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }
}
