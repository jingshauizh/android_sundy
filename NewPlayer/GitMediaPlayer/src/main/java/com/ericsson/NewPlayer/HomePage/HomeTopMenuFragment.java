package com.ericsson.NewPlayer.HomePage;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.R;


public class HomeTopMenuFragment extends Fragment {

    private TextView txt1, txt2, txt3, txt4, txt5, txt6,txt7;

    public static HomeTopMenuFragment newInstance() {
        return new HomeTopMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View freg_view = inflater.inflate(R.layout.fragment_home_top_menu, container, false);
        txt1 = ((TextView) freg_view.findViewById(R.id.homepage_menu_index));
        txt2 = ((TextView) freg_view.findViewById(R.id.homepage_menu_live));
        txt3 = ((TextView) freg_view.findViewById(R.id.homepage_menu_tvseries));
        txt4 = ((TextView) freg_view.findViewById(R.id.homepage_menu_movie));
        txt5 = ((TextView) freg_view.findViewById(R.id.homepage_menu_cartoon));
        txt6 = ((TextView) freg_view.findViewById(R.id.homepage_menu_favourite));
        txt7 = ((TextView) freg_view.findViewById(R.id.homepage_menu_recommendview));

        //在代码中设置加粗
        //添加下划线
        txt1.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt2.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt3.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt4.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt5.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt6.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt7.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.ALL);
                //Toast.makeText(getActivity(), txt1.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.LIVE);
                //Toast.makeText(getActivity(), txt2.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.TVSERIES);
                //Toast.makeText(getActivity(), txt3.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.MOVIES);
                //Toast.makeText(getActivity(), txt4.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.CARTOON);
                //Toast.makeText(getActivity(), txt4.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.FAVOURITE);
                //Toast.makeText(getActivity(), txt5.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(MovieType.RECOMMEND);
                //Toast.makeText(getActivity(), txt6.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return freg_view;
    }



    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MovieType pMovieType);
    }



}
