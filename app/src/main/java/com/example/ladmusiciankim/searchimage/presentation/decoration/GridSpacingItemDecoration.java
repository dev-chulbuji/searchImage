package com.example.ladmusiciankim.searchimage.presentation.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.ladmusiciankim.searchimage.R;

/**
 * Created by ladmusician.kim on 2017. 6. 11..
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int offsetSize;

    public GridSpacingItemDecoration(Context context) {
        offsetSize = context.getResources().getDimensionPixelSize(R.dimen.item_offset);
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        StaggeredGridLayoutManager.LayoutParams layoutParams
                = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();

        //int position = layoutParams.getViewPosition();
        int position = parent.getChildAdapterPosition(view);
        if (position == RecyclerView.NO_POSITION) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        int itemSpanIndex = layoutParams.getSpanIndex();

        outRect.right = itemSpanIndex != 0 ? offsetSize : offsetSize/2;
        outRect.left = itemSpanIndex != 0 ? offsetSize/2 : offsetSize;
        outRect.top = position < 2 ? offsetSize : 0;
        outRect.bottom = offsetSize;
    }
}
