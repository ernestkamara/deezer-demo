package com.ernestkamara.deezersample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import com.ernestkamara.deezersample.adapters.viewholders.AlbumViewHolder;
import com.ernestkamara.deezersample.adapters.viewholders.BaseViewHolder;
import com.ernestkamara.deezersample.model.ArtistAlbumsDataSource;
import com.ernestkamara.deezersample.model.DataSource;

import java.lang.ref.WeakReference;

/**
 * @author Ernest Saidu Kamara
 * @since 08/04/16.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> implements DataSource.Delegate {
    protected final Context mContext;
    protected final DataSource mDataSource;

    protected final WeakReference<AdapterDelegate> mAdapterDelegate;

    public BaseRecyclerViewAdapter(Context context, DataSource dataSource, AdapterDelegate adapterDelegate) {
        mContext = context;
        mDataSource = dataSource;
        mAdapterDelegate = new WeakReference<>(adapterDelegate);

        dataSource.setDelegate(this);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mDataSource instanceof ArtistAlbumsDataSource) {
            ((AlbumViewHolder)holder).setArtist(((ArtistAlbumsDataSource)mDataSource).getArtist());
        }
        holder.bindItem(mDataSource.getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDataSource.getItemCount();
    }

    @Override
    public void notifyDataLoaded() {
        notifyDataSetChanged();
        mAdapterDelegate.get().onItemsLoadingComplete();
    }

    @Override
    public void notifyError(String error) {
        mAdapterDelegate.get().onItemsLoadingFailed(error);
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent);

    protected View inflateLayout(int layoutId, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutId, parent, false);
    }

}
