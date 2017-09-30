package com.ericsson.NewPlayer.Play;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericsson.NewPlayer.HomePage.base.HeaderViewPagerFragment;
import com.ericsson.NewPlayer.R;

import java.util.ArrayList;
import java.util.List;

public class CommentRecyclerFragment extends HeaderViewPagerFragment {

    private List<String> mDatas = new ArrayList<>();
    private Activity activity;
    private CommentRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;


    public static CommentRecyclerFragment newInstance() {
        return new CommentRecyclerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View freg_view = inflater.inflate(R.layout.fragment_play_comment, container, false);
        mRecyclerView = (RecyclerView) freg_view.findViewById(R.id.play_comment_view);
        activity = getActivity();
        initDatas();
        adapter = new CommentRecyclerAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        /*adapter.setListener(new CommentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(activity,"第"+position+"项被点击了",Toast.LENGTH_SHORT).show();
                String playerUrl = "https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4";

                VideoData.VideosBean videosBean = new VideoData.VideosBean();
                videosBean.setVideoUrl(playerUrl);
                videosBean.setLike(position == 0);
                videosBean.setTitle("这是第几个标题：" + position);
                videosBean.setSubtitle("这是第几个副标题：" + position);
                videosBean.setInfo("这是第几个影片的详细内容解释：" + position);
                Intent playerIntent = new Intent(getActivity().getApplicationContext(), PlayerActivity.class);
                playerIntent.putExtra("videoBean", videosBean);
                startActivity(playerIntent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(activity, "第" + position + "项被long点击了", Toast.LENGTH_SHORT).show();
            }
        });*/
        return freg_view;
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }


    private void initDatas() {
        for (int i = 1; i < 16; i++) {
            mDatas.add("用户评论" + i);
        }
    }
}
