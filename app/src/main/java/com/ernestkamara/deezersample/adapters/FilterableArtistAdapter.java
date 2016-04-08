package com.ernestkamara.deezersample.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ernestkamara.deezersample.model.DataSource;

import java.lang.ref.WeakReference;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class FilterableArtistAdapter extends BaseAdapter implements DataSource.Delegate {
    private Context mContext;
    private DataSource dataSource;

    protected final WeakReference<AdapterDelegate> mAdapterDelegate;

    public FilterableArtistAdapter(Context mContext, DataSource dataSource, AdapterDelegate adapterDelegate) {
        this.mContext = mContext;
        mAdapterDelegate = new WeakReference<>(adapterDelegate);
        this.dataSource = dataSource;
        dataSource.setDelegate(this);
    }

    @Override
    public int getCount() {
        return dataSource.getItemCount();
    }

    @Override
    public Artist getItem(int position) {
        return (Artist) dataSource.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.filterable_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        final Artist artist = getItem(position);
        if (artist != null) {
            viewHolder.mTitle.setText(artist.getName());

            Glide.with(mContext)
                 .load(artist.getPictureUrl())
                 .centerCrop()
                 .into(viewHolder.mThumb);


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AdapterDelegate delegate = mAdapterDelegate.get();
                    if (delegate != null) {
                        delegate.onItemClick(artist);
                    }
                }
            });
        }

        return convertView;
    }

    @Override
    public void notifyDataLoaded() {
        notifyDataSetChanged();
    }

    @Override
    public void notifyError(String error) {
        notifyDataSetInvalidated();
    }

    static class ViewHolder {
        @Bind(R.id.title) TextView mTitle;
        @Bind(R.id.thumb) ImageView mThumb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
