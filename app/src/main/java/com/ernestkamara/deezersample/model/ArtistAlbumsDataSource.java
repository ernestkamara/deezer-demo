package com.ernestkamara.deezersample.model;

import android.content.Context;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.ApiRequestFactory;

import java.util.ArrayList;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class ArtistAlbumsDataSource extends DataSource<Album> {
    private ArrayList<Album> mAlbums;
    private final Artist mArtist;

    public ArtistAlbumsDataSource(Context context, Artist artist) {
        super(context);
        mArtist = artist;
        mAlbums = new ArrayList<>();
    }

    /**
     * Get Album artist
     * @return
     */
    public Artist getArtist() {
        return mArtist;
    }


    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    @Override
    public Album getItem(int position) {
        return mAlbums.get(position);
    }

    /**
     * Load all Album associated to mArtist
     *
     */
    public void fetchArtistAlbums() {
        mApiRequestFactory.newArtistAlbumRequest(getArtist().getId(), new ApiRequestFactory.RequestCallback() {
            @Override
            public void onRequestSuccess(Object o) {
                if (o != null) {
                    mAlbums = (ArrayList<Album>) o;
                    notifyDataLoaded();
                }
            }

            @Override
            public void onRequestFailed(Exception e) {
                notifyError(e.getMessage());
            }
        });
    }
}
