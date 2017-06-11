package com.example.ladmusiciankim.searchimage.presentation.ui.image;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.viewholder.BaseViewHolder;
import com.example.ladmusiciankim.searchimage.presentation.listener.OnItemClickListener;
import com.example.ladmusiciankim.searchimage.presentation.custom.DynamicHeightImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ImageViewHolder extends BaseViewHolder<DaumImage> {

    private Context context;
    private OnItemClickListener onItemClickListener;
    private RequestManager glideRequestManager = null;

    @BindView(R.id.image_container) CardView container;
    @BindView(R.id.image_thumbnail) DynamicHeightImageView thumnail;
    @BindView(R.id.image_author) TextView author;

    public ImageViewHolder(
            Context context, ViewGroup parent,
            RequestManager manager,
            OnItemClickListener onItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));

        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.glideRequestManager = manager;

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(int position, DaumImage item) {

        container.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v.getId(), position);
        });

        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) thumnail.getLayoutParams();
        float ratio = (float)item.getHeight() / (float)item.getWidth();
        rlp.height = (int) (rlp.width * ratio);
        thumnail.setLayoutParams(rlp);
        thumnail.setRatio(ratio);

        glideRequestManager
                .load(item.getThumbnail())
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.img_default_avatar)
                .error(R.drawable.img_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .into(thumnail);
        author.setText(item.getAuthor());
    }
}
