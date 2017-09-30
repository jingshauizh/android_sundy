package com.ericsson.NewPlayer.HomePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 8/10/2017.
 */

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.RecyclerHomeViewHolder> {
    private Context context;
    private List<VideoData.VideosBean> mDatas;
    public List<VideoData.VideosBean> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<VideoData.VideosBean> mDatas) {
        this.mDatas = mDatas;
    }
    public RecyclerHomeAdapter(Context context, List<VideoData.VideosBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.fragment_home_ui_item, null);
        //View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_home_ui_item,parent,false);
        RecyclerHomeViewHolder mRecyclerTestViewHolder = new RecyclerHomeViewHolder(itemView, listener);
        return mRecyclerTestViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerHomeViewHolder holder, final int position) {
        holder.tvTitle.setText(mDatas.get(position).getTitle());
        holder.tvSubTitle.setText(mDatas.get(position).getSubtitle());
        Glide.with(context)
                //.load(mDatas.get(position).getVideoImageUrl())
                .load(R.drawable.video_image)
                .centerCrop()    //先填充image 再剪切多余的
                .into(holder.mImageView);
        //holder.mImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.handou));
    }


    @Override
    public int getItemCount() {
        if(mDatas!= null){
            return mDatas.size();
        }
        return 0;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }


    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class RecyclerHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        TextView tvTitle;
        TextView tvSubTitle;
        ImageView mImageView;
        private OnItemClickListener mlistener;


        public RecyclerHomeViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imgv_homeUI_rv_item);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_homeUI_rv_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_homeUI_rv_subTitle);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mlistener = listener;
        }

        @Override
        public void onClick(View v) {
            if (mlistener != null) {
                mlistener.onItemClick(v, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mlistener != null) {
                mlistener.onItemLongClick(v, getLayoutPosition());
            }
            return true;
        }


    }


}
