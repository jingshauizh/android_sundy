package com.ericsson.NewPlayer.Play;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ericsson.NewPlayer.R;

import java.util.List;

/**
 * Created by eqruvvz on 8/10/2017.
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.RecyclerHomeViewHolder> {
    private Context context;
    private List<String> mDatas;

    public CommentRecyclerAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.fragment_play_comment_item, null);
        //View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_play_comment_item,parent,false);
        RecyclerHomeViewHolder mRecyclerTestViewHolder = new RecyclerHomeViewHolder(itemView, listener);
        return mRecyclerTestViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerHomeViewHolder holder, final int position) {
        holder.commentUserName.setText(mDatas.get(position));
        holder.commentUseContent.setText(mDatas.get(position));
        Glide.with(context)
                .load(R.drawable.user)
                .centerCrop()    //先填充image 再剪切多余的
                .into(holder.commentUserIcon);
        //holder.commentUserIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.handou));
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
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


        TextView commentUserName;
        TextView commentUseContent;
        ImageView commentUserIcon;
        private OnItemClickListener mlistener;


        public RecyclerHomeViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            commentUserIcon = (ImageView) itemView.findViewById(R.id.comment_user_icon);
            commentUserName = (TextView) itemView.findViewById(R.id.comment_user_name);
            commentUseContent = (TextView) itemView.findViewById(R.id.comment_user_content);
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
