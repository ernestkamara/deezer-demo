package com.ernestkamara.deezersample.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import com.deezer.sdk.model.Artist;
import com.deezer.sdk.model.Track;
import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.Bind;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class AlbumTrackViewHolder extends BaseViewHolder {
    private Track mTrack;

    @Bind(R.id.track_number) TextView mAlbumTrackNumber;
    @Bind(R.id.track_title) TextView mAlbumTrackTitle;
    @Bind(R.id.track_artists) TextView mAlbumTrackArtists;
    @Bind(R.id.track_duration) TextView mAlbumTrackDuration;

    public AlbumTrackViewHolder(View itemView, AdapterDelegate adapterDelegate) {
        super(itemView, adapterDelegate);
        itemView.setOnClickListener(this);
    }

    @Override
    protected Object getItem() {
        return mTrack;
    }

    @Override
    public void bindItem(Object item) {
        mTrack = (Track) item;
        mAlbumTrackNumber.setText(String.valueOf(mTrack.getTrackPosition()) + ".");
        mAlbumTrackTitle.setText(mTrack.getShortTitle());
        int duration = mTrack.getDuration();

        int minutes = duration / 60;
        int seconds = duration % 60;
        mAlbumTrackDuration.setText(String.format("%d:%02d", minutes, seconds));

        Artist artist = mTrack.getArtist();
        if (artist != null){
            mAlbumTrackArtists.setText(artist.getName());
        }
    }
}
