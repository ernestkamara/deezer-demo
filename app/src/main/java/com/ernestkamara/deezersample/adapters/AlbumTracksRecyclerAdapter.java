package com.ernestkamara.deezersample.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import com.ernestkamara.deezersample.adapters.viewholders.AlbumTrackViewHolder;
import com.ernestkamara.deezersample.adapters.viewholders.BaseViewHolder;
import com.ernestkamara.deezersample.model.DataSource;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class AlbumTracksRecyclerAdapter extends BaseRecyclerViewAdapter {

    public AlbumTracksRecyclerAdapter(Context mContext, DataSource dataSource, AdapterDelegate adapterDelegate) {
        super(mContext, dataSource, adapterDelegate);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent) {
        return new AlbumTrackViewHolder(inflateLayout(R.layout.album_track_list_item, parent), mAdapterDelegate.get());
    }

}
