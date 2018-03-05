package com.arouter.jingshuai.mvpdemo.newa;

/**
 * Created by eqruvvz on 2/28/2018.
 */

public interface IContract {
    public interface IView{
        void updateNews(String newsText);
        void setPresenter(NewPresenter mNewPresenter);
    }



    public interface Ipresenter{
        void getNextNews();
    }
}
