package com.ernestkamara.deezersample.adapters.viewholders;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.Bind;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class AlbumViewHolder extends BaseViewHolder {
    protected Album mAlbum;
    protected Artist mArtist;

    @Bind(R.id.album_thumb) ImageView mAlbumThumb;
    @Bind(R.id.album_title) TextView mAlbumTitle;
    @Bind(R.id.album_artists) TextView mAlbumArtists;


    public AlbumViewHolder(View itemView, AdapterDelegate adapterDelegate) {
        super(itemView, adapterDelegate);

        mAlbumThumb.setOnClickListener(this);

        DisplayMetrics displayMetrics = itemView.getResources().getDisplayMetrics();
        mAlbumThumb.getLayoutParams().width = getThumbWidth(displayMetrics, 2);
        mAlbumThumb.getLayoutParams().height = getThumbWidth(displayMetrics, 2);
    }

    public void recycleView() {
        Glide.clear(mAlbumThumb);
    }

    @Override
    protected Object getItem() {
        return mAlbum;
    }

    @Override
    public void bindItem(Object item) {
        mAlbum = (Album) item;
        mAlbumTitle.setText(mAlbum.getTitle());

        if (mArtist != null) {
            mAlbumArtists.setText(mArtist.getName());
        }

        Glide.with(mAlbumThumb.getContext())
             .load(mAlbum.getCoverUrl())
             .centerCrop()
             .into(mAlbumThumb);
    }

    public int getThumbWidth(DisplayMetrics displayMetrics, int numColumns ) {
        int widthPixels = displayMetrics.widthPixels;
        int finalWidth;
        int marginsTotal = numColumns * 1;
        finalWidth = (widthPixels - marginsTotal) / numColumns;
        return finalWidth;
    }

    public void setArtist(Artist artist) {
        mArtist = artist;
    }
}