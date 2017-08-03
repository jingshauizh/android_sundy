package com.baozi.treerecyclerview.adpater.wapper;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.adpater.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

/**
 * Created by baozi on 2017/4/30.
 */

public class HeaderAndFootWapper<T extends BaseItem> extends BaseWapper<T> {

    private SparseArray<View> mHeaderViews = new SparseArray<>();
    private SparseArray<View> mFootViews = new SparseArray<>();

    public HeaderAndFootWapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
        mAdapter.setCheckItem(new CheckItem() {
            @Override
            public boolean checkPosition(int position) {
                return !(isHeaderViewPos(position) || isFooterViewPos(position));
            }

            @Override
            public int getAfterCheckingPosition(int position) {
                return position - getHeadersCount();
            }
        });
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return ViewHolder.createViewHolder( mFootViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        super.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - mAdapter.getItemCount());
        }
        return super.getItemViewType(position - getHeadersCount());
    }


    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size(), view);
    }

    public void addFootView(View view) {
        mFootViews.put(Integer.MAX_VALUE - mFootViews.size(), view);
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + mAdapter.getItemCount();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }


}
