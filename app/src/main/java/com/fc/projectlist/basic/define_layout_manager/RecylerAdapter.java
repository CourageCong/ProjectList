package com.fc.projectlist.basic.define_layout_manager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fc.projectlist.R;

/**
 * Created by zuoyebang on 2019/8/7.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyer_item_1,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyer_txt_1);
        }
    }
}
