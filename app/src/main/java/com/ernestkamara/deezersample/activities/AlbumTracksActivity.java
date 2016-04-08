package com.ernestkamara.deezersample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Album;
import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.AlbumTracksRecyclerAdapter;
import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ernestkamara.deezersample.model.AlbumTracksDataSource;


/**
 * @author Ernest Saidu Kamara
 * @since 08/04/16.
 */
public class AlbumTracksActivity extends AppCompatActivity implements AdapterDelegate {
    public static final String EXTRA_ALBUM = "album";

    @Bind(R.id.album_cover_backdrop) ImageView mAlbumBackdrop;
    @Bind(R.id.tracks_recyclerview) RecyclerView mTracksRecyclerView;

    private AlbumTracksRecyclerAdapter mTracksRecyclerAdapter;
    private AlbumTracksDataSource mAlbumTracksDataSource;
    private Album mAlbum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_tracks);
        ButterKnife.bind(this);


        if (savedInstanceState != null) {
            mAlbum = savedInstanceState.getParcelable(EXTRA_ALBUM);

        }else {
            Intent intent = getIntent();
            mAlbum = intent.getParcelableExtra(EXTRA_ALBUM);
        }


        Intent intent = getIntent();
        mAlbum = intent.getParcelableExtra(EXTRA_ALBUM);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mAlbum.getTitle());

        Glide.with(this).load(mAlbum.getBigImageUrl()).centerCrop().into(mAlbumBackdrop);

        mAlbumTracksDataSource = new AlbumTracksDataSource(this, mAlbum);
        mAlbumTracksDataSource.fetchAlbumTracks();

        mTracksRecyclerAdapter = new AlbumTracksRecyclerAdapter(this, mAlbumTracksDataSource, this);

        mTracksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTracksRecyclerView.setAdapter(mTracksRecyclerAdapter);
    }

    @Override
    public void onItemClick(Object object) {
        Toast.makeText(this, "Playing not support", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemsLoadingComplete() {

    }

    @Override
    public void onItemsLoadingFailed(String error) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(EXTRA_ALBUM, mAlbum);
        super.onSaveInstanceState(savedInstanceState);
    }
}
