package com.ernestkamara.deezersample.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import com.ernestkamara.deezersample.adapters.viewholders.AlbumViewHolder;
import com.ernestkamara.deezersample.adapters.viewholders.BaseViewHolder;
import com.ernestkamara.deezersample.model.DataSource;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class ArtistAlbumRecyclerAdapter extends BaseRecyclerViewAdapter {

    public ArtistAlbumRecyclerAdapter(Context context, DataSource dataSource, AdapterDelegate adapterDelegate) {
        super(context, dataSource, adapterDelegate);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent) {
        return new AlbumViewHolder(inflateLayout(R.layout.artist_album_list_item, parent), mAdapterDelegate.get());
    }

    @Override
    public void onViewRecycled(BaseViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ((AlbumViewHolder)viewHolder).recycleView();
    }
}
