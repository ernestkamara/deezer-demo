package com.ernestkamara.deezersample.model;

import android.content.Context;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Track;
import com.ernestkamara.deezersample.ApiRequestFactory;

import java.util.ArrayList;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class AlbumTracksDataSource extends DataSource<Track>{
    private ArrayList<Track> mTracks;
    private final Album mAlbum;

    public AlbumTracksDataSource(Context context, Album album) {
        super(context);
        mAlbum = album;
        mTracks = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return mTracks.get(position);
    }

    public Album getAlbum() {
        return mAlbum;
    }

    /**
     * Load all Tracks associated to mAlbum
     */
    public void fetchAlbumTracks() {
     mApiRequestFactory.newAlbumTracksRequest(mAlbum.getId(), new ApiRequestFactory.RequestCallback() {
         @Override
         public void onRequestSuccess(Object result) {
             mTracks = (ArrayList<Track>) result;
             notifyDataLoaded();
         }

         @Override
         public void onRequestFailed(Exception exception) {
             notifyError(exception.getMessage());
         }
     });
    }
}
