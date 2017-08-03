package com.jingshuai.android.fregmentapp.view.recircleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingshuai.android.fregmentapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/15 0015.
 */

public class  RecyclerTestAdapter extends RecyclerView.Adapter<RecyclerTestAdapter.RecyclerTestViewHolder> {
    private Context context;
    private List<String> mDatas;

    public RecyclerTestAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public RecyclerTestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false);
        RecyclerTestViewHolder mRecyclerTestViewHolder = new RecyclerTestViewHolder(itemView);
        return mRecyclerTestViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerTestViewHolder holder, final int position) {
//        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
//        lp.height =20;
//        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(v,position);
                }
            }
        });

        holder.tv.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(listener != null){
                    listener.onItemLongClick(v,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }




    public static class RecyclerTestViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public RecyclerTestViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }



    private OnItemClickListener listener;
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
