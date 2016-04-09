package com.ernestkamara.deezersample.model;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.ApiRequestFactory;

import java.util.ArrayList;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class FilterableArtistDataSource extends DataSource<Artist> implements Filterable{
    private ArrayList<Artist> mArtists;
    private String mQueryString;
    public FilterableArtistDataSource(Context context) {
        super(context);
        mArtists = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    @Override
    public Artist getItem(int position) {
        return mArtists.get(position);
    }

    @Override
    public void fetchData() {
        if (mQueryString != null) {
            getFilter().filter(mQueryString);
        }
    }

    public void setQueryString(String queryString) {
        mQueryString = queryString;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                mApiRequestFactory.newArtistRequest((String) constraint, new ApiRequestFactory.RequestCallback() {
                    @Override
                    public void onRequestSuccess(Object o) {
                        FilterResults results = new FilterResults();
                        if (constraint != null) {
                            mArtists = (ArrayList<Artist>) o;
                        }

                        if (mArtists != null) {
                            results.values = mArtists;
                            results.count = mArtists.size();
                        }
                        publishResults(constraint, results);
                    }

                    @Override
                    public void onRequestFailed(Exception e) {
                        publishResults(constraint, null);
                    }
                });

                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataLoaded();

                } else {
                    notifyError("Artist not found");
                }
            }
        };
    }
}
