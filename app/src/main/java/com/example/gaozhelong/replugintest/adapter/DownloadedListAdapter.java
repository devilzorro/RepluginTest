package com.example.gaozhelong.replugintest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gaozhelong.replugintest.R;

import java.util.List;

public class DownloadedListAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    @NonNull
    private List<String> fileNames;
    private onItemDoneListener mOnItemDoneListener;

    @Override
    public void onClick(View view) {
        int viewPos = (int)view.getTag();
        if (mOnItemDoneListener != null) {
            mOnItemDoneListener.onItemDone(view,"install",viewPos);
        }
    }

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
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        ViewHolder itemView = (ViewHolder)holder;
        itemView.fileName.setText(fileNames.get(position));

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemDoneListener != null) {
                    mOnItemDoneListener.onItemDone(view,"delete",position);
                }
            }
        };

        itemView.deleteBtn.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }

    public interface onItemDoneListener {
        void onItemDone(View v,String val,int position);
    }

    public void setOnitemDoneListener(onItemDoneListener onitemDoneListener) {
        this.mOnItemDoneListener = onitemDoneListener;
    }
}
