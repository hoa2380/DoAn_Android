package com.example.fashi_shop.Model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMore extends RecyclerView.OnScrollListener{
    int hiddenItem = 0;
    int allItem = 0;
    int firstItemLoaded = 6;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMore(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        allItem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager) {
            firstItemLoaded = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if (layoutManager instanceof GridLayoutManager) {
            firstItemLoaded = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(allItem <= (hiddenItem + firstItemLoaded)){
           iLoadMore.LoadMore(allItem);
        }

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
