package com.arouter.jingshuai.mvpdemo.newa;

/**
 * Created by eqruvvz on 2/28/2018.
 */

public class NewPresenter implements IContract.Ipresenter{
    private IContract.IView mView;
    private NewsModel mNewsModel;

    public NewPresenter(IContract.IView pView){
        this.mView = pView;
        mView.setPresenter(this);
        mNewsModel = new NewsModel();

    }
    @Override
    public void getNextNews() {
        String news = mNewsModel.getNextNews();
        mView.updateNews(news);
    }
}
