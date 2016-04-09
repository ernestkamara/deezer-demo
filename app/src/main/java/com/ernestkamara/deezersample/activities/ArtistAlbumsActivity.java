package com.ernestkamara.deezersample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.R;
import com.ernestkamara.deezersample.adapters.ArtistAlbumRecyclerAdapter;
import com.ernestkamara.deezersample.model.ArtistAlbumsDataSource;

import butterknife.Bind;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class ArtistAlbumsActivity extends BaseActivity {
    public static final String EXTRA_ARTIST = "artist";

    @Bind(R.id.albums_recyclerview) RecyclerView mAlbumsRecyclerView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.artist_cover_backdrop)
    ImageView mArtistBackdrop;

    private Artist mArtist;
    private ArtistAlbumsDataSource mArtistAlbumsDataSource;
    private ArtistAlbumRecyclerAdapter mArtistAlbumRecyclerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_artist_albums;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mArtist = savedInstanceState.getParcelable(EXTRA_ARTIST);

        }else {
            Intent intent = getIntent();
            mArtist = intent.getParcelableExtra(EXTRA_ARTIST);
        }


        setTitle(mArtist.getName());
        Glide.with(this).load(mArtist.getBigImageUrl()).centerCrop().into(mArtistBackdrop);

        mArtistAlbumsDataSource = getDataSourceFactory().newArtistAlbumsDataSource(mArtist);
        mArtistAlbumsDataSource.fetchData();

        mArtistAlbumRecyclerAdapter = new ArtistAlbumRecyclerAdapter(this, mArtistAlbumsDataSource, this);

        mAlbumsRecyclerView.setLayoutManager(new GridLayoutManager(this.getBaseContext(), 2));
        mAlbumsRecyclerView.setAdapter(mArtistAlbumRecyclerAdapter);
    }

    @Override
    public void onItemClick(Object object) {
        Intent intent = new Intent(this, AlbumTracksActivity.class);
        intent.putExtra(AlbumTracksActivity.EXTRA_ALBUM, (Album)object);
        startActivity(intent);
    }

    @Override
    public void onItemsLoadingComplete() {
        mProgressBar.setVisibility(View.GONE);
        mAlbumsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemsLoadingFailed(String error) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(EXTRA_ARTIST, mArtist);
        super.onSaveInstanceState(savedInstanceState);
    }
}
