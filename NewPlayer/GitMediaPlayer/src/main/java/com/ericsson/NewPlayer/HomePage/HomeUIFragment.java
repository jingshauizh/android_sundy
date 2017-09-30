package com.ericsson.NewPlayer.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ericsson.NewPlayer.HomePage.base.HeaderViewPager;
import com.ericsson.NewPlayer.HomePage.base.HeaderViewPagerFragment;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.moviefrag.HomeMoviesFragment;
import com.ericsson.NewPlayer.PlayerApp;
import com.ericsson.NewPlayer.R;


// data utl http://150.236.223.175:8008/db
public class HomeUIFragment extends HeaderViewPagerFragment {
    private final String TAG = "HomeUIFragment";
    private HeaderViewPager scrollableLayout;
    private ViewPager pagerHeader;
    private HomeMoviesFragment mRecliclerFragment;
    private HomeTopMenuFragment mHomeTopMenuFragment;
    private AppCompatActivity parentActivity;
    private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayoutTopMenu;
    private HomeTopMenuFragment.OnItemClickListener mOnItemClickListener;

    public static HomeUIFragment newInstance() {
        return new HomeUIFragment();
    }

    @Override
    public View getScrollableView() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View freg_view = inflater.inflate(R.layout.fragment_home_ui_main, container, false);
        parentActivity = (AppCompatActivity) getActivity();
        mRecliclerFragment = HomeMoviesFragment.newInstance();
        mHomeTopMenuFragment = HomeTopMenuFragment.newInstance();
        mLinearLayout = (LinearLayout) freg_view.findViewById(R.id.frag_linearLayout);
        mLinearLayoutTopMenu = (LinearLayout) freg_view.findViewById(R.id.frag_linearLayout_menu_top);
        FragmentManager manager = parentActivity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_linearLayout, mRecliclerFragment).commit();
        manager.beginTransaction().replace(R.id.frag_linearLayout_menu_top, mHomeTopMenuFragment).commit();
        pagerHeader = (ViewPager) freg_view.findViewById(R.id.frag_pagerHeader);
        mOnItemClickListener = new HomeTopMenuFragment.OnItemClickListener(){
            @Override
            public void onItemClick(MovieType pMovieType) {
                Log.d(TAG, "222222222 pMovieType="+pMovieType.toString());
                mRecliclerFragment.setmMovieType(pMovieType);
            }
        };

        PlayerApp.getRefWatcher(parentActivity).watch(this);

        mHomeTopMenuFragment.setListener(mOnItemClickListener);

        return freg_view;
    }


}
