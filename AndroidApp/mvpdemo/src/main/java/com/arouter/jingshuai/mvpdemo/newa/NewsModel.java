package com.arouter.jingshuai.mvpdemo.newa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eqruvvz on 2/28/2018.
 */

public class NewsModel {
    private List<String> newsList = new ArrayList<String>();
    private int index = 0;
    public NewsModel(){
        newsList.add("new 1");
        newsList.add("new 2");
        newsList.add("new 3");
    }

    public String getNextNews(){
        index++;
        if(index >=0 && index <newsList.size()){
            return newsList.get(index);
        }
        else{
            index=0;
            return newsList.get(index);
        }
    }





}
