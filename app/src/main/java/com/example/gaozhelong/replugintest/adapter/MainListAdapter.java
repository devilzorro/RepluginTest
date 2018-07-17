package com.example.gaozhelong.replugintest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaozhelong.replugintest.R;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter {
    @NonNull
    private List<String> names;
    private OnItemDoneListener mOnItemDoneListener;

    public MainListAdapter(@NonNull List<String> names) {
        this.names = names;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;

        public ViewHolder(View view) {
            super(view);
            appIcon = view.findViewById(R.id.item_img);
            appName = view.findViewById(R.id.item_name);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainlist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder)holder;
        if (position == names.size()) {
            itemHolder.appName.setText("           +     ");
            itemHolder.appName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemDoneListener.onItemDone(view,"addDownload");
                }
            });
        } else {
            itemHolder.appIcon.setImageResource(R.mipmap.test_icon);
            itemHolder.appName.setText(names.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return names.size() + 1;
    }

    public interface OnItemDoneListener {
        void onItemDone(View view,String val);
    }

    public void setOnItemDoneListener(OnItemDoneListener onItemDoneListener) {
        this.mOnItemDoneListener = onItemDoneListener;
    }
}
