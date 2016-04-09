package com.ernestkamara.deezersample.model;

import android.content.Context;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Artist;

/**
 * @author Ernest Saidu Kamara
 * @since 09/04/16.
 */
public class DataSourceFactory {
    private final Context mContext;

    public DataSourceFactory(Context context) {
        mContext = context;
    }

    public AlbumTracksDataSource newAlbumTracksDataSource(Album album){
        return new AlbumTracksDataSource(mContext, album);
    }

    public ArtistAlbumsDataSource newArtistAlbumsDataSource(Artist artist) {
        return new ArtistAlbumsDataSource(mContext, artist);
    }

    public FilterableArtistDataSource newFilterableArtistDataSource(){
        return new FilterableArtistDataSource(mContext);
    }
}
